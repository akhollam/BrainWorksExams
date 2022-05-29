package com.brainworksexams.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brainworksexams.repository.UserRepository;

@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<com.brainworksexams.entity.User> user = userRepository.findByUsername(userName);
		if (user.isPresent()) {
			com.brainworksexams.entity.User userEntity = user.get();
			return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
		}

		throw new UsernameNotFoundException("User not found. ");
		// return new User(userEntity.getUsername(),
		// "$2a$10$OXmVVEItkLcCdJIwR3iXGewnYpreeK1qtxfrZsaESO//SYkMaX20y", new
		// ArrayList<>());
		// Logic to get the user form the Database
	}

}
