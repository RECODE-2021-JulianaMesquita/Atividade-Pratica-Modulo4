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
import com.herokuapp.JuhMesquitaViagens.model.Physics;
import com.herokuapp.JuhMesquitaViagens.repository.PhysicsRepository;

@CrossOrigin(origins = "https://juhmesquitaviagens-front-end.herokuapp.com")
@RestController
@RequestMapping("/physics")
public class PhysicsController {
	@Autowired
	private PhysicsRepository physicsRepository;
	
	// get all physicss
	@GetMapping("/")
	public List<Physics> getAllPhysicss(){
		return physicsRepository.findAll();
	}		
	
	// create physics rest api
	@PostMapping("/create")
	public Physics createPhysics(@RequestBody Physics physics) {
		return physicsRepository.save(physics);
	}
	
	// get physics by id rest api
	@GetMapping("/getById/{id}")
	public ResponseEntity<Physics> getPhysicsById(@PathVariable int id) {
		Physics physics = physicsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Physics not exist with id :" + id));
		return ResponseEntity.ok(physics);
	}
	
	// update physics rest api	
	@PutMapping("/update/{id}")
	public ResponseEntity<Physics> updatePhysics(@PathVariable int id, @RequestBody Physics physicsDetails){
		Physics physics = physicsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Physics not exist with id :" + id));
		
		physics.setAddress(physicsDetails.getAddress());
		physics.setName(physicsDetails.getName());
		physics.setEmail(physicsDetails.getEmail());
		physics.setPassword(physicsDetails.getPassword());
		physics.setPhone(physicsDetails.getPhone());
		physics.setAdministrator(physicsDetails.isAdministrator());
		physics.setCpf(physicsDetails.getCpf());
		physics.setPackages(physicsDetails.getPackages());
		Physics updatedPhysics = physicsRepository.save(physics);
		return ResponseEntity.ok(updatedPhysics);
	}
	
	// delete physics rest api
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deletePhysics(@PathVariable int id){
		Physics physics = physicsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Physics not exist with id :" + id));
		
		physicsRepository.delete(physics);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
