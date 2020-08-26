package com.banking.serviceimpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.banking.model.Account;
import com.banking.repository.ApplicationRepository;
import com.banking.repository.BankAccountRepository;

@Component
@Service
public class BankAccountService 
{
	@Autowired
	BankAccountRepository repository;
	
	@Autowired
	ApplicationRepository dao;
	
	public List<Account> getDetail(String panNo) 
	{
		return repository.findByPanNo(panNo);
	}
	
	public Account addAccountDetails(Account account)
	{
		return repository.save(account);
	}
	
	public List<Account> getAccount() 
	{
		return (List<Account>) repository.findAll();
	}

}

