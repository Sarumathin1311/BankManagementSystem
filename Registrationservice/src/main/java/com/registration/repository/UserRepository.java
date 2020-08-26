package com.registration.repository;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.registration.model.ApplicationUser;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, String>{

ApplicationUser findByPanNo(String panNo);





}




