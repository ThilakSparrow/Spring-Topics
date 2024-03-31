package io.spring.security.owasp.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import io.spring.security.owasp.entity.Account;
import io.spring.security.owasp.entity.AccountDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


@Repository
public class AccountRepository {

//	private final DataSource dataSource;
    private final EntityManager em;

    public AccountRepository(EntityManager em) {
		super();
		this.em = em;
	}
    
    public List<AccountDTO> unsafeJpaFindAccountsByCustomerId(String customerId) {
        String jql = "from Account where customerId = '" + customerId + "'";
        TypedQuery<Account> q = em.createQuery(jql, Account.class);
        return q.getResultList()
            .stream()
            .map(a -> AccountDTO.builder()
                .accNumber(a.getAccNumber())
                .balance(a.getBalance())
                .branchId(a.getAccNumber())
                .customerId(a.getCustomerId())
                .build())
            .collect(Collectors.toList());
    }
    
    
    public List<AccountDTO> safeJpaFindAccountsByCustomerId(String customerId) {

        String jql = "from Account where customerId = :customerId";
        TypedQuery<Account> q = em.createQuery(jql, Account.class)
            .setParameter("customerId", customerId);

        return q.getResultList()
            .stream()
            .map(a -> AccountDTO.builder()
                .accNumber(a.getAccNumber())
                .balance(a.getBalance())
                .branchId(a.getAccNumber())
                .customerId(a.getCustomerId())
                .build())
            .collect(Collectors.toList());
    }

    
    
}
