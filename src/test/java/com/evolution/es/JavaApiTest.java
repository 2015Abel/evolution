package com.evolution.es;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @date: 2018-05-17 11:16
 */
@RunWith(JUnit4.class)
public class JavaApiTest {
    /*TransportClient client = null;

    @Before
    public void initClient() {
        try {
//            TransportAddress
            Settings settings = Settings.builder().put("cluster.name","my-application").build();
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(new InetSocketAddress("127.0.0.1",9300)));
//            client = new TransportClient.Builder().build()
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(address),port));
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
            GetResponse getReponse = iterator.next().getResponse();
            if(getReponse!=null){
                Map<String,Object> map = getReponse.getSource();
                for(Map.Entry entry:map.entrySet()){
                    System.out.println(">>>> "+entry.getKey()+":"+entry.getValue());
                }
            }
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
    }*/
}
