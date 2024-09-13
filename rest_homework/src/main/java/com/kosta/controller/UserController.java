package com.kosta.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.domain.UserRequest;
import com.kosta.domain.UserResponse;
import com.kosta.service.UserService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
	private final UserService userService;
	
	//회원 추가
	@PostMapping("")
	public ResponseEntity<UserResponse> insertUser(@RequestBody UserRequest us){
		UserResponse ur = userService.insertUser(us);
		return ResponseEntity.status(HttpStatus.CREATED).body(ur);
		
	}
	//회원 전체 목록
	@GetMapping("")
	public ResponseEntity<List<UserResponse>> getAllUser(){
		List<UserResponse> userList = new ArrayList<>();
		userList = userService.getAllUser();
		return ResponseEntity.ok(userList);
	}
	//회원 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<UserResponse> deleteUser(@PathVariable("id") Long id){
		UserResponse ur = userService.deleteUser(id);
		return ResponseEntity.ok(ur);
	}
	//회원 수정
	@PatchMapping("/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id){
		UserResponse ur = userService.updateUser(id);
		return ResponseEntity.ok(ur);
	}
}
