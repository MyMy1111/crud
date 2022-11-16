package com.example.demo.user;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public void addUser(User user) {
		Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
		if (userOptional.isPresent()) {
			throw new IllegalStateException("Email existed!");
		}
		userRepository.save(user);
	}

	public void deleteUser(Long userId) {
		boolean isUser = userRepository.existsById(userId);
		if (!isUser) {
			throw new IllegalStateException("User with id " + userId + " does not exist");
		}
		userRepository.deleteById(userId);
	}
	
	@Transactional
	public void updateUser(Long userId, User updateUser) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist!"));
		
		if (updateUser != null && !Objects.equals(user.getName(), updateUser.getName())) {
			user.setName(updateUser.getName());
		}
		
		if (updateUser != null && !Objects.equals(user.getEmail(), updateUser.getEmail())) {
			Optional<User> userOptional = userRepository.findUserByEmail(updateUser.getEmail());
			if(userOptional.isPresent()) {
				throw new IllegalStateException("Email existed!!!");
			}
			user.setEmail(updateUser.getEmail());
		}
	}
}