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

import com.herokuapp.JuhMesquitaViagens.model.Legal;
import com.herokuapp.JuhMesquitaViagens.repository.LegalRepository;

@CrossOrigin(origins = "https://juhmesquitaviagens-front-end.herokuapp.com")
@RestController
@RequestMapping(value="/legal")
public class LegalController {
	private LegalRepository legalRepository;
	
	public LegalController(LegalRepository legalRepository) {
		super();
		this.legalRepository = legalRepository;
	}
	
	@PostMapping
	public ResponseEntity<Legal> save(@RequestBody Legal legal){
		legalRepository.save(legal);
		return new ResponseEntity<>(legal, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Legal>> getAll(){
		List<Legal> legals = new ArrayList<>();
		legals = legalRepository.findAll();
		return new ResponseEntity<>(legals, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Optional<Legal>> getById(@PathVariable Integer id){
		Optional<Legal> legal;
		try {
			legal = legalRepository.findById(id);
			return new ResponseEntity<Optional<Legal>>(legal, HttpStatus.OK);
		}catch(NoSuchElementException nsee) {
			return new ResponseEntity<Optional<Legal>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Optional<Legal>> deleteById(@PathVariable Integer id){
		try {
			legalRepository.deleteById(id);
			return new ResponseEntity<Optional<Legal>>(HttpStatus.OK);
		}catch(NoSuchElementException nsee){
			return new ResponseEntity<Optional<Legal>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Legal> update(@PathVariable Integer id, @RequestBody Legal newLegal){
		return legalRepository.findById(id)
			   .map(legal -> {
				   legal.setAddress(newLegal.getAddress());
					legal.setName(newLegal.getName());
					legal.setEmail(newLegal.getEmail());
					legal.setPassword(newLegal.getPassword());
					legal.setPhone(newLegal.getPhone());
					legal.setAdministrator(newLegal.isAdministrator());
					legal.setCnpj(newLegal.getCnpj());
				   Legal legalUpdate = legalRepository.save(legal);
				   return ResponseEntity.ok().body(legalUpdate);
			   }).orElse(ResponseEntity.notFound().build());
	}	
}