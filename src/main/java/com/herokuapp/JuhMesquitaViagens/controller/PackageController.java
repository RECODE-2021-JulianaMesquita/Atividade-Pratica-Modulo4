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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class PackageController {
    @Autowired
    private PackageRepository packageRepository;

    // get all pacotes
    @GetMapping("/packages")
    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    // create pacote rest api
    @PostMapping("/packages")
    public Package createPackage(@RequestBody Package createPackage) {
        return packageRepository.save(createPackage);
    }

    // get pacote by id rest api
    @GetMapping("/packages/{id}")
    public ResponseEntity <Package> getPackageById(@PathVariable Long id) {
        Package packageId = packageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe no banco de dados o pacote com o id=" + id));
        return ResponseEntity.ok(packageId);
    }

    // update pacote rest api
    @PutMapping("/packages/{id}")
     public ResponseEntity <Package> updatePackage(@PathVariable Long id, @RequestBody Package packageDetails) {
        Package packageEdit = packageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe no banco de dados o pacote com o id=" + id));
 
        packageEdit.setTitle(packageDetails.getTitle());
        packageEdit.setIdAddressDestiny(packageDetails.getIdAddressDestiny());
        packageEdit.setIdAddressOrigin(packageDetails.getIdAddressOrigin());
        packageEdit.setIdLegal(packageDetails.getIdLegal());
        packageEdit.setPeople(packageDetails.getPeople());
        packageEdit.setValue(packageDetails.getValue());
        packageEdit.setPromotion(packageDetails.isPromotion());
        packageEdit.setDateGoing(packageDetails.getDateGoing());
        packageEdit.setDateReturn(packageDetails.getDateReturn());
 
         Package updatedPackage = packageRepository.save(packageEdit);
         return ResponseEntity.ok(updatedPackage);
     }

    // delete pacote rest api
    @DeleteMapping("/packages/{id}")
    public ResponseEntity<Map<String,Boolean>>deletePackage(@PathVariable Long id) {
        Package packageDelete = packageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe no banco de dados o pacote com o id =:" + id));
        packageRepository.delete(packageDelete);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
