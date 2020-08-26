package com.registration.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.registration.RegistrationserviceApplication;
import com.registration.model.ApplicationUser;
import com.registration.repository.UserRepository;
import com.registration.serviceimpl.CustomerServiceImplementation;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegistrationserviceApplication.class)

class CustomerServiceTest {

@Mock
private UserRepository repo;

@InjectMocks
private CustomerServiceImplementation service;

@Test
public void testGetCustomer() {
ApplicationUser user = new ApplicationUser("gk1967", "Saru", "Mathi", "saru@gmail.com", null, "saru", "saru", null,
null);
when(repo.findByPanNo("gk1967")).thenReturn(user);
assertEquals(user, service.getUserByPan("gk1967"));
}

}

