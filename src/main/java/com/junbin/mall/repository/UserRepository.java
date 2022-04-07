package com.junbin.mall.repository;

import com.junbin.mall.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByName(String name);

    List<User> findAll();

    @Query(value = "select count(id) from user", nativeQuery = true)
    Long countUserById();
}
