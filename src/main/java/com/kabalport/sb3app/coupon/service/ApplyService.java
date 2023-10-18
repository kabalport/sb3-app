package com.kabalport.sb3app.coupon.service;

import com.kabalport.sb3app.coupon.entity.Coupon;
import com.kabalport.sb3app.coupon.repository.AppliedUserRepository;
import com.kabalport.sb3app.coupon.repository.CouponCountRepository;
import com.kabalport.sb3app.coupon.repository.CouponRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
  private final CouponRepository couponRepository;

  private final CouponCountRepository couponCountRepository;

  private final AppliedUserRepository appliedUserRepository;

  public ApplyService(
      CouponRepository couponRepository,
      CouponCountRepository couponCountRepository,
      AppliedUserRepository appliedUserRepository) {
    this.couponRepository = couponRepository;
    this.couponCountRepository = couponCountRepository;
    this.appliedUserRepository = appliedUserRepository;
  }
  @Transactional
  public void apply(Long userId) {
    Long apply = appliedUserRepository.add(userId);

    if (apply != 1) {
      return;
    }

    Long count = couponCountRepository.increment();

    if (count > 100) {
      return;
    }

    couponRepository.save(new Coupon(userId));
  }
}
