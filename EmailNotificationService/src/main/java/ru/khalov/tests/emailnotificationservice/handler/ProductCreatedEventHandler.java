package ru.khalov.tests.emailnotificationservice.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.khalov.tests.core.CreateProductEvent;
import ru.khalov.tests.emailnotificationservice.exceptoion.NonRetryableException;
import ru.khalov.tests.emailnotificationservice.exceptoion.RetryableException;


@Slf4j
@Component
@KafkaListener (topics = "productTopic", containerFactory = "containerFactory")
public class ProductCreatedEventHandler {

    private RestTemplate restTemplate;

    public ProductCreatedEventHandler (RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @KafkaHandler
    public void handle(CreateProductEvent createEvent) {

//        log.info("Receive event: {}", createEvent.getTitle());
//        log.info("Price: {}", createEvent.getPrice());

        String url = "http://localhost:8090/response/200";
        try {
            ResponseEntity<String> response =  restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            if (response.getStatusCode().value() == HttpStatus.OK.value()){
                log.info("Receive event: {}", createEvent.getTitle());
                log.info("Price: {}", createEvent.getPrice());
                log.info("response body: {}", response.getBody());
            }
        } catch (ResourceAccessException e){
            log.error(e.getMessage());
            throw new RetryableException("tyr retry");
        } catch (HttpServerErrorException e){
            log.error(e.getMessage());
            throw new NonRetryableException("don't try retry");
        } catch (Exception e){
            log.error(e.getMessage());
            throw new NonRetryableException("don't try retry");
        }
    }


}