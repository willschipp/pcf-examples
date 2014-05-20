package io.pivotal.cf.web;

import io.pivotal.cf.domain.Customer;
import io.pivotal.cf.domain.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * RESTful endpoint for the customer repository
 * 
 * @author wschipp
 *
 */
@RestController
@RequestMapping("/customers")
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Customer save(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
	
}
