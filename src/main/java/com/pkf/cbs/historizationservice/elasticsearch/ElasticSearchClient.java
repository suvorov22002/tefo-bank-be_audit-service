package com.pkf.cbs.historizationservice.elasticsearch;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkf.cbs.historizationservice.utils.MapperConfig;
import com.tefo.library.commonutils.basestructure.dto.AuditTrailRequest;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class ElasticSearchClient {

   @Autowired
   private ElasticSearchConnection connection;

   @Autowired
   private RestHighLevelClient client;

    @Autowired
    private MapperConfig mapper;

     public void createIndex(String index) {

         try{

             CreateIndexRequest request = new CreateIndexRequest(index);
             request.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 2));

             CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
             System.out.println("response id: " + createIndexResponse.index());

         }
         catch (IOException e) {
             e.printStackTrace();
         }
         catch (ElasticsearchStatusException el){
             throw new IllegalArgumentException("Index %s already exist");
         }

     }

    public void insertDataPojos(AuditTrailRequest object) {

        IndexResponse indexResponse = null;
        String index;
        try{

            index = null == object.getIndex() ? "auditget" : object.getIndex().toLowerCase();
            System.out.println("New Index: " + index);

            if(object.getCreatedAt() == null) {
                object.setCreatedAt(new Date());
            }

            IndexRequest indexRequest = new IndexRequest(index);
            indexRequest.id(String.valueOf(object.getUuid()));
            indexRequest.source(new ObjectMapper().writeValueAsString(object), XContentType.JSON);
            indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            System.out.println("response id: "+indexResponse.getId());
            System.out.println("response name: "+indexResponse.getResult().name());


        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
