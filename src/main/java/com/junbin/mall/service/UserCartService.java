package com.junbin.mall.service;

import com.junbin.mall.domain.Cart;
import com.junbin.mall.domain.Product;
import com.junbin.mall.dto.CartDto;
import com.junbin.mall.dto.CartToFrontDto;
import com.junbin.mall.exception.CartInfoIsNotExistException;
import com.junbin.mall.exception.ExceptionMessage;
import com.junbin.mall.exception.ProductIsNotExistException;
import com.junbin.mall.repository.CartRepository;
import com.junbin.mall.repository.ProductRepository;
import com.junbin.mall.utils.ConvertTool;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserCartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    public UserCartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public CartToFrontDto addProductsToCart(CartDto cartDto) {
        Optional<Product> product = productRepository.findById(cartDto.getProductId());
        if(!product.isPresent()) {
            throw new ProductIsNotExistException(ExceptionMessage.PRODUCT_NOT_EXIST);
        }
        Cart cart = cartRepository.findByProductAndUserId(product.get(),cartDto.getUserId());
        if(cart != null){
            cart.setCount(cart.getCount()+cartDto.getCount());
        }else {
            cart = Cart.builder()
                    .userId(cartDto.getUserId())
                    .product(product.get())
                    .count(cartDto.getCount())
                    .build();
        }
        Cart newCart = cartRepository.save(cart);
        return ConvertTool.convertObject(newCart, CartToFrontDto.class);
    }

    public List<CartToFrontDto> getCartInfo(Long userId) {
        List<Cart> cart = cartRepository.findAllByUserId(userId);
        if(cart.isEmpty()) {
            throw new CartInfoIsNotExistException(ExceptionMessage.CART_IS_NOT_EXIST);
        }
        return ConvertTool.convertList(cart, CartToFrontDto.class);
    }
}
