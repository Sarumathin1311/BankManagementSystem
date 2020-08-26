package com.banking.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.banking.model.Account;

@Component
@Repository
public interface BankAccountRepository extends JpaRepository<Account, Integer> 
{
	@Query(value = "SELECT * FROM account a WHERE a.panNo=:panNo", nativeQuery = true)
	List<Account> findByPanNo(String panNo);
}
