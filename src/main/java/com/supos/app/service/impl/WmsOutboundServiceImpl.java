package com.supos.app.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.supos.app.domain.entity.WmsInventoryOperation;
import com.supos.app.domain.entity.WmsInventoryOperationDetail;
import com.supos.app.domain.entity.WmsRfidMaterial;
import com.supos.app.domain.entity.WmsTask;
import com.supos.app.domain.entity.*;
import com.supos.app.mapper.WmsOutboundDetailMapper;
import com.supos.app.mapper.WmsOutboundMapper;
import com.supos.app.service.InventoryUpdateService;
import com.supos.app.service.WmsOutboundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class WmsOutboundServiceImpl extends ServiceImpl<WmsOutboundMapper, WmsInventoryOperation>
        implements WmsOutboundService {

    @Autowired
    WmsRfidMaterialServiceImpl wmsRfidMaterialServiceImpl;

    @Autowired
    private WmsOutboundMapper wmsInventoryOperationMapper;

    @Autowired
    private WmsOutboundDetailMapper wmsInventoryOperationDetailMapper;

    @Autowired
    private WmsMaterialServiceImpl wmsMaterialServiceImpl;

    @Autowired
    private WmsStorageLocationServiceImpl wmsStorageLocationServiceImpl;

    @Autowired
    private InventoryUpdateService inventoryUpdateService;

    @Autowired
    private WmsTaskServiceImpl wmsTaskServiceImpl;

    @Transactional
    public int insertSelective(WmsInventoryOperation wmsInventoryOperation) {
        if( wmsInventoryOperation.getWmsInventoryOperationDetails() == null || wmsInventoryOperation.getWmsInventoryOperationDetails().isEmpty()) {
            throw new IllegalArgumentException("inventory operation details are required");
        }
        if( !"PDA".equals(wmsInventoryOperation.getSource()) &&  !"manual".equals(wmsInventoryOperation.getSource()) ) {
            throw new IllegalArgumentException("source must be PDA or manual");
        }
        // 1. insert wms_outbound table
        Long id = wmsInventoryOperation.getId();
        if (id == null) {
            id = IdWorker.getId();
            wmsInventoryOperation.setId(id);
        }
        int rows_affected = wmsInventoryOperationMapper.insertSelective(wmsInventoryOperation);

        if ("PDA".equals(wmsInventoryOperation.getSource())) {

            // for PDA, there are only RFID in Operation Detail, each RFID mapping to 1 material and 1 quantity, need summarize first
            Map<Long, WmsInventoryOperationDetail> material_id_detail_map = new HashMap<>();
            wmsInventoryOperation.getWmsInventoryOperationDetails().forEach(wmsInventoryOperationDetail -> {

                WmsRfidMaterial wmsRfidMaterial = new WmsRfidMaterial();
                wmsRfidMaterial.setRf_id(wmsInventoryOperationDetail.getRf_id());
                List<WmsRfidMaterial> wmsRfidMaterials = wmsRfidMaterialServiceImpl.selectall(wmsRfidMaterial);

                if (!wmsRfidMaterials.isEmpty()) {
                    wmsRfidMaterial = wmsRfidMaterials.get(0);
                    Long material_id = wmsRfidMaterial.getMaterial_id();
                    if (material_id_detail_map.containsKey(material_id)) {
                        WmsInventoryOperationDetail detail = material_id_detail_map.get(material_id);
                        detail.setQuantity(detail.getQuantity() + 1);
                    } else {
                        WmsInventoryOperationDetail detail = new WmsInventoryOperationDetail();
                        material_id_detail_map.put(material_id, detail);
                    }
                }
            });
            if (!material_id_detail_map.isEmpty())
            {
                wmsInventoryOperation.setWmsInventoryOperationDetails((List<WmsInventoryOperationDetail>) material_id_detail_map.values());
            }
        }

        // 2. insert wms_outbound_detail table
        for (WmsInventoryOperationDetail detail : wmsInventoryOperation.getWmsInventoryOperationDetails()) {
            detail.setOperation_id(id);
            wmsInventoryOperationDetailMapper.insertSelective(detail);
        }

        // 3. update wms_storage_location table, assume 1 storage location only store 1 kind of material
        for (WmsInventoryOperationDetail wmsInventoryOperationDetail : wmsInventoryOperation.getWmsInventoryOperationDetails()) {
            inventoryUpdateService.updateStorageLocationMaterial(wmsInventoryOperationDetail.getLocation_id(), wmsInventoryOperationDetail.getMaterial_id(), wmsInventoryOperationDetail.getQuantity(), false);
        }

        // 4. update wms_material_storage_location table
        for (WmsInventoryOperationDetail wmsInventoryOperationDetail : wmsInventoryOperation.getWmsInventoryOperationDetails()) {
            inventoryUpdateService.updateMaterialStorageLocation(wmsInventoryOperationDetail.getMaterial_id(), wmsInventoryOperationDetail.getLocation_id(), wmsInventoryOperationDetail.getQuantity(), false);
        }

        // 5. create pickup task
        WmsTask wmsTask = new WmsTask();
        wmsTask.setId(IdWorker.getId());
        wmsTask.setOperation_id(id);
        wmsTask.setType("pickup");
        wmsTask.setNote("Outbound triggered pickup task on " + DateTime.now());
        wmsTask.setStatus("pending");
        wmsTaskServiceImpl.insertSelective(wmsTask);

        //6. use rule to auto assign people and resources
        inventoryUpdateService.updatePeopleAndResourceByRule(wmsTask);

        return rows_affected;
    }

    public int updateOperationById(WmsInventoryOperation wmsInventoryOperation) {
        return wmsInventoryOperationMapper.updateRecordById(wmsInventoryOperation);
    }

    public int deleteOperationById(WmsInventoryOperation wmsInventoryOperation) {
        return wmsInventoryOperationMapper.deleteRecordById(wmsInventoryOperation);
    }

    public List<WmsInventoryOperation> selectAll(WmsInventoryOperation wmsInventoryOperation) {
        List<WmsInventoryOperation> wmsInventoryOperations = wmsInventoryOperationMapper.selectAll(wmsInventoryOperation);
        for(WmsInventoryOperation operation: wmsInventoryOperations) {
            WmsInventoryOperationDetail wmsInventoryOperationDetail = new WmsInventoryOperationDetail();
            wmsInventoryOperationDetail.setOperation_id(operation.getId());
            List<WmsInventoryOperationDetail> details = wmsInventoryOperationDetailMapper.selectAll(wmsInventoryOperationDetail);
            operation.setWmsInventoryOperationDetails(details);
        }
        return wmsInventoryOperations;
    }

}




