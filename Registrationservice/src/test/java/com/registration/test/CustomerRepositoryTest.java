package com.registration.test;


import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.registration.RegistrationserviceApplication;
import com.registration.model.ApplicationUser;
import com.registration.repository.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegistrationserviceApplication.class)
class CustomerRepositoryTest {

@MockBean
UserRepository repository;

@Test
public void testFindByPanNo() {
ApplicationUser user = new ApplicationUser("gm1967", "Venu", "Avin", "venu@gmail.com", null, "saru", "saru", null, null);
when(repository.findByPanNo("gm1967")).thenReturn(user);
Assert.assertEquals(user, repository.findByPanNo("gm1967"));
}
}

