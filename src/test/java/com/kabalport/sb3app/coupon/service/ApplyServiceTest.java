package com.kabalport.sb3app.coupon.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.kabalport.sb3app.coupon.repository.AppliedUserRepository;
import com.kabalport.sb3app.coupon.repository.CouponCountRepository;
import com.kabalport.sb3app.coupon.repository.CouponRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class ApplyServiceTest {

  private static final Logger logger = LoggerFactory.getLogger(ApplyServiceTest.class);

  @Autowired private StringRedisTemplate redisTemplate;

  @Autowired private ApplyService applyService;

  @Autowired private AppliedUserRepository appliedUserRepository;

  @Autowired private CouponRepository couponRepository;

  @Autowired private CouponCountRepository couponCountRepository;

  @BeforeEach
  public void clearData() {
    //    couponRepository.deleteAll();
    redisTemplate.execute(
        (RedisCallback<Object>)
            connection -> {
              connection.flushDb();
              return null;
            });
  }

  @AfterEach
  public void tearDown() {
    redisTemplate.execute(
        (RedisCallback<Object>)
            connection -> {
              connection.flushDb();
              return null;
            });
    couponRepository.deleteAll();
  }

  //  @Test
  //  public void 한번만응모() {
  //    logger.info("테스트 시작: 한번만응모");
  //    applyService.apply(1L);
  //
  //    long count = couponRepository.count();
  //
  //    assertThat(count).isEqualTo(1);
  //    logger.info("테스트 종료: 한번만응모");
  //  }
  //
  //  @Test
  //  public void 여러명응모() throws InterruptedException {
  //    logger.info("테스트 시작: 여러명응모");
  //    int threadCount = 1000;
  //    ExecutorService executorService = Executors.newFixedThreadPool(32);
  //    CountDownLatch latch = new CountDownLatch(threadCount);
  //
  //    for (int i = 0; i < threadCount; i++) {
  //      long userId = i;
  //      executorService.submit(
  //              () -> {
  //                try {
  //                  applyService.apply(userId);
  //                } finally {
  //                  latch.countDown();
  //                }
  //              });
  //    }
  //    latch.await();
  //
  //    long count = couponRepository.count();
  //
  //    assertThat(count).isEqualTo(100);  // 이 부분을 수정했습니다.
  //    logger.info("테스트 종료: 여러명응모");
  //  }

  @Test
  public void 한명당_한개의쿠폰만_발급() throws InterruptedException {
    logger.info("테스트 시작: 한명당_한개의쿠폰만_발급");
    int threadCount = 1000;
    ExecutorService executorService = Executors.newFixedThreadPool(32);
    CountDownLatch latch = new CountDownLatch(threadCount);

    for (int i = 0; i < threadCount; i++) {
      executorService.submit(
          () -> {
            try {
              applyService.apply(11111L);
            } finally {
              latch.countDown();
            }
          });
    }
    latch.await();

    long count = couponRepository.count();

    assertThat(count).isEqualTo(1);
    logger.info("테스트 종료: 한명당_한개의쿠폰만_발급");
  }
}
