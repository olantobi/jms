package com.liferon.jms.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class OrderDto {
    @NotBlank
    private String customerId;
    @NotBlank
    private String productId;
    @NotNull
    private String amount;
}
