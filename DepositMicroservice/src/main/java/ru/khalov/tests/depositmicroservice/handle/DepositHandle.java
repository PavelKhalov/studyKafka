package ru.khalov.tests.depositmicroservice.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.khalov.tests.core.DepositRequestEvent;

@Component
@Slf4j
@KafkaListener(topics = "deposit-topic", containerFactory = "containerFactory")
public class DepositHandle {

    @KafkaHandler
    public void handle(@Payload DepositRequestEvent depositRequestEvent){
        log.info("Received event about deposit, amount: {}", depositRequestEvent.getAmount());
    }

}
