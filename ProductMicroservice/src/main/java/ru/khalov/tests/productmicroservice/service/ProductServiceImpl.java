package ru.khalov.tests.productmicroservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.khalov.tests.core.CreateProductEvent;
import ru.khalov.tests.productmicroservice.service.dto.CreateProductDto;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final KafkaTemplate<String, CreateProductEvent> kafkaTemplate;

    @Override
    public String createProduct(CreateProductDto createProductDto) throws ExecutionException, InterruptedException {
        //TODO send to postgres
        String prodId = UUID.randomUUID().toString();
        CreateProductEvent event = new CreateProductEvent(
                prodId,
                createProductDto.title(),
                createProductDto.price(),
                createProductDto.quantity()
        );

        //// Асинхронщина ////
        CompletableFuture<SendResult<String, CreateProductEvent>> future = kafkaTemplate.send("productTopic", prodId, event);
        future.whenComplete((result, exception) -> {
            if (exception != null) {
                log.error("Ошибка отправки сообщения в кафку: {}", exception.getMessage());
            } else {
                log.info("Всё хорошо, сообщение доставлено в кафку: {}", result.getRecordMetadata());
            }
        });


        //// Синхронщина ////
//        SendResult<String, CreateProductEvent> res = kafkaTemplate.send("productTopic", prodId, event).get();
//        log.info("Topic: {}", res.getRecordMetadata().topic());
//        log.info("Partition: {}", res.getRecordMetadata().partition());

        log.info("Return: {}", prodId);
        return prodId;
    }
}
