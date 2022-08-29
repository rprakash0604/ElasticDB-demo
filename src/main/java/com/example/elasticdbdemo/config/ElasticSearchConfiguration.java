package com.example.elasticdbdemo.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

@Configuration
public class ElasticSearchConfiguration {

    @Value("${elastic-repo.url}")
    private String url;


    @Bean( destroyMethod = "close" )
    public RestHighLevelClient restHighLevelClient() throws Exception {

        ClientConfiguration clientConfiguration =
                ClientConfiguration.builder().connectedTo(url).build();
        RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
        return client;
    }
}
