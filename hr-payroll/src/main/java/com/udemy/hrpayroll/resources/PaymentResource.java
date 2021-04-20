package com.udemy.hrpayroll.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.hrpayroll.entities.Payment;
import com.udemy.hrpayroll.services.PaymentService;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping(value = "/{id}/days/{days}")
	public ResponseEntity<Payment> getPayment(
			@PathVariable("id") Long workerId,
			@PathVariable("days") Integer days
			) {
		Payment payment = this.paymentService.getPayment(workerId, days);
		return ResponseEntity.ok(payment);
	}
	
}
