package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginRequest;
import com.app.dto.UserDto;
import com.app.payloads.ApiResponse;
import com.app.service.UserService;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	public UserController() {
		System.out.println("in ctr of " + getClass());
	}

	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody LoginRequest request) {
		System.out.println("in login user details");
		System.out.println(request.getUsername() + request.getPassword());
		return new ResponseEntity<>(userService.getUserByUsernameAndPassword(request.getUsername(), request.getPassword()), HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@RequestBody @Valid UserDto request) {
		return new ResponseEntity<>(userService.createUser(request), HttpStatus.OK);
	}

	@PutMapping("/{user_id}")
	public ResponseEntity<?> updateUser(@PathVariable Long user_id, @RequestBody UserDto request) {
		UserDto updateUser = this.userService.updateUser(request, user_id);
		return ResponseEntity.ok(updateUser);
	}

	@DeleteMapping("/{user_id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long user_id) {
		this.userService.deleteUser(user_id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Succefully", true), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	@GetMapping("/{user_id}")
	public ResponseEntity<?> getUserById(@PathVariable Long user_id) {
		return ResponseEntity.ok(this.userService.getUserById(user_id));
	}
}
