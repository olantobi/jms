package com.liferon.jms.document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Setter
@Getter
@ToString
public class OrderTransaction {
    @Id
    private String id;
    private String customerId;
    private String productId;
    private BigDecimal amount;
    private LocalDateTime transDate;
}
