package com.kabalport.sb3app.score.service;

import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
  public void process(String message) {
    System.out.println("processing ... " + message);
  }
}
