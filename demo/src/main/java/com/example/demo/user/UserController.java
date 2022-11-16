package com.example.demo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@PostMapping
	public void registerUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@DeleteMapping(path = "{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		userService.deleteUser(userId);
	}
	
	@PutMapping(path = "{userId}")
	public void updateUser(
			@PathVariable("userId") Long userId,
			@RequestBody User user) {
		userService.updateUser(userId, user);
	}
	
}
 