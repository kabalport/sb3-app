package com.kabalport.sb3app.coupon.repository;

import com.kabalport.sb3app.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {}
