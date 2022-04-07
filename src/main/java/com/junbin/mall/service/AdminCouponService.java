package com.junbin.mall.service;

import com.junbin.mall.domain.Coupon;
import com.junbin.mall.domain.UserCoupon;
import com.junbin.mall.dto.AdminCouponDto;
import com.junbin.mall.exception.CouponIsExistException;
import com.junbin.mall.exception.CouponIsNotExistException;
import com.junbin.mall.exception.ExceptionMessage;
import com.junbin.mall.repository.CouponRepository;
import com.junbin.mall.repository.UserCouponRepository;
import com.junbin.mall.utils.ConvertTool;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AdminCouponService {
    private final CouponRepository couponRepository;

    private final UserCouponRepository userCouponRepository;

    public AdminCouponService(CouponRepository couponRepository, UserCouponRepository userCouponRepository) {

        this.couponRepository = couponRepository;
        this.userCouponRepository = userCouponRepository;
    }

    @Transactional
    public AdminCouponDto createCoupon(AdminCouponDto adminCouponDto) {
        Optional<Coupon> coupon = couponRepository.findCouponByName(adminCouponDto.getName());
        if(coupon.isPresent()) {
            if(coupon.get().getCompanyName().equals(adminCouponDto.getName()))
            throw new CouponIsExistException(ExceptionMessage.COUPON_IS_EXIST);
        }
        Coupon newCoupon = couponRepository.save(ConvertTool.convertObject(adminCouponDto, Coupon.class));
        return ConvertTool.convertObject(newCoupon, AdminCouponDto.class);
    }

    @Transactional
    public void deleteCoupon(Long id) {
        Optional<Coupon> coupon = couponRepository.findById(id);
        if(!coupon.isPresent()) {
            throw new CouponIsNotExistException(ExceptionMessage.COUPON_IS_NOT_EXIST);
        }
        List<UserCoupon> userCoupons = userCouponRepository.findAllByCouponId(id);
        if(!userCoupons.isEmpty()) {
            userCouponRepository.deleteByCouponId(id);
        }
        couponRepository.deleteById(id);
    }

    public List<AdminCouponDto> getCoupons(String companyName) {
        List<Coupon> coupons = couponRepository.findCouponByCompanyName(companyName);

        if(coupons.isEmpty()) {
            throw new CouponIsNotExistException(ExceptionMessage.COUPON_IS_NOT_EXIST);
        }
        return ConvertTool.convertList(coupons, AdminCouponDto.class);
    }

    @Transactional
    public void deleteUserCoupon(Long id) {
        Optional<UserCoupon> userCoupon = userCouponRepository.findById(id);
        if(!userCoupon.isPresent()) {
            throw new CouponIsNotExistException(ExceptionMessage.COUPON_IS_NOT_EXIST);
        }
        userCouponRepository.deleteById(id);
    }
}
