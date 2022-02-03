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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class PhysicsController {
    @Autowired
    private PhysicsRepository physicsRepository;

    // get all pessoas físicas cadastrados
    @GetMapping("/physics")
    public List<Physics> getAllPhysics() {
        return physicsRepository.findAll();
    }

    // create pessoa física rest api
    @PostMapping("/physics")
    public Physics createLogin(@RequestBody Physics createPhysics) {
        return physicsRepository.save(createPhysics);
    }

    // get pessoa física by id rest api
    @GetMapping("/physics/{id}")
    public ResponseEntity <Physics> getLoginById(@PathVariable Long id) {
        Physics physicsId = physicsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe no banco de dados pessoa física com o id = "  + id));
        return ResponseEntity.ok(physicsId);
    }

    // update usuario rest api
    @PutMapping("/physics/{id}")
     public ResponseEntity <Physics> updateLogin(@PathVariable Long id, @RequestBody Physics physicsDetails) {
        Physics physicsEdit = physicsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe no banco de dados usuario com o id = "  + id));
 
        physicsEdit.setCpf(physicsDetails.getCpf());
        physicsEdit.setPackages(physicsDetails.getPackages());

        Physics updatedPhysics = physicsRepository.save(physicsEdit);
        return ResponseEntity.ok(updatedPhysics);
    }

    // delete usuario rest api
    @DeleteMapping("/physics/{id}")
    public ResponseEntity<Map<String,Boolean>>deletePhysics(@PathVariable Long id) {
        Physics physicsDelete = physicsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe no banco de dados usuario com o id = " + id));
        physicsRepository.delete(physicsDelete);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
