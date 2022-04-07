package com.junbin.mall.service;

import com.junbin.mall.domain.MallOrder;
import com.junbin.mall.dto.AdminOrderDto;
import com.junbin.mall.exception.ExceptionMessage;
import com.junbin.mall.exception.OrderIsNotExist;
import com.junbin.mall.repository.OrderRepository;
import com.junbin.mall.repository.UserRepository;
import com.junbin.mall.utils.ConvertTool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminOrderService {
    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    public AdminOrderService (OrderRepository orderRepository,
                               UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<AdminOrderDto> getOrders(String companyName) {
        List<MallOrder> orders = orderRepository.findAllByCompanyName(companyName);
        if(orders.isEmpty()) {
            throw new OrderIsNotExist(ExceptionMessage.ORDER_IS_NOT_EXIST);
        }
        return ConvertTool.convertList(orders, AdminOrderDto.class);
    }
}
