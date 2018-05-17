package com.evolution.es.repository;

import com.evolution.es.entity.EqImei;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @date: 2018-05-16 10:03
 */
public interface ImeiRepository extends ElasticsearchRepository<EqImei,String> {

}
