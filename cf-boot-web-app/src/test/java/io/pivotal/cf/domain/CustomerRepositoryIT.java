package io.pivotal.cf.domain;

import static org.junit.Assert.assertTrue;
import io.pivotal.cf.Application;
import io.pivotal.cf.domain.Customer;
import io.pivotal.cf.domain.CustomerRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles("junit")
public class CustomerRepositoryIT {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Before
	public void before() {
		customerRepository.deleteAllInBatch();
	}
	
	@Test
	public void test() {
		//check empty
		assertTrue(customerRepository.count() <= 0);
		//build
		Customer customer = new Customer();
		//save
		customerRepository.save(customer);
		
		//check persisted
		assertTrue(customerRepository.count() > 0);
	}
	
}
