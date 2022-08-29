package com.example.elasticdbdemo.service;

import com.alibaba.fastjson.JSON;
import com.example.elasticdbdemo.model.Person;
import com.example.elasticdbdemo.util.CommonUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ElasticDbDataServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    public IndexResponse insertData() throws IOException {
        logger.info("START: ElasticDbDataServiceImpl.insertData()");
        IndexRequest indexRequest = CommonUtils.createIndexRequest("Sample Data","10",new Date());
        IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        return response;
    }

    public IndexResponse insertDataWithParams(String name, String age, Date dob) throws IOException {
        logger.info("START: ElasticDbDataServiceImpl.insertDataWithParams()");
        IndexRequest indexRequest = CommonUtils.createIndexRequest(name,age,dob);
        IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        return response;
    }

    public GetResponse fetchDataById(String id) throws IOException {
        logger.info("START: ElasticDbDataServiceImpl.fetchDataById()");
        GetRequest getRequest = new GetRequest("people");
        getRequest.id(id);
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        return getResponse;
    }

    public List<Person> fetchData() throws IOException {
        logger.info("START: ElasticDbDataServiceImpl.fetchData()");
        SearchResponse response = restHighLevelClient.search(new SearchRequest(), RequestOptions.DEFAULT);
        SearchHit[] searchHits = response.getHits().getHits();
        List<Person> results =
                Arrays.stream(searchHits)
                        .map(hit -> {
                            Person person = JSON.parseObject(hit.getSourceAsString(), Person.class);
                            person.setId(hit.getId());
                            return person;
                        })
                        .collect(Collectors.toList());

        return results;
    }

    public DeleteResponse deleteData(String id) throws IOException {
        logger.info("START : ElasticDbDataServiceImpl.deleteData()");
        DeleteRequest deleteRequest = new DeleteRequest("people");
        deleteRequest.id(id);
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        return deleteResponse;
    }

    public UpdateResponse updateData(String id,String name,String age,Date dob) throws IOException {
        logger.info("START : ElasticDbDataServiceImpl.updateData()");
        IndexRequest indexRequest = CommonUtils.createIndexRequest(name,age,dob);
        UpdateRequest request = new UpdateRequest("people",id);
        request.doc(indexRequest);
        UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        return updateResponse;
    }

    public List<String> getAllIds() throws IOException {
        logger.info("START : ElasticDbDataServiceImpl.getAllIds()");
        SearchResponse response = restHighLevelClient.search(new SearchRequest(), RequestOptions.DEFAULT);
        SearchHit[] searchHits = response.getHits().getHits();
        List<String> results =
                Arrays.stream(searchHits)
                        .map(hit -> hit.getId())
                        .collect(Collectors.toList());

        return results;
    }

}
