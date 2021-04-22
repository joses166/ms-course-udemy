package com.udemy.hrworker.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.hrworker.entities.Worker;
import com.udemy.hrworker.repositories.WorkerRepository;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {
		
	private static final Logger log = LoggerFactory.getLogger(WorkerResource.class);
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private WorkerRepository workerRepository;
	
	@GetMapping
	public ResponseEntity<List<Worker>> findAll() {
		List<Worker> list = this.workerRepository.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable("id") Long id) {
		
		log.info("PORT = " + environment.getProperty("local.server.port"));
		
		Worker worker = this.workerRepository.findById(id).orElse(null);
		if (worker == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(worker);
	}
	
}
