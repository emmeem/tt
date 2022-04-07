package com.junbin.mall.service;

import com.junbin.mall.domain.Coupon;
import com.junbin.mall.domain.User;
import com.junbin.mall.domain.UserCoupon;
import com.junbin.mall.dto.UserDto;
import com.junbin.mall.dto.UserLoginDto;
import com.junbin.mall.exception.*;
import com.junbin.mall.repository.CouponRepository;
import com.junbin.mall.repository.UserCouponRepository;
import com.junbin.mall.repository.UserRepository;
import com.junbin.mall.utils.ConvertTool;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final CouponRepository couponRepository;

    private final UserCouponRepository userCouponRepository;

    private UserCoupon userCoupon;

    public UserService (UserRepository userRepository, CouponRepository couponRepository,
                        UserCouponRepository userCouponRepository) {
        this.userRepository = userRepository;
        this.couponRepository = couponRepository;
        this.userCouponRepository = userCouponRepository;
    }

    public UserLoginDto login(UserLoginDto userLoginDto) {
       User user = userRepository.findUserByName(userLoginDto.getName())
                 .orElseThrow(() -> new UserIsNotExistException(ExceptionMessage.USER_NOT_EXIST));
       if(!user.getPassword().equals(user.getPassword())) {
           throw new UserPasswordIsNotCorrectException(ExceptionMessage.USER_PASSWORD_NOT_CORRECT);
       }
       return ConvertTool.convertObject(user,UserLoginDto.class);
    }


    private User setCoupon(User user, String companyName, String couponType) {
        Optional<Coupon> coupon = couponRepository.findCouponByCompanyNameAndType(companyName, couponType);

        userCoupon = userCoupon.builder()
                     .coupon(coupon.get())
                     .user(user)
                     .build();

        List<UserCoupon> userCoupons = new ArrayList<>();
        userCoupons.add(userCoupon);
        user.setUserCoupons(userCoupons);
        return user;
    }

    @Transactional
    public UserDto register(UserDto userDto) {
      if(userRepository.findUserByName(userDto.getName()).isPresent()) {
          throw new UserIsExistException(ExceptionMessage.USER_IS_EXIST);
      }

      User user = ConvertTool.convertObject(userDto,User.class);
      String companyName = "A";
      String couponType = "All";
      User userAfterSetCoupon = setCoupon(user, companyName, couponType);
      User newUser =userRepository.save(userAfterSetCoupon);

      return ConvertTool.convertObject(newUser, UserDto.class);
    }

    public UserDto getUser(String name) {
        User user = userRepository.findUserByName(name)
                .orElseThrow(() -> new UserIsNotExistException(ExceptionMessage.USER_NOT_EXIST));

        return ConvertTool.convertObject(user,UserDto.class);
    }
}
