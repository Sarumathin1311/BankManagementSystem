package com.registration.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.registration.model.Account;
import com.registration.model.ApplicationUser;
import com.registration.model.MutualFund;
import com.registration.serviceimpl.CustomerServiceImplementation;



@RestController
@RequestMapping("/users")
public class CustomerController {

	@Autowired
	CustomerServiceImplementation customerService;
	@Autowired
	ApplicationUser users;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	Account account;

	Logger logger = LoggerFactory.getLogger(CustomerController.class);

//  FOR REGISTRATION OF USER USING REST TEMPLATE

	@PostMapping(value = "/sign-up", produces = "application/json")
	public ApplicationUser createUser(@Valid @RequestBody ApplicationUser user) {
		ApplicationUser details = customerService.createUser(user);
		return details;

	}

//ADD ACCOUNT DETAILS USING REST TEMPLATE

	@PostMapping(value = "/AddAccount", produces = "application/json")
	public Object createAccount(@Valid @RequestBody Account user) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
//		Account data = new Account();
//		HttpEntity<?> entity = new HttpEntity<Object>(data, headers);
		Object responseEntity = restTemplate.postForObject("http://localhost:6579/addAccount", user, Object.class,
				users.getPanNo()); 

		return responseEntity;
	}

//ADD MUTUAL FUND DETAILS USING REST TEMPLATE

	@PostMapping(value = "/AddMutualFund", produces = "application/json")
	public MutualFund createMutualFund(@Valid @RequestBody MutualFund fund) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		// MutualFund data = new MutualFund();
		// HttpEntity<?> entity = new HttpEntity<MutualFund>(data, headers);
		MutualFund responseEntity = restTemplate.postForObject("http://localhost:6580/addMutualFunds", fund,
				MutualFund.class, users.getPanNo());

		return responseEntity;
	}

//. GETTING ACCOUNT BY PAN NO WITH HYSTRIX CIRCUIT BREAKER

	@GetMapping(value = "/getBypanNo/{panNo}", produces = "application/json")
	@HystrixCommand(fallbackMethod = "getFallbackCatalog", threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "99") })
	public ResponseEntity<String> getUserById(@PathVariable("panNo") String panNo) {
		HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(50000000);
		final String Url = "http://localhost:6579/get/{panNo}";

		// used rest template

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> user = /* (List<Account>) */ restTemplate.getForEntity(Url, String.class, panNo);
		return user;
	}

	// FALLBACK METHOD GETFALLBACKCATALOG

	public ResponseEntity<String> getFallbackCatalog(@PathVariable("panNo") String panNo) {

		return (new ResponseEntity<String>("Something went wrong!!! Please try After Sometime", HttpStatus.BAD_REQUEST));
	}

//FOR GETTING INVESTMENT DETAILS

	@GetMapping(value = "/getInvestmentDetails/{panNo}/{fundId}", produces = "application/json")
//	@HystrixCommand(fallbackMethod = "getFallbackCatalogForInvestmentDetails", threadPoolProperties = {
//			@HystrixProperty(name = "coreSize", value = "99") })

	public String getTransactionDetails(@PathVariable("panNo") String panNo, @PathVariable("fundId") Integer fundId) {
//		HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(50000000);
		final String Url = "http://localhost:6580/getInvestmentDetails/{panNo}/{fundId}";
		RestTemplate restTemplate = new RestTemplate();
		String user = restTemplate.getForObject(Url, String.class, panNo, fundId);
		return user;
	}
	/*
	 * // FALLBACK METHOD
	 * 
	 * public String getFallbackCatalogForInvestmentDetails(@PathVariable("panNo")
	 * String panNo,
	 * 
	 * @PathVariable("fundId") String fundId) {
	 * 
	 * return new String("Something went wrong!!! Please try After Sometime"); }
	 * 
	 */

// FOR GETTING ALL ACCOUNT DETAILS

	@GetMapping(value = "/getAllAccounts", produces = "application/json")
	@HystrixCommand(fallbackMethod = "getFallbackCatalogForAccounts", threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "99") })
	public ResponseEntity<String> getAllAccountDetails() {
		HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(50000000);
		final String Url = "http://localhost:6579/getAll";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> user = restTemplate.getForEntity(Url, String.class);
		return user;
	}

	// FALLBACK METHOD

	public ResponseEntity<String> getFallbackCatalogForAccounts() {

		return (new ResponseEntity<String>("Something went wrong!!! Please try After Sometime", HttpStatus.OK));
	}

//-------------------------------------------------------------------------------------------

 @GetMapping(value = "/getUser/{panNo}", produces = "application/json")

	public ApplicationUser getUser(@PathVariable("panNo") String panNo) {

		ApplicationUser user = customerService.getUserByPan(panNo);
		return user;
	}

}