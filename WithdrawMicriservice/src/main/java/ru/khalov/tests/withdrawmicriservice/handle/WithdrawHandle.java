package ru.khalov.tests.withdrawmicriservice.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.khalov.tests.core.WithdrawRequestEvent;

@Component
@Slf4j
@KafkaListener(topics = "withdraw-topic", containerFactory = "containerFactory")
public class WithdrawHandle {

    @KafkaHandler
    public void handle(@Payload WithdrawRequestEvent withdrawRequestEvent){
        log.info("Received message about withdraw, amount = {}", withdrawRequestEvent.getAmount());
    }

}
