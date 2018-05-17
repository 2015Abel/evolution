package com.evolution.es.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @description: es配置
 * @author: liuzijian
 * @date: 2018-05-16 10:09
 */
@Configuration
@EnableElasticsearchRepositories("com.evolution.es")
public class ESConfig {

}
