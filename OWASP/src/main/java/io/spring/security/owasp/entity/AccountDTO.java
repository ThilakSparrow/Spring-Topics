package io.spring.security.owasp.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTO {
    
    private String customerId;
    private String accNumber;
    private String branchId;
    private double balance;
}