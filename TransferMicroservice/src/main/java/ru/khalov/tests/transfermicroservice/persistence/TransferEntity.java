package ru.khalov.tests.transfermicroservice.persistence;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "transfers")
public class TransferEntity implements Serializable {

    private static final long serialVersionUID = -3543543534543232323L;

    @Id
    @Column(name = "transferid", nullable = false)
    private String transferId;

    @Column(name = "senderid", nullable = false)
    private String senderId;

    @Column(name = "recipientid", nullable = false)
    private String recipientId;

    @Column(nullable = false)
    private BigDecimal amount;

    public TransferEntity() {
    }

    public TransferEntity(String transferId, String senderId, String recipientId, BigDecimal amount) {
        this.transferId = transferId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
