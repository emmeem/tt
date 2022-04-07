package com.junbin.mall.service;

import com.junbin.mall.domain.Picture;
import com.junbin.mall.domain.Product;
import com.junbin.mall.dto.AdminProductDto;
import com.junbin.mall.exception.ExceptionMessage;
import com.junbin.mall.exception.ProductIsNotExistException;
import com.junbin.mall.repository.ProductRepository;
import com.junbin.mall.utils.ConvertTool;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminProductService {
    private final ProductRepository productRepository;

    public AdminProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<AdminProductDto> getProducts(String companyName) {
        List<Product> products = productRepository.findAllByCompanyName(companyName);
        return ConvertTool.convertList(products, AdminProductDto.class);
    }

    @Transactional
    public AdminProductDto createProduct(AdminProductDto adminProductDto) {
        Product product = ConvertTool.convertObject(adminProductDto, Product.class);
        List<Picture> pictureList = adminProductDto.getPictures();
        List<Picture> pictures = pictureList.stream().peek(t-> t.setProduct(product)).collect(Collectors.toList());

        product.setPictures(pictures);
        Product newProduct = productRepository.save(product);
        return ConvertTool.convertObject(newProduct, AdminProductDto.class);
    }

    @Transactional
    public AdminProductDto update(Long id, AdminProductDto adminProductDto) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductIsNotExistException(ExceptionMessage.PRODUCT_NOT_EXIST));

        Product product = ConvertTool.convertObject(adminProductDto, Product.class);
        product.setId(id);
        Product newProduct = productRepository.save(product);
        return ConvertTool.convertObject(newProduct, AdminProductDto.class);
    }
}
