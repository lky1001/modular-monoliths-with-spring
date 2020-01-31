package com.tistory.lky1001.sns.domain.users;

import com.tistory.lky1001.sns.infrastructure.domain.users.SnsUserRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("snsUserRepository")
public interface IUserRepository extends CrudRepository<User, Long>, SnsUserRepository {

}
