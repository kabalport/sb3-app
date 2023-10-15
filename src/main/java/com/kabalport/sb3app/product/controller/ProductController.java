package com.kabalport.sb3app.product.controller;

import com.kabalport.sb3app.product.model.Product;
import com.kabalport.sb3app.product.model.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {

  private final ProductRepository productRepository;

  @GetMapping("/products")
  public ResponseEntity<?> products() {
    List<Product> products = productRepository.findAll();
    return new ResponseEntity<>(products, HttpStatus.OK);
  }
}
