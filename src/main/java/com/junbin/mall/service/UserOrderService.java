package com.junbin.mall.service;

import com.junbin.mall.domain.MallOrder;
import com.junbin.mall.domain.OrderItem;
import com.junbin.mall.domain.User;
import com.junbin.mall.dto.UserOrderDto;
import com.junbin.mall.repository.OrderRepository;
import com.junbin.mall.repository.UserRepository;
import com.junbin.mall.utils.ConvertTool;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserOrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    public UserOrderService (OrderRepository orderRepository,
                             UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public UserOrderDto create(UserOrderDto userOrderDto) {
        MallOrder order = ConvertTool.convertObject(userOrderDto, MallOrder.class);

        Optional<User> user = userRepository.findById(userOrderDto.getUserId());
        order.setUser(user.get());

        List<OrderItem> orderItemList = userOrderDto.getOrderItems();
        List<OrderItem> orderItems = orderItemList.stream().
                peek(t-> t.setOrder(order)).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        MallOrder mallOrder = orderRepository.save(order);
        return ConvertTool.convertObject(mallOrder, UserOrderDto.class);
    }
}
