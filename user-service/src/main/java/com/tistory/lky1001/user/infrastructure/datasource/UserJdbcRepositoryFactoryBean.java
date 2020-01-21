package com.tistory.lky1001.user.infrastructure.datasource;

import org.springframework.data.jdbc.repository.support.JdbcRepositoryFactoryBean;

public class UserJdbcRepositoryFactoryBean extends JdbcRepositoryFactoryBean {
    /**
     * Creates a new {@link JdbcRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    protected UserJdbcRepositoryFactoryBean(Class repositoryInterface) {
        super(repositoryInterface);
        setTransactionManager("userTransactionManager");
    }
}
