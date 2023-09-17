package com.app.service;

import java.util.List;

import com.app.dto.LoginResponse;
import com.app.dto.UserDto;
import com.app.entities.User;

public interface UserService {
	LoginResponse getUserByUsernameAndPassword(String username, String password) ;
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Long user_id);
	List<UserDto> getAllUsers(); 
	UserDto getUserById(Long user_id);
	void deleteUser(Long user_id);
}
