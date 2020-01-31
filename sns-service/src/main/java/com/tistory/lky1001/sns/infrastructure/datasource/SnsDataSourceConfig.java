package com.tistory.lky1001.sns.infrastructure.datasource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.jdbc.core.convert.BasicJdbcConverter;
import org.springframework.data.jdbc.core.convert.DataAccessStrategy;
import org.springframework.data.jdbc.core.convert.DefaultDataAccessStrategy;
import org.springframework.data.jdbc.core.convert.DefaultJdbcTypeFactory;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.core.convert.RelationResolver;
import org.springframework.data.jdbc.core.convert.SqlGeneratorSource;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJdbcRepositories(
        basePackages = "com.tistory.lky1001.sns",
        repositoryFactoryBeanClass = SnsJdbcRepositoryFactoryBean.class
)
public class SnsDataSourceConfig extends AbstractJdbcConfiguration {

    @Override
    @Bean("snsDataAccessStrategy")
    public DataAccessStrategy dataAccessStrategyBean(@Qualifier("snsOperations") NamedParameterJdbcOperations operations,
            @Qualifier("snsJdbcConverter") JdbcConverter jdbcConverter, RelationalMappingContext context) {
        return new DefaultDataAccessStrategy(new SqlGeneratorSource(context), context, jdbcConverter, operations);
    }

    @Override
    @Bean("snsJdbcConverter")
    public JdbcConverter jdbcConverter(RelationalMappingContext mappingContext, @Qualifier("snsOperations") NamedParameterJdbcOperations operations,
            @Qualifier("snsDataAccessStrategy") @Lazy RelationResolver relationResolver, JdbcCustomConversions conversions) {

        DefaultJdbcTypeFactory jdbcTypeFactory = new DefaultJdbcTypeFactory(operations.getJdbcOperations());

        return new BasicJdbcConverter(mappingContext, relationResolver, conversions, jdbcTypeFactory);
    }

    @Bean("snsOperations")
    public NamedParameterJdbcOperations snsOperations(@Qualifier("snsDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean("snsTransactionManager")
    public PlatformTransactionManager userTransactionManager(@Qualifier("snsDataSource") DataSource dataSource,
            ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        transactionManagerCustomizers.ifAvailable(customizers -> customizers.customize(transactionManager));

        return transactionManager;
    }

    @Override
    @Bean("snsJdbcAggregateTemplate")
    public JdbcAggregateTemplate jdbcAggregateTemplate(ApplicationContext applicationContext,
            RelationalMappingContext mappingContext, @Qualifier("snsJdbcConverter") JdbcConverter converter,
            @Qualifier("snsDataAccessStrategy") DataAccessStrategy dataAccessStrategy) {

        return new JdbcAggregateTemplate(applicationContext, mappingContext, converter, dataAccessStrategy);
    }
}
