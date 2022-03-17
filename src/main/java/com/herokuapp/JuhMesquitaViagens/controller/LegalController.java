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
import com.herokuapp.JuhMesquitaViagens.model.Legal;
import com.herokuapp.JuhMesquitaViagens.repository.LegalRepository;

@CrossOrigin(origins = "https://juhmesquitaviagens-front-end.herokuapp.com")
@RestController
@RequestMapping("legal")
public class LegalController {
	@Autowired
	private LegalRepository legalRepository;
	
	// get all legals
	@GetMapping("/all")
	public List<Legal> getAllLegals(){
		return legalRepository.findAll();
	}		
	
	// create legal rest api
	@PostMapping("/create")
	public Legal createLegal(@RequestBody Legal legal) {
		return legalRepository.save(legal);
	}
	
	// get legal by id rest api
	@GetMapping("/details/{id}")
	public ResponseEntity<Legal> getLegalById(@PathVariable int id) {
		Legal legal = legalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Legal not exist with id :" + id));
		return ResponseEntity.ok(legal);
	}
	
	// update legal rest api
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Legal> updateLegal(@PathVariable int id, @RequestBody Legal legalDetails){
		Legal legal = legalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Legal not exist with id :" + id));
		
		legal.setAddress(legalDetails.getAddress());
		legal.setName(legalDetails.getName());
		legal.setEmail(legalDetails.getEmail());
		legal.setPassword(legalDetails.getPassword());
		legal.setPhone(legalDetails.getPhone());
		legal.setAdministrator(legalDetails.isAdministrator());
		legal.setCnpj(legalDetails.getCnpj());
		Legal updatedLegal = legalRepository.save(legal);
		return ResponseEntity.ok(updatedLegal);
	}
	
	// delete legal rest api
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteLegal(@PathVariable int id){
		Legal legal = legalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Legal not exist with id :" + id));
		
		legalRepository.delete(legal);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
