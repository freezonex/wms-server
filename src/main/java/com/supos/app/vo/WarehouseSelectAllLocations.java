package com.supos.app.vo;

import com.supos.app.entity.WmsStorageLocation;

import java.util.Date;
import java.util.List;

public class WarehouseSelectAllLocations {
    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Long warehouse_id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String material_name;

    /**
     *
     */
    private Integer del_flag;

    /**
     *
     */
    private Date update_time;

    /**
     *
     */
    private Date create_time;

    private List<WarehouseSelectAllMaterial> materials;
    private static final long serialVersionUID = 1L;

    public WarehouseSelectAllLocations(WmsStorageLocation storageLocation) {
        this.id = storageLocation.getId();
        this.warehouse_id = storageLocation.getWarehouse_id();
        this.name = storageLocation.getName();
        this.material_name = storageLocation.getMaterial_name();
        this.del_flag = storageLocation.getQuantity();
        this.update_time = storageLocation.getUpdate_time();
        this.create_time = storageLocation.getCreate_time();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(Long warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public Integer getQuantity() {
        return del_flag;
    }

    public void setQuantity(Integer del_flag) {
        this.del_flag = del_flag;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public List<WarehouseSelectAllMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<WarehouseSelectAllMaterial> materials) {
        this.materials = materials;
    }
}
