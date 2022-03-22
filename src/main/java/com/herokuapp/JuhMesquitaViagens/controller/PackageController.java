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
import com.herokuapp.JuhMesquitaViagens.model.Package;
import com.herokuapp.JuhMesquitaViagens.repository.PackageRepository;

@CrossOrigin(origins = "https://juhmesquitaviagens-front-end.herokuapp.com")
@RestController
@RequestMapping("/packageTravel")
public class PackageController {
	@Autowired
	private PackageRepository packageTravelRepository;
	
	// get all packageTravels
	@GetMapping("/")
	public List<Package> getAllPackages(){
		return packageTravelRepository.findAll();
	}		
	
	// create packageTravel rest api
	@PostMapping("/create")
	public Package createPackage(@RequestBody Package packageTravel) {
		return packageTravelRepository.save(packageTravel);
	}
	
	// get packageTravel by id rest api
	@GetMapping("/getById/{id}")
	public ResponseEntity<Package> getPackageById(@PathVariable int id) {
		Package packageTravel = packageTravelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Package not exist with id :" + id));
		return ResponseEntity.ok(packageTravel);
	}
	
	// update packageTravel rest api	
	@PutMapping("/update/{id}")
	public ResponseEntity<Package> updatePackage(@PathVariable int id, @RequestBody Package packageTravelDetails){
		Package packageTravel = packageTravelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Package not exist with id :" + id));
		packageTravel.setAddressDestiny(packageTravelDetails.getAddressDestiny());
		packageTravel.setAddressOrigin(packageTravelDetails.getAddressOrigin());
		packageTravel.setTitle(packageTravelDetails.getTitle());
		packageTravel.setPeople(packageTravelDetails.getPeople());
		packageTravel.setValue(packageTravelDetails.getValue());
		packageTravel.setPromotion(packageTravelDetails.isPromotion());
		packageTravel.setDateGoing(packageTravelDetails.getDateGoing());
		packageTravel.setDateReturn(packageTravelDetails.getDateReturn());
		Package updatedPackage = packageTravelRepository.save(packageTravel);
		return ResponseEntity.ok(updatedPackage);
	}
	
	// delete packageTravel rest api
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deletePackage(@PathVariable int id){
		Package packageTravel = packageTravelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Package not exist with id :" + id));
		
		packageTravelRepository.delete(packageTravel);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
