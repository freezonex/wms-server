package com.supos.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.supos.app.entity.WmsInventoryOperation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface WmsInboundMapper extends BaseMapper<WmsInventoryOperation> {
    int insertSelective(WmsInventoryOperation wmsInventoryOperation);

    int updateRecordById(WmsInventoryOperation wmsInventoryOperation);

    int deleteRecordById(WmsInventoryOperation wmsInventoryOperation);

    List<WmsInventoryOperation> selectAll(WmsInventoryOperation wmsInventoryOperation);
}



