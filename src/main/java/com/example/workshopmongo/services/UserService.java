package com.example.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.dto.UserDTO;
import com.example.workshopmongo.repository.UserRepository;
import com.example.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return this.repository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. Id: " + id));
	}
	
	public User insert(User obj) {
	 	return repository.insert(obj);
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}