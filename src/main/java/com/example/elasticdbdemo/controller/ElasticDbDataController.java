package com.example.elasticdbdemo.controller;

import com.example.elasticdbdemo.model.Person;
import com.example.elasticdbdemo.service.ElasticDbDataService;
import com.example.elasticdbdemo.service.ElasticDbDataServiceImpl;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;

@RestController
public class ElasticDbDataController {

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    ElasticDbDataService service;

    @GetMapping(path = "/insertDefaultData")
    public ResponseEntity<DocWriteResponse.Result> insertDefaultData() throws IOException {
        logger.info("START : ElasticDbDataController.insert()");
        IndexResponse response = service.insertData();
        logger.info("Data was : "+response.getResult());
        return new ResponseEntity<>(response.getResult(), HttpStatus.OK);
    }

    @GetMapping(path = "/insertData")
    public ResponseEntity<DocWriteResponse.Result> insertDataWithParams(@RequestParam(required=true) String name,
                                  @RequestParam(required =true) String age,
                                  @RequestParam(required = true) @DateTimeFormat(pattern="MM-dd-yyyy") Date dob) throws IOException {
        logger.info("START : ElasticDbDataController.insertDataWithParams()");
        IndexResponse response = service.insertDataWithParams(name, age, dob);
        logger.info("Data was : "+response.getResult());
        return new ResponseEntity<>(response.getResult(), HttpStatus.OK);
    }

    @GetMapping(path = "/updateData")
    public ResponseEntity<DocWriteResponse.Result> updateData(@RequestParam(required=true) String id,
                                 @RequestParam(required=true) String name,
                                 @RequestParam(required =true) String age,
                                 @RequestParam(required = true) @DateTimeFormat(pattern="MM-dd-yyyy") Date dob) throws IOException {
        logger.info("START : ElasticDbDataController.insertDataWithParams()");
        UpdateResponse response = service.updateData(id, name, age, dob);
        logger.info("Data was : "+response.getResult());
        return new ResponseEntity<>(response.getResult(), HttpStatus.OK);
    }

    @GetMapping(path = "/fetchDataById")
    public ResponseEntity<GetResponse> fetchDataById(@RequestParam(required=true) String id) throws IOException {
        logger.info("START : ElasticDbDataController.fetchData()");
        GetResponse response = service.fetchDataById(id);
        logger.info("List size : "+response.getSourceAsString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/fetchData")
    public ResponseEntity<List<Person>> getSampleData() throws IOException {
        logger.info("START : ElasticDbDataController.fetchData()");
        List<Person> response = service.fetchData();
        logger.info("List size : "+response.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/deleteData")
    public ResponseEntity<DocWriteResponse.Result> deleteData(@RequestParam(required = true) String id) throws IOException {
        logger.info("START : ElasticDbDataController.deleteData()");
        DeleteResponse response = service.deleteData(id);
        logger.info("Data was : "+response.getResult());
        return new ResponseEntity<>(response.getResult(), HttpStatus.OK);
    }

    @GetMapping(path = "/deleteAllData")
    public ResponseEntity<String> deleteAllData() throws IOException {
        logger.info("START : ElasticDbDataController.deleteData()");
        service.deleteAllData();
        logger.info("All the Data was deleted");
        return new ResponseEntity<>("All the Data was deleted", HttpStatus.OK);
    }

    @GetMapping(path = "/getAllIds")
    public ResponseEntity<List<String>> getAllIds() throws IOException {
        logger.info("START : ElasticDbDataController.getAllIds()");
        List<String> response = service.getAllIds();
        logger.info("Data was : "+response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/sortData")
    public ResponseEntity<List<Person>> sortData(@RequestParam(required = true) String field,
                                                 @RequestParam(required = false) String sortOrder) throws IOException {
        logger.info("START : ElasticDbDataController.sortData()");
        List<Person> response = service.sortData(field,sortOrder);
        logger.info("List size : "+response.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
