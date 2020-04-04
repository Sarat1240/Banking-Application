package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository tser;

	@Override
	public List<Transaction> getTransactionData(String month) {
		// TODO Auto-generated method stub
		return tser.findRecordsByMonth(month);
		
		 
	}

}
