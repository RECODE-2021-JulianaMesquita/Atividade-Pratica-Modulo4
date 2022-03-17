package com.herokuapp.JuhMesquitaViagens.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herokuapp.JuhMesquitaViagens.exception.ResourceNotFoundException;
import com.herokuapp.JuhMesquitaViagens.model.Login;
import com.herokuapp.JuhMesquitaViagens.repository.LoginRepository;

@CrossOrigin(origins = "https://juhmesquitaviagens-front-end.herokuapp.com")
@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginRepository loginRepository;
	
	// get all logins
	@GetMapping("/all")
	public List<Login> getAllLogins(){
		return loginRepository.findAll();
	}		
	
	// create login rest api
	@PostMapping("/create")
	public Login createLogin(@RequestBody Login login) {
		return loginRepository.save(login);
	}
	
	// get login by id rest api
	@GetMapping("/details/{id}")
	public ResponseEntity<Login> getLoginById(@PathVariable int id) {
		Login login = loginRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Login not exist with id :" + id));
		return ResponseEntity.ok(login);
	}
	
	// update login rest api	
	@PutMapping("/update/{id}")
	public ResponseEntity<Login> updateLogin(@PathVariable int id, @RequestBody Login loginDetails){
		Login login = loginRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Login not exist with id :" + id));
		
		login.setAddress(loginDetails.getAddress());
		login.setName(loginDetails.getName());
		login.setEmail(loginDetails.getEmail());
		login.setPassword(loginDetails.getPassword());
		login.setPhone(loginDetails.getPhone());
		login.setAdministrator(loginDetails.isAdministrator());
		Login updatedLogin = loginRepository.save(login);
		return ResponseEntity.ok(updatedLogin);
	}
	
	// delete login rest api
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteLogin(@PathVariable int id){
		Login login = loginRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Login not exist with id :" + id));
		
		loginRepository.delete(login);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
