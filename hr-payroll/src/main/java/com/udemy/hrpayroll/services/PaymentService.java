package com.udemy.hrpayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.hrpayroll.entities.Payment;
import com.udemy.hrpayroll.entities.Worker;
import com.udemy.hrpayroll.feignclients.WorkerFeignClient;

@Service
public class PaymentService {

	@Autowired
	private WorkerFeignClient workerFeignClient;
	
	public Payment getPayment(long workerId, int days) {
		// url, classe, parametros
		Worker worker = workerFeignClient.findById(workerId).getBody();
		
		if (worker == null) return null;
		
		return new Payment(
			worker.getName(), 
			worker.getDailyIncome(), 
			days
		);
	}
	
}
