package com.liferon.jms.controller;

import com.liferon.jms.document.OrderTransaction;
import com.liferon.jms.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RequestMapping("/api/order")
@RestController
@RequiredArgsConstructor
public class OrderTransactionController {

    private final JmsTemplate jmsTemplate;
    private final ModelMapper modelMapper;

    public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderDto orderDto, BindingResult result) {

        if (result.hasFieldErrors()) {
            String errors = result.getFieldErrors().stream()
                    .map(p -> p.getDefaultMessage()).collect(Collectors.joining("\n"));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        OrderTransaction orderTrans = modelMapper.map(orderDto, OrderTransaction.class);

        orderTrans.setTransDate(LocalDateTime.now());

        jmsTemplate.setMessageTimestampEnabled(true);
        jmsTemplate.setMessageIdEnabled(true);
        jmsTemplate.convertAndSend(orderDto);


        return ResponseEntity.ok("Order place successfully");
    }
}
