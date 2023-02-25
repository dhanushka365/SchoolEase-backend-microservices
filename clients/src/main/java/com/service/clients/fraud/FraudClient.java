package com.service.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fraud")
public interface FraudClient {
    @GetMapping(path = "api/service/fraud-check/{customerEmail}")
    FraudCheckResponse isFraudster(
            @PathVariable("customerEmail")  String customerEmail);
    }
