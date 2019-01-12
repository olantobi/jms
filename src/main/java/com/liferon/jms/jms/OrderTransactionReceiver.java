package com.liferon.jms.jms;

import com.liferon.jms.document.OrderTransaction;
import com.liferon.jms.repository.OrderTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderTransactionReceiver {

    private final OrderTransactionRepository transactionRepository;

    private int counter = 0;

    @JmsListener(destination = "orderTransactionQueue")
    public void receiveTransaction(OrderTransaction order) {
        log.info("<"+ ++counter +"> Received order "+order);

        transactionRepository.save(order);
    }

}
