package com.example.bankingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingApp.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	

}
