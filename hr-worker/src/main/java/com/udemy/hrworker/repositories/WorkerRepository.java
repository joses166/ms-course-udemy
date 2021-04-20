package com.udemy.hrworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.hrworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
