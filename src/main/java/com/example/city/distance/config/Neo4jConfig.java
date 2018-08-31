package com.example.city.distance.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableNeo4jRepositories("com.example.city.distance.repository")
public class Neo4jConfig {

    @Value("${spring.data.neo4j.uri}")
    private String databaseUrl;

    @Value("${spring.data.neo4j.username}")
    private String userName;

    @Value("${spring.data.neo4j.password}")
    private String password;

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        return new org.neo4j.ogm.config.Configuration.Builder()
                .uri(databaseUrl)
                .credentials(userName, password)
                .build();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(getConfiguration(), "com.example.city.distance.model");
    }
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }
}
