package com.example.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Integer>{

	Customer findByUsernameAndPassword(String username, String password);

	Customer findByCustomerid(int id);

	Optional<Customer> findByUsername(String username);

	Customer findById(int id);
	boolean existsByUsername(String username);

}
