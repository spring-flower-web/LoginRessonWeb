package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

//newをしなくてもインスタンス化されている
@Service
public class UserService {

		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private PasswordEncoder passwordEncoder;
		
		public void register(String username,String password,String name) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(passwordEncoder.encode(password));
			user.setName(name);
			
			userRepository.save(user);
		}
}	
