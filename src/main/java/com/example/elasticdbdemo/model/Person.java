package com.example.elasticdbdemo.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

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

    @JSONField(name = "age")
    private int age;

    @JSONField(name = "fullName")
    private String fullName;

    @JSONField(name = "dateOfBirth")
    private Date dateOfBirth;


}
