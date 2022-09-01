package com.example.elasticdbdemo.util;

import com.alibaba.fastjson.JSON;
import com.example.elasticdbdemo.model.Person;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CommonUtils {

    public static IndexRequest createIndexRequest(String name, String age, Date dob) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("fullName", name)
                .field("dob", dob.getTime())
                .field("age",age)
                .endObject();

        IndexRequest indexRequest = new IndexRequest("people");
        indexRequest.source(builder);
        return indexRequest;
    }

    public static List<Person> parseJsonObjectToPersonObject(SearchHit[] searchHits){
        return Arrays.stream(searchHits)
                        .map(hit -> {
                            Person person = JSON.parseObject(hit.getSourceAsString(), Person.class);
                            person.setId(hit.getId());
                            person.setDateOfBirth(CommonUtils.converyLongtoDate(person.getDob()));
                            return person;
                        })
                        .collect(Collectors.toList());
    }

    public static Date converyLongtoDate(Long longDate){
        return new Date(longDate);
    }
}
