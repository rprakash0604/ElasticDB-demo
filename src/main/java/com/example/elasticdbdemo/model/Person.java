package com.example.elasticdbdemo.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Getter
@Setter
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @JSONField(name = "id")
    private String id;

    @Field(name = "age",type = FieldType.Keyword, fielddata = true)
    private Long age;

    @JSONField(name = "fullName")
    private String fullName;

    @Field(name = "dob", type = FieldType.Keyword, fielddata = true)
    private Long dob;

    @JSONField(name = "dateOfBirth")
    private Date dateOfBirth;


}
