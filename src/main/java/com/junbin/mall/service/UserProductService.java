package com.junbin.mall.service;

import com.junbin.mall.domain.Product;
import com.junbin.mall.dto.UserProductDto;
import com.junbin.mall.repository.ProductRepository;
import com.junbin.mall.utils.ConvertTool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProductService {
    private final ProductRepository productRepository;

    public UserProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<UserProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        return ConvertTool.convertList(products, UserProductDto.class);
    }

    public List<UserProductDto> getProductsByCompanyName(String companyName) {
        List<Product> products = productRepository.findAllByCompanyName(companyName);

        return ConvertTool.convertList(products, UserProductDto.class);
    }
}
