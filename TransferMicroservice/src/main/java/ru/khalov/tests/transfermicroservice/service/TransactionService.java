package ru.khalov.tests.transfermicroservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.web.ReactiveOffsetScrollPositionHandlerMethodArgumentResolver;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.khalov.tests.core.DepositRequestEvent;
import ru.khalov.tests.core.WithdrawRequestEvent;
import ru.khalov.tests.transfermicroservice.error.TransferServiceException;
import ru.khalov.tests.transfermicroservice.model.TransferRestModel;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TransactionService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final RestTemplate restTemplate;
    private final Environment environment;


    @Transactional
    public boolean transfer(TransferRestModel transferRestModel) {
        WithdrawRequestEvent withdraw = new WithdrawRequestEvent(
                transferRestModel.getSenderId(),
                transferRestModel.getRecipientId(),
                transferRestModel.getAmount()
        );

        DepositRequestEvent deposit = new DepositRequestEvent(
                transferRestModel.getSenderId(),
                transferRestModel.getRecipientId(),
                transferRestModel.getAmount()
        );

        try{
            kafkaTemplate.send(environment.getProperty("withdraw-topic", "withdraw-topic"), withdraw);
            log.info("Send event to withdraw topic");

            callRemoteMethod();

            kafkaTemplate.send(environment.getProperty("deposit-topic", "deposit-topic"), deposit);
            log.info("Send event to deposit topic");
        } catch (Exception e){
            log.error(e.getMessage());
            throw new TransferServiceException(e);
        }
        return true;
    }

    private ResponseEntity<String> callRemoteMethod() throws Exception{
        String reqUrl = "http://localhost:8090/response/200";
        ResponseEntity<String> response = restTemplate.exchange(reqUrl, HttpMethod.GET, null, String.class);
        if(response.getStatusCode().value() == HttpStatus.SERVICE_UNAVAILABLE.value())
            throw new Exception("deposit Microservice not aviable");

        if (response.getStatusCode().value() == HttpStatus.OK.value()){
            log.info("Received response from mock service: " + response.getBody());
        }
        return response;
    }

}
