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

import com.herokuapp.JuhMesquitaViagens.model.Physics;
import com.herokuapp.JuhMesquitaViagens.repository.PhysicsRepository;

@CrossOrigin(origins = "https://juhmesquitaviagens-front-end.herokuapp.com")
@RestController
@RequestMapping(value="/physics")
public class PhysicsController {
	private PhysicsRepository physicsRepository;
	
	public PhysicsController(PhysicsRepository physicsRepository) {
		super();
		this.physicsRepository = physicsRepository;
	}
	
	@PostMapping
	public ResponseEntity<Physics> save(@RequestBody Physics physics){
		physicsRepository.save(physics);
		return new ResponseEntity<>(physics, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Physics>> getAll(){
		List<Physics> physicss = new ArrayList<>();
		physicss = physicsRepository.findAll();
		return new ResponseEntity<>(physicss, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Optional<Physics>> getById(@PathVariable Integer id){
		Optional<Physics> physics;
		try {
			physics = physicsRepository.findById(id);
			return new ResponseEntity<Optional<Physics>>(physics, HttpStatus.OK);
		}catch(NoSuchElementException nsee) {
			return new ResponseEntity<Optional<Physics>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Optional<Physics>> deleteById(@PathVariable Integer id){
		try {
			physicsRepository.deleteById(id);
			return new ResponseEntity<Optional<Physics>>(HttpStatus.OK);
		}catch(NoSuchElementException nsee){
			return new ResponseEntity<Optional<Physics>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Physics> update(@PathVariable Integer id, @RequestBody Physics newPhysics){
		return physicsRepository.findById(id)
			   .map(physics -> {
				   physics.setAddress(newPhysics.getAddress());
					physics.setName(newPhysics.getName());
					physics.setEmail(newPhysics.getEmail());
					physics.setPassword(newPhysics.getPassword());
					physics.setPhone(newPhysics.getPhone());
					physics.setAdministrator(newPhysics.isAdministrator());
				   Physics physicsUpdate = physicsRepository.save(physics);
				   return ResponseEntity.ok().body(physicsUpdate);
			   }).orElse(ResponseEntity.notFound().build());
	}
		



}
