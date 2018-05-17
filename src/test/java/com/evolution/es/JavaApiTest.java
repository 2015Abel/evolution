package com.evolution.es;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Iterator;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @date: 2018-05-17 11:16
 */
@RunWith(JUnit4.class)
public class JavaApiTest {
    TransportClient client = null;

    @Before
    public void initClient() {
        try {
//            TransportAddress
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        } catch (Exception ex) {
            ex.printStackTrace();
            if(client!=null){
                client.close();
            }
        }
    }

    @After
    public void destoryClient(){
        if(client!=null){
            client.close();
        }
    }

    @Test
    public void mget() {
        MultiGetResponse response = client.prepareMultiGet().add("mac","apus","m123")
                .add("imei","apus","i444").get();
        Iterator<MultiGetItemResponse> iterator = response.iterator();
        while (iterator.hasNext()){
            System.out.println("<<<<<" + iterator.next().getResponse().getSourceAsString());
        }
    }

    @Test
    public void mapping() throws IOException {
        // 使用XContentBuilder创建Mapping
        XContentBuilder builder =
                XContentFactory.jsonBuilder()
                        .startObject()
                            .field("browser")
                                .startObject()
                                    .field("type","keyword")
                                .endObject()
                        .endObject();
        System.out.println(builder.string());
        PutMappingRequest mappingRequest = Requests.putMappingRequest("mac").source(builder).type("apus");
        this.client.admin().indices().putMapping(mappingRequest).actionGet();
    }

    @Test
    public void add() throws IOException {
        XContentBuilder productBuilder = XContentFactory.jsonBuilder()
                .startObject()
                    .field("browser",222)
                    .field("camera",111)
                .endObject();
        client.prepareIndex().setIndex("mac").setType("apus").setId("m123").setSource(productBuilder).get();
    }
}
