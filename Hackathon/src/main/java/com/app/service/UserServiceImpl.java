package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.LoginResponse;
import com.app.dto.UserDto;
import com.app.entities.User;
import com.app.repository.BlogRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	private BlogRepository blogRepository;
	@Autowired
	private ModelMapper modelMapper;

	

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToSUer(userDto);
		User savedUser = this.userRepository.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long user_id) {
		User user = this.userRepository.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", user_id));
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		User updatedUser = this.userRepository.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Long user_id) {
		User user = this.userRepository.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", user_id));
		this.userRepository.delete(user);
	}

	@Override
	public UserDto getUserById(Long user_id) {
		User user = this.userRepository.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", user_id));
		return this.userToDto(user);
	}

	public User dtoToSUer(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public LoginResponse getUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
//        if (user != null) {
//            user.getPost();
//        }
        return this.modelMapper.map(user,LoginResponse.class);
    }
}
