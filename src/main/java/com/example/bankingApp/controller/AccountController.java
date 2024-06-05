package com.example.bankingApp.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingApp.model.Account;
import com.example.bankingApp.service.AccountService;
import com.example.bankingApp.service.KafkaProducer;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	@Autowired
    private AccountService accountService;
	
	@Autowired
	private KafkaProducer kafkaproducer;
	
	
	@PostMapping
	public Account createAccount(@RequestBody Account account) {
		
		return accountService.createAccount(account);
	}
	
	
	@GetMapping("/{id}")
	public Optional<Account> getAccount(@PathVariable Integer id) {
		return accountService.getAccount(id);
	}
	
	@PostMapping("/{id}/deposit")
	public Account deposit(@PathVariable Integer id, @RequestBody Map<String,Double> request) {
		Double amount=request.get("amount");
		return accountService.deposit(id, amount);
	}
	
	@PostMapping("/{id}/withdraw")
	public Account withdraw(@PathVariable Integer id, @RequestBody Map<String,Double> request) {
			Double amount=request.get("amount");
			return accountService.withdraw(id, amount);
		
	}
	
	@PostMapping("Transaction")
	public String sendTransaction(@RequestBody String transaction) {
		kafkaproducer.sendMessage(transaction);
		return "Transaction sent to Kafka!";
	}
	
	
	
	
	

}
