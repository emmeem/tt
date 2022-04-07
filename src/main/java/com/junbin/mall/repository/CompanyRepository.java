package com.junbin.mall.repository;

import com.junbin.mall.domain.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    Optional<Company> findByName(String name);
}
