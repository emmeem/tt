package com.junbin.mall.repository;


import com.junbin.mall.domain.Cart;
import com.junbin.mall.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findByProductAndUserId(Product product, Long userId);

    List<Cart> findAllByUserId(Long userId);
}

