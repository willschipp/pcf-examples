package io.pivotal.cf.web;

import io.pivotal.cf.domain.Customer;
import io.pivotal.cf.domain.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}
	
}
