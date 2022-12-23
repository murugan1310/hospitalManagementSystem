package com.hms.spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hms.spring.dto.UserRegistrationDto;
import com.hms.spring.entity.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}