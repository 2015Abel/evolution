package com.evolution.es.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Map;

/**
 * @description: 实体
 * @author: liuzijian
 * @date: 2018-05-16 10:16
 */
@Data
@Document(indexName = "imei",type = "apus")
public class EqImei extends BaseEquipment {
    private static final long serialVersionUID = -1L;

    private Map<String,Object> product;
}
