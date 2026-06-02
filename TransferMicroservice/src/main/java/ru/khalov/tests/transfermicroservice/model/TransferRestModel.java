package ru.khalov.tests.transfermicroservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class TransferRestModel {

    private String senderId;
    private String recipientId;
    private BigDecimal amount;

}
