package com.example.elasticdbdemo.service;

import com.example.elasticdbdemo.model.Person;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface ElasticDbDataService {

     IndexResponse insertData() throws IOException;
     IndexResponse insertDataWithParams(String name, String age, Date dob) throws IOException;
     GetResponse fetchDataById(String id) throws IOException;
     List<Person> fetchData() throws IOException;
     DeleteResponse deleteData(String id) throws IOException;
     void deleteAllData() throws IOException;
     UpdateResponse updateData(String id, String name, String age, Date dob) throws IOException;
     List<String> getAllIds() throws IOException;
     List<Person> sortData(String fieldName, String sortOrder) throws IOException;
}
