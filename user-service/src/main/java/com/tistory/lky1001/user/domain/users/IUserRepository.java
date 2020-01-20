package com.tistory.lky1001.user.domain.users;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IUserRepository extends CrudRepository<User, Long> {

    @Query("select * from user where email = :email")
    User getByEmail(@Param("email") String encodedEmail);
}
