package com.banking.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.Account;

public interface ApplicationRepository extends JpaRepository<Account,Integer>
{

}
