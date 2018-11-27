package com.evolution.es;

import com.evolution.es.entity.EqAndroid;
import com.evolution.es.entity.EqImei;
import com.evolution.es.repository.AndroidRepository;
import com.evolution.es.repository.ImeiRepository;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @date: 2018-05-16 16:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ESStarter.class)
public class ESRepositoryTest {

    @Resource
    AndroidRepository androidRepository;

    @Resource
    ImeiRepository imeiRepository;

    @Resource
    ElasticsearchOperations esTemplate;

    @Test
    public void testOperation(){
        EqAndroid android = new EqAndroid();
        android.setId("a123");
        android.setProduct(new HashMap<String, Object>(ImmutableMap.of("browser",222)));
        androidRepository.index(android);

        EqAndroid android2 = new EqAndroid();
        android.setId("a124");
        android.setProduct(new HashMap<String, Object>(ImmutableMap.of("browser",444,
                "camera",333)));
        androidRepository.index(android);

        EqImei imei = new EqImei();
        imei.setId("i444");
        imei.setProduct(new HashMap<String, Object>(ImmutableMap.of("browser",444)));
        imeiRepository.index(imei);
    }

    @Test
    public void setEsTemplate(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withIndices("android","imei")
                .withTypes("apus").withIds(Arrays.asList("a123","i55")).build();
        LinkedList<EqAndroid> eqList = esTemplate.multiGet(searchQuery,EqAndroid.class);
        for(EqAndroid eq:eqList){
//            if(eq instanceof EqAndroid){
            System.out.println("<<<<<       "+EqAndroid.class.cast(eq));
//            }else {
//                System.out.println("<<<<<       "+EqImei.class.cast(eq));
//            }
        }

    }


}
