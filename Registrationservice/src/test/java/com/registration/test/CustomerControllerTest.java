package com.registration.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.registration.RegistrationserviceApplication;
import com.registration.controller.CustomerController;
import com.registration.model.ApplicationUser;
import com.registration.serviceimpl.CustomerServiceImplementation;

@SpringBootTest(classes = RegistrationserviceApplication.class)
@RunWith(SpringRunner.class)
class CustomerControllerTest {

@Autowired
private ApplicationUser user;

@InjectMocks
CustomerController customer;

@MockBean
private CustomerServiceImplementation customerService;

@Test
public void addCustomerTest() throws Exception {
when(customerService.createUser(user))
.thenReturn(new ApplicationUser("gm1967", "Venu", "Avin", "venu@gmail.com", null, "saru", "saru", null, null));
assertEquals("Venu", customerService.createUser(user).getFirstName());

}
}

