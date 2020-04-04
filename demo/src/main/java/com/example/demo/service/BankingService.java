package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.InsufficientFundsException;

public interface BankingService {

	public User registerUser(User user);
	public String transferFunds(long from,long to,long amount) throws AccountNotFoundException, InsufficientFundsException;

}
