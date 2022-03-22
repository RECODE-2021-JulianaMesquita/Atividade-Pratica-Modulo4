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
import com.herokuapp.JuhMesquitaViagens.model.Address;
import com.herokuapp.JuhMesquitaViagens.repository.AddressRepository;

@CrossOrigin(origins = "https://juhmesquitaviagens-front-end.herokuapp.com")
@RestController
@RequestMapping("address")
public class AddressController {@Autowired
	private AddressRepository addressRepository;
	
	// get all addresss
	@GetMapping("/")
	public List<Address> getAllAddresss(){
		return addressRepository.findAll();
	}		
	
	// create address rest api
	@PostMapping("/create")
	public Address createAddress(@RequestBody Address address) {
		return addressRepository.save(address);
	}
	
	// get address by id rest api
	@GetMapping("/getById/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable int id) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Address not exist with id :" + id));
		return ResponseEntity.ok(address);
	}
	
	// update address rest api
	@PutMapping("/update/{id}")
	public ResponseEntity<Address> updateAddress(@PathVariable int id, @RequestBody Address addressDetails){
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Address not exist with id :" + id));
		
		address.setState(addressDetails.getState());
		address.setCountry(addressDetails.getCountry());
		address.setMunicipality(addressDetails.getMunicipality());
		address.setCode(addressDetails.getCode());
		Address updatedAddress = addressRepository.save(address);
		return ResponseEntity.ok(updatedAddress);
	}
	
	// delete address rest api
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteAddress(@PathVariable int id){
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Address not exist with id :" + id));
		
		addressRepository.delete(address);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}				   
	
}
