package io.spring.security.owasp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Accounts")
@Data
@NoArgsConstructor
public class Account {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
    private String customerId;
    private String accNumber;
    private String branchId;
    private double balance;
    
	public Account(String customerId, String accNumber, String branchId, double balance) {
		super();
		this.customerId = customerId;
		this.accNumber = accNumber;
		this.branchId = branchId;
		this.balance = balance;
	}

    
    
    
}



