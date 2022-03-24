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

import com.herokuapp.JuhMesquitaViagens.model.Address;
import com.herokuapp.JuhMesquitaViagens.repository.AddressRepository;

@CrossOrigin(origins = "https://juhmesquitaviagens-front-end.herokuapp.com")
@RestController
@RequestMapping(value="/address")
public class AddressController {
	
	private AddressRepository addressRepository;
	
	public AddressController(AddressRepository addressRepository) {
		super();
		this.addressRepository = addressRepository;
	}
	
	@PostMapping
	public ResponseEntity<Address> save(@RequestBody Address address){
		addressRepository.save(address);
		return new ResponseEntity<>(address, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Address>> getAll(){
		List<Address> addresss = new ArrayList<>();
		addresss = addressRepository.findAll();
		return new ResponseEntity<>(addresss, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Optional<Address>> getById(@PathVariable Integer id){
		Optional<Address> address;
		try {
			address = addressRepository.findById(id);
			return new ResponseEntity<Optional<Address>>(address, HttpStatus.OK);
		}catch(NoSuchElementException nsee) {
			return new ResponseEntity<Optional<Address>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Optional<Address>> deleteById(@PathVariable Integer id){
		try {
			addressRepository.deleteById(id);
			return new ResponseEntity<Optional<Address>>(HttpStatus.OK);
		}catch(NoSuchElementException nsee){
			return new ResponseEntity<Optional<Address>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Address> update(@PathVariable Integer id, @RequestBody Address newAddress){
		return addressRepository.findById(id)
			   .map(address -> {
				   address.setState(newAddress.getState());
					address.setCountry(newAddress.getCountry());
					address.setMunicipality(newAddress.getMunicipality());
					address.setCode(newAddress.getCode());
				   Address addressUpdate = addressRepository.save(address);
				   return ResponseEntity.ok().body(addressUpdate);
			   }).orElse(ResponseEntity.notFound().build());
	}
	
}
