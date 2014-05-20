package io.pivotal.cf.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import io.pivotal.cf.Application;
import io.pivotal.cf.domain.Customer;
import io.pivotal.cf.domain.CustomerRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles("junit")
public class CustomerRepositoryIT {

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@After
	public void after() {
		customerRepository.deleteAllInBatch();
	}
	
	@Test
	public void testSave() throws Exception {
		String customer;
		//build
		Customer customerObject = new Customer();
		customerObject.setFirstName("John");
		customerObject.setLastName("Doe");
		//convert
		customer = objectMapper.writeValueAsString(customerObject);
		//check the repository
		assertTrue(customerRepository.count() <= 0);
		//make the request
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/customers").contentType(MediaType.APPLICATION_JSON).content(customer))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();
		//convert back
		Customer responseCustomer = objectMapper.readValue(result.getResponse().getContentAsByteArray(), Customer.class);
		//check
		assertNotNull(responseCustomer.getId());
		assertEquals(responseCustomer.getFirstName(),customerObject.getFirstName());
		//check in the database
		assertTrue(customerRepository.count() > 0);
		
	}
	
}
