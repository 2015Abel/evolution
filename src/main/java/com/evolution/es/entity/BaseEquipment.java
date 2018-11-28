package com.evolution.es.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @date: 2018-05-16 17:26
 */
public class BaseEquipment implements Serializable {

    @Id
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
