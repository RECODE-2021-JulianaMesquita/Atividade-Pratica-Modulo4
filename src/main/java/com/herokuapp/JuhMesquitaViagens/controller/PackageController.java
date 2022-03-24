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

import com.herokuapp.JuhMesquitaViagens.model.Package;
import com.herokuapp.JuhMesquitaViagens.repository.PackageRepository;

@CrossOrigin(origins = "https://juhmesquitaviagens-front-end.herokuapp.com")
@RestController
@RequestMapping(value="/packageTravel")
public class PackageController {
	private PackageRepository packageTravelRepository;
	
	public PackageController(PackageRepository packageTravelRepository) {
		super();
		this.packageTravelRepository = packageTravelRepository;
	}
	
	@PostMapping
	public ResponseEntity<Package> save(@RequestBody Package packageTravel){
		packageTravelRepository.save(packageTravel);
		return new ResponseEntity<>(packageTravel, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Package>> getAll(){
		List<Package> packageTravels = new ArrayList<>();
		packageTravels = packageTravelRepository.findAll();
		return new ResponseEntity<>(packageTravels, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Optional<Package>> getById(@PathVariable Integer id){
		Optional<Package> packageTravel;
		try {
			packageTravel = packageTravelRepository.findById(id);
			return new ResponseEntity<Optional<Package>>(packageTravel, HttpStatus.OK);
		}catch(NoSuchElementException nsee) {
			return new ResponseEntity<Optional<Package>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Optional<Package>> deleteById(@PathVariable Integer id){
		try {
			packageTravelRepository.deleteById(id);
			return new ResponseEntity<Optional<Package>>(HttpStatus.OK);
		}catch(NoSuchElementException nsee){
			return new ResponseEntity<Optional<Package>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Package> update(@PathVariable Integer id, @RequestBody Package newPackageTravel){
		return packageTravelRepository.findById(id)
			   .map(packageTravel -> {
				   packageTravel.setAddressDestiny(newPackageTravel.getAddressDestiny());
					packageTravel.setAddressOrigin(newPackageTravel.getAddressOrigin());
					packageTravel.setTitle(newPackageTravel.getTitle());
					packageTravel.setPeople(newPackageTravel.getPeople());
					packageTravel.setValue(newPackageTravel.getValue());
					packageTravel.setPromotion(newPackageTravel.isPromotion());
					packageTravel.setDateGoing(newPackageTravel.getDateGoing());
					packageTravel.setDateReturn(newPackageTravel.getDateReturn());
				   Package packageTravelUpdate = packageTravelRepository.save(packageTravel);
				   return ResponseEntity.ok().body(packageTravelUpdate);
			   }).orElse(ResponseEntity.notFound().build());
	}
}
