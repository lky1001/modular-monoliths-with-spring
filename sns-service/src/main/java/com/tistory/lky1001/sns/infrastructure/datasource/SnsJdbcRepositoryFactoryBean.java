package com.tistory.lky1001.sns.infrastructure.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.core.convert.DataAccessStrategy;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.repository.support.JdbcRepositoryFactoryBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

public class SnsJdbcRepositoryFactoryBean extends JdbcRepositoryFactoryBean {
    /**
     * Creates a new {@link JdbcRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    protected SnsJdbcRepositoryFactoryBean(Class repositoryInterface) {
        super(repositoryInterface);
        this.setTransactionManager("snsTransactionManager");
    }

    @Autowired
    @Override
    public void setConverter(@Qualifier("snsJdbcConverter") JdbcConverter converter) {
        super.setConverter(converter);
    }

    @Autowired
    @Override
    public void setJdbcOperations(@Qualifier("snsOperations") NamedParameterJdbcOperations operations) {
        super.setJdbcOperations(operations);
    }

    @Autowired
    @Override
    public void setDataAccessStrategy(@Qualifier("snsDataAccessStrategy") DataAccessStrategy dataAccessStrategy) {
        super.setDataAccessStrategy(dataAccessStrategy);
    }
}
