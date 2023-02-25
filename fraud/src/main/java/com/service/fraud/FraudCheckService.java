package com.service.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {


    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(String customerEmail) {

        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerEmail(customerEmail)
                        .isFraudster(false)
                        .createAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
