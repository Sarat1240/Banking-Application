package com.example.demo.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.InsufficientFundsException;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.BankingRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.utility.RandomNumber;

@Service
@Transactional
public class BankingServiceImpl implements BankingService{
	
	@Autowired
	private BankingRepository brep;
	
	@Autowired
	private AccountRepository acrep;
	
	@Autowired
	private TransactionRepository trep;

	@Override
	public User registerUser(User user) 
	{
		// TODO Auto-generated method stub
		Account acc = new Account();
		acc.setAccountNumber(acc.getAccountNumber()+RandomNumber.random());
		acc.setAccountType("savings");
		acc.setOpeningBalance(5000);

		user.setAcc(acc);
		User u = brep.save(user);
		System.out.println(user);
	
		return u;
		
	}

	@Override
	public String transferFunds(long sourceAccountNumber, long destinationAccountNumber, long amount)throws AccountNotFoundException, InsufficientFundsException 
	{
		Optional<Account> srcAccount = acrep.findByAccountNumber(sourceAccountNumber);
				
		if (srcAccount.isPresent()) 
		{
			Account sac = srcAccount.get();
			Optional<Account> destAccount = acrep.findByAccountNumber(destinationAccountNumber);
			if (!destAccount.isPresent()) 
			{
				throw new AccountNotFoundException("Given destination account doesn't exist. Please provide valid details");
			} 
			else 
			{
				Account dac = destAccount.get();
				
				if(sac.getOpeningBalance()>=amount)
				{
					long remainingBal = sac.getOpeningBalance()-amount;
					sac.setOpeningBalance(remainingBal);
					acrep.save(sac);
					long newBal = dac.getOpeningBalance()+amount;
					dac.setOpeningBalance(newBal);
					acrep.save(dac);
					
					Transaction tx1 = new Transaction();
					tx1.setAccountNumber(sac.getAccountNumber());
					tx1.setAmount(amount);
					tx1.setTransactionType("Debit");
					tx1.setDate(new Date().toString());
					
					Transaction tx2 = new Transaction();
					tx2.setAccountNumber(dac.getAccountNumber());
					tx2.setAmount(amount);
					tx2.setTransactionType("Credit");
					tx2.setDate(new Date().toString());
					
					trep.save(tx1);
					trep.save(tx2);
					
				}
				else
				{
					throw new InsufficientFundsException("There is no sufficient funds to tranfer from given source account. Please try with a lesser amount.");
				}
			}
		}
		else 
		{
			throw new AccountNotFoundException("Given source account doesn't exist. Please provide valid details");
		}
		return "Success";
	}

}
