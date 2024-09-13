package com.kosta.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kosta.domain.UserRequest;
import com.kosta.domain.UserResponse;
import com.kosta.entity.User;
import com.kosta.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Override
	public UserResponse insertUser(UserRequest us) {
		String encodedPassword = bCryptPasswordEncoder.encode(us.getPassword());
		us.setPassword(encodedPassword);
		User save = userRepository.save(us.toEntity());
		UserResponse ur = UserResponse.toDTO(save);
		return ur;
	}

	@Override
	public List<UserResponse> getAllUser() {
		List<User> userList = userRepository.findAll();
		List<UserResponse> urList = userList.stream().map(UserResponse::toDTO).toList();
		return urList;
	}

	@Override
	public UserResponse deleteUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("없음"));
		userRepository.delete(user);
		return UserResponse.toDTO(user);
	}

	@Override
	public UserResponse updateUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("없음"));
		UserResponse ur = UserResponse.toDTO(user);
		return ur;
	}

}
