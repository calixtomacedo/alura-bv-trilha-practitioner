package com.med.voll.springbootii.repository;

import com.med.voll.springbootii.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findAllByUsername(String username);

}
