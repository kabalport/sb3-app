package com.kabalport.sb3app;

import com.kabalport.sb3app.score.service.KafkaConsumerService;
import com.kabalport.sb3app.score.service.KafkaProducerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

@Order(0)
@DirtiesContext
public class KafkaConsumerApplicationTests extends IntegrationTest {
  @Autowired private KafkaProducerService kafkaProducerService;

  @MockBean private KafkaConsumerService kafkaConsumerService;

  @Test
  public void kafkaSendAndConsumeTest() {
    // given
    String topic = "test-topic";
    String expectValue = "expect-value";

    // when
    kafkaProducerService.send(topic, expectValue);

    // then
    var stringCaptor = ArgumentCaptor.forClass(String.class);
    Mockito.verify(kafkaConsumerService, Mockito.timeout(5000).times(1))
        .process(stringCaptor.capture());

    Assertions.assertEquals(expectValue, stringCaptor.getValue());
  }
}
