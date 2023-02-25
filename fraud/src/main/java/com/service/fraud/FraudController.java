package com.service.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/services/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {
    private final FraudCheckService fraudCheckService;


    @GetMapping(path = "{customerEmail}")
    public FraudCheckResponse isFraudster(
        @PathVariable("customerEmail")  String customerEmail){
    boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerEmail);
    log.info("fraud check request for customer { }", customerEmail);
    return new FraudCheckResponse(isFraudulentCustomer);

    }
}
