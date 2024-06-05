package com.example.bankingApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingApp.model.Account;
import com.example.bankingApp.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepo;
	
	public Account createAccount(Account account) {
		return accountRepo.save(account);
		
	}
	
	public Optional<Account> getAccount(Integer id){
		return accountRepo.findById(id);	
	}
	
	public Account deposit(Integer id, double amount) {
		Account account=getAccount(id).orElseThrow(()->new RuntimeException("Account not found"));
		account.setBalance(account.getBalance() + amount);
		return accountRepo.save(account);
	}
	
	public Account withdraw(Integer id, double amount) {
		Account account=getAccount(id).orElseThrow(()->new RuntimeException("Account not found"));
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance");
		}
		account.setBalance(account.getBalance()-amount);
		return accountRepo.save(account);
	}
	
	
	
	
	

}
