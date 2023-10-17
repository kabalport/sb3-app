package com.kabalport.sb3app.score.controller;

import com.kabalport.sb3app.score.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RedisController {
  private final RedisService redisService;

  @GetMapping("/redis/set/{key}")
  public void setRedis(@PathVariable("key") String key, @RequestParam("value") String value) {
    redisService.set(key, value);
  }

  @GetMapping("/redis/get/{key}")
  public String getRedis(@PathVariable("key") String key) {
    return redisService.get(key);
  }
}
