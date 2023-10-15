package com.kabalport.sb3app.score.config;

import com.kabalport.sb3app.product.model.Product;
import com.kabalport.sb3app.product.model.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class InitConfig {
  @Profile({"dev"}) // dev 동작
  @Bean
  CommandLineRunner init(ProductRepository productRepository) { // main이 실행될 때 반드시 한번 실행됨
    return args -> {
      Product product1 = new Product(null, "바나나", 1000);
      Product product2 = new Product(null, "딸기", 2000);
      Product product3 = new Product(null, "참외", 3000);
      productRepository.save(product1);
      productRepository.save(product2);
      productRepository.save(product3);
    };
  }
}
