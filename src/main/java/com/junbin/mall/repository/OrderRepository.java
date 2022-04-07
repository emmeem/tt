package com.junbin.mall.repository;

import com.junbin.mall.domain.MallOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<MallOrder, Long> {
    List<MallOrder> findAllByCompanyName(String companyName);
}
