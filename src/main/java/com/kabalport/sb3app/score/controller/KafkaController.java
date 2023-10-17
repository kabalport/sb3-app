package com.kabalport.sb3app.score.controller;

import com.kabalport.sb3app.score.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KafkaController {
  private final KafkaProducerService kafkaProduceService;

  @GetMapping("/kafka/producer/send")
  public void send(@RequestParam("topic") String topic, @RequestParam("value") String value) {
    kafkaProduceService.send(topic, value);
  }
}
