package com.tistory.lky1001.sns.infrastructure.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SnsDataSourceProperty {

    @Bean("snsDataSource")
    @Qualifier("snsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.sns")
    public DataSource snsDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
