package com.junbin.mall.repository;

import com.junbin.mall.domain.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    Optional<Admin> findAdminByName(String name);
}
