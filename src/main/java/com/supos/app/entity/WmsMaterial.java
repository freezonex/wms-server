package com.supos.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import lombok.Data;

/**
 * 
 * @TableName wms_material
 */
@Data
public class WmsMaterial implements Serializable {

    private Long id;
    private String material_code;
    private String name;
    private String material_type;
    private String unit;
    private String note;
    private String specification;
    private Long max;
    private Long min;
    private String status;
    private Long expect_wh_id;
    private String expect_storage_locations;
    private Boolean del_flag;
    private Date create_time;
    private Date update_time;

    private static final long serialVersionUID = 1L;

    private Long suggested_storage_location_id;
    private String suggested_storage_location_name;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        WmsMaterial other = (WmsMaterial) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMaterial_code() == null ? other.getMaterial_code() == null : this.getMaterial_code().equals(other.getMaterial_code()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getMaterial_type() == null ? other.getMaterial_type() == null : this.getMaterial_type().equals(other.getMaterial_type()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getSpecification() == null ? other.getSpecification() == null : this.getSpecification().equals(other.getSpecification()))
            && (this.getMax() == null ? other.getMax() == null : this.getMax().equals(other.getMax()))
            && (this.getMin() == null ? other.getMin() == null : this.getMin().equals(other.getMin()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getExpect_wh_id() == null ? other.getExpect_wh_id() == null : this.getExpect_wh_id().equals(other.getExpect_wh_id()))
            && (this.getExpect_storage_locations() == null ? other.getExpect_storage_locations() == null : this.getExpect_storage_locations().equals(other.getExpect_storage_locations()))
            && (this.getDel_flag() == null ? other.getDel_flag() == null : this.getDel_flag().equals(other.getDel_flag()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
            && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMaterial_code() == null) ? 0 : getMaterial_code().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getMaterial_type() == null) ? 0 : getMaterial_type().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getSpecification() == null) ? 0 : getSpecification().hashCode());
        result = prime * result + ((getMax() == null) ? 0 : getMax().hashCode());
        result = prime * result + ((getMin() == null) ? 0 : getMin().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getExpect_wh_id() == null) ? 0 : getExpect_wh_id().hashCode());
        result = prime * result + ((getExpect_storage_locations() == null) ? 0 : getExpect_storage_locations().hashCode());
        result = prime * result + ((getDel_flag() == null) ? 0 : getDel_flag().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", material_code=").append(material_code);
        sb.append(", name=").append(name);
        sb.append(", material_type=").append(material_type);
        sb.append(", unit=").append(unit);
        sb.append(", note=").append(note);
        sb.append(", specification=").append(specification);
        sb.append(", max=").append(max);
        sb.append(", min=").append(min);
        sb.append(", status=").append(status);
        sb.append(", expect_wh_id=").append(expect_wh_id);
        sb.append(", expect_storage_locations=").append(expect_storage_locations);
        sb.append(", del_flag=").append(del_flag);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterial_code() {
        return material_code;
    }

    public void setMaterial_code(String material_code) {
        this.material_code = material_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(String material_type) {
        this.material_type = material_type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getExpect_wh_id() {
        return expect_wh_id;
    }

    public void setExpect_wh_id(Long expect_wh_id) {
        this.expect_wh_id = expect_wh_id;
    }

    public String getExpect_storage_locations() {
        return expect_storage_locations;
    }

    public void setExpect_storage_locations(String expect_storage_locations) { this.expect_storage_locations = expect_storage_locations; }

    public Boolean getDel_flag() { return del_flag; }

    public void setDel_flag(Boolean del_flag) { this.del_flag = del_flag; }

    public Date getCreate_time() { return create_time; }

    public void setCreate_time(Date create_time) { this.create_time = create_time; }

    public Date getUpdate_time() { return update_time; }

    public void setUpdate_time(Date update_time) { this.update_time = update_time; }

    public Long getSuggested_storage_location_id() {
        return suggested_storage_location_id;
    }

    public void setSuggested_storage_location_id(Long suggested_storage_location_id) { this.suggested_storage_location_id = suggested_storage_location_id; }

    public String getSuggested_storage_location_name() {
        return suggested_storage_location_name;
    }

    public void setSuggested_storage_location_name(String suggested_storage_location_name) { this.suggested_storage_location_name = suggested_storage_location_name; }
}