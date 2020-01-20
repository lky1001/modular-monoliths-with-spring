package com.tistory.lky1001.user.domain.users;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IRoleRepository extends CrudRepository<Role, Integer> {

    @Query("select * from role where name = :roleName")
    Role getByRoleName(@Param("roleName") String roleName);
}
