package com.example.elasticdbdemo.util;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.Date;

public class CommonUtils {

    public static IndexRequest createIndexRequest(String name, String age, Date dob) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("fullName", name)
                .field("dateOfBirth", dob)
                .field("age", age)
                .endObject();

        IndexRequest indexRequest = new IndexRequest("people");
        indexRequest.source(builder);
        return indexRequest;
    }
}
