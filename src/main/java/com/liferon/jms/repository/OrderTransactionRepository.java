package com.liferon.jms.repository;

import com.liferon.jms.document.OrderTransaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OrderTransactionRepository extends ReactiveMongoRepository<OrderTransaction, String> {
}
