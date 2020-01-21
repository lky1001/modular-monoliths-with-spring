package com.tistory.lky1001.user.domain.users;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional("userTransactionManager")
public interface IUserRepository extends CrudRepository<User, Long> {

    @Override
    <S extends User> S save(S entity);

    @Override
    Optional<User> findById(Long id);

    @Override
    long count();

    @Query("select * from user where email = :email")
    User getByEmail(@Param("email") String encodedEmail);

    @Query("select * from user where id = :userId")
    Optional<User> getById(@Param("userId") Long userId);
}
