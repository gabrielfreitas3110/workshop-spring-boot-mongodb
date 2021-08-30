package com.example.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public List<User> findAll(){
		return this.repository.findAll();
	}
}