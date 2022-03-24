package com.herokuapp.JuhMesquitaViagens.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.herokuapp.JuhMesquitaViagens.model.Login;
import com.herokuapp.JuhMesquitaViagens.repository.LoginRepository;

@CrossOrigin(origins = "https://juhmesquitaviagens-front-end.herokuapp.com")
@RestController
@RequestMapping(value="/login")
public class LoginController {
	private LoginRepository loginRepository;
	
	public LoginController(LoginRepository loginRepository) {
		super();
		this.loginRepository = loginRepository;
	}
	
	@PostMapping
	public ResponseEntity<Login> save(@RequestBody Login login){
		loginRepository.save(login);
		return new ResponseEntity<>(login, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Login>> getAll(){
		List<Login> logins = new ArrayList<>();
		logins = loginRepository.findAll();
		return new ResponseEntity<>(logins, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Optional<Login>> getById(@PathVariable Integer id){
		Optional<Login> login;
		try {
			login = loginRepository.findById(id);
			return new ResponseEntity<Optional<Login>>(login, HttpStatus.OK);
		}catch(NoSuchElementException nsee) {
			return new ResponseEntity<Optional<Login>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Optional<Login>> deleteById(@PathVariable Integer id){
		try {
			loginRepository.deleteById(id);
			return new ResponseEntity<Optional<Login>>(HttpStatus.OK);
		}catch(NoSuchElementException nsee){
			return new ResponseEntity<Optional<Login>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Login> update(@PathVariable Integer id, @RequestBody Login newLogin){
		return loginRepository.findById(id)
			   .map(login -> {
				   login.setAddress(newLogin.getAddress());
					login.setName(newLogin.getName());
					login.setEmail(newLogin.getEmail());
					login.setPassword(newLogin.getPassword());
					login.setPhone(newLogin.getPhone());
					login.setDescrition(newLogin.getDescrition());
				   Login loginUpdate = loginRepository.save(login);
				   return ResponseEntity.ok().body(loginUpdate);
			   }).orElse(ResponseEntity.notFound().build());
	}
}
