package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.InsufficientFundsException;
import com.example.demo.service.BankingService;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private BankingService bser;
	
	@Autowired
	private TransactionService tser;
	
	
	@GetMapping(value = "/")
	public String testMethod()
	{
		return "In testMethod()";
	}
	
	@PostMapping(value = "/")
	public User registerUser(@Valid @RequestBody User user)
	{
		
		//return new ResponseEntity<User>(HttpStatus.CREATED);
		return bser.registerUser(user);
	}
	
	
	  @PutMapping(value = "/transfer/{sourceAccount}/{destinationAccount}/{amount}")
	  public ResponseEntity<?> transferFunds(@Valid @PathVariable long sourceAccount ,@PathVariable long destinationAccount,@PathVariable  long amount) throws AccountNotFoundException, InsufficientFundsException 
	  {
		/*
		 * if(sourceAccount==destinationAccount) { return
		 * "Both Source and Destination accounts cannot be the same"; } if(amount<=0) {
		 * return "Amount to be transferred should be greater than zero"; }
		 */
		  
	  
		  //return new ResponseEntity<User>(HttpStatus.CREATED); return
		 String status =   bser.transferFunds(sourceAccount,destinationAccount,amount);
		 return new ResponseEntity<Account>(HttpStatus.OK);
		 
	  }
	  
	  @GetMapping(value="/{month}")
	  public  ResponseEntity<List<Transaction>> getTransactionData(@PathVariable String month)
	  {
		  List txList = tser.getTransactionData(month);
		  if(txList.isEmpty())
			  return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_FOUND);
		  else
			  return new ResponseEntity<List<Transaction>>(txList,HttpStatus.OK);
	  }
	 
}
