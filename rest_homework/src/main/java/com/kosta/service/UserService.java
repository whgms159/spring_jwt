package com.kosta.service;

import java.util.List;

import com.kosta.domain.UserRequest;
import com.kosta.domain.UserResponse;

public interface UserService {

	UserResponse insertUser(UserRequest us);

	List<UserResponse> getAllUser();

	UserResponse deleteUser(Long id);

	UserResponse updateUser(Long id);

}
