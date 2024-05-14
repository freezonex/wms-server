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

    private static final long serialVersionUID = 1L;

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

    private Long suggested_storage_location_id;
    private String suggested_storage_location_name;
}