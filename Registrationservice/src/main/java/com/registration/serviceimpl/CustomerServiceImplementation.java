package com.registration.serviceimpl;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.registration.model.ApplicationUser;
import com.registration.repository.UserRepository;


@Component
@Service
public class CustomerServiceImplementation implements UserDetailsService {

@Autowired
private BCryptPasswordEncoder bcryptEncoder;
@Autowired
private UserRepository userRepository;

@Autowired
RestTemplate restTemplate;

public CustomerServiceImplementation(UserRepository userRepository) {
this.userRepository = userRepository;
}

public ApplicationUser createUser(ApplicationUser user) {

userRepository.save(user);

user.setPassword(bcryptEncoder.encode(user.getPassword()));
System.out.println("Getting data from DB " + user);
return userRepository.save(user);
}

@Override
public UserDetails loadUserByUsername(String panNo) throws UsernameNotFoundException {

ApplicationUser user = userRepository.findByPanNo(panNo);

if (user == null) {
throw new UsernameNotFoundException("Invalid username or password.");
}
return new org.springframework.security.core.userdetails.User(user.getPanNo(), user.getPassword(),
getAuthority());
}

private List<SimpleGrantedAuthority> getAuthority() {

return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
}

public ApplicationUser findOne(String panNo) {

return userRepository.findByPanNo(panNo);
}

public Optional<ApplicationUser> getUserById(String id) {

return userRepository.findById(id);
}

public ApplicationUser getUserByPan(String panNo) {
return userRepository.findByPanNo(panNo);
}

}

