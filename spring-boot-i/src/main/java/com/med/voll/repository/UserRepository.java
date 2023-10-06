package com.med.voll.repository;

import com.med.voll.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findAllByUsername(String username);

}
