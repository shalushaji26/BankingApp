package com.example.bankingApp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.bankingApp.model.Account;
import com.example.bankingApp.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class KafkaConsumer {
	@Autowired
	private AccountRepository accountRepo;
	
	@KafkaListener(topics = "bank-transactions", groupId = "group_id")
	public void consume(String message) {
		
		//System.out.println("Consumed message: " + message);
		ObjectMapper objectMapper= new ObjectMapper();
		try {
            // Assuming the message is a JSON string that matches the Transaction entity
            Account account = objectMapper.readValue(message, Account.class);
            accountRepo.save(account);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
	}

}
