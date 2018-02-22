package com.rits.springelasticsearch.config;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;
import java.io.IOException;

@Configuration
@EnableJpaRepositories(basePackages = "com.rits.springelasticsearch.jparepository")
@EnableElasticsearchRepositories(basePackages = "com.rits.springelasticsearch.repository")
public class ElasticConfiguration {

    @Bean
    NodeBuilder nodeBuilder(){
        return new NodeBuilder();
    }

    @Bean
    ElasticsearchOperations elasticsearchTemplate(){
        File tempDir = null;
        try {
            tempDir= File.createTempFile("elastic",Long.toString(System.nanoTime()));
        }catch (IOException e){
            e.printStackTrace();
        }
        Settings.Builder elasticSearchBuilder = Settings.settingsBuilder()
                .put("http.enabled","true")
                .put("index.number_of_shards","1")
                .put("path.data",new File(tempDir,"data").getAbsolutePath())
                .put("path.logs",new File(tempDir,"logs").getAbsolutePath())
                .put("path.work",new File(tempDir,"work").getAbsolutePath())
                .put("path.home",tempDir);

        return new ElasticsearchTemplate(nodeBuilder().local(true)
        .settings(elasticSearchBuilder.build())
        .node()
        .client());

    }
}
