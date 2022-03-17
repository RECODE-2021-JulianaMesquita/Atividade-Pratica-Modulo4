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

    // get all empresas cadastradas
    @GetMapping("/alllegal")
    public List<Legal> getAllLegal() {
        return legalRepository.findAll();
    }

    // create empresa rest api
    @PostMapping("/create")
    public Legal createLogin(@RequestBody Legal createLegal) {
        return legalRepository.save(createLegal);
    }

    // get empresa by id rest api
    @GetMapping("/legal/{id}")
    public ResponseEntity <Legal> getLoginById(@PathVariable int id) {
        Legal legalId = legalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe no banco de dados empresa com o id = "  + id));
        return ResponseEntity.ok(legalId);
    }

    // update usuario rest api
    @PutMapping("/legal/{id}")
     public ResponseEntity <Legal> updateLegal(@PathVariable int id, @RequestBody Legal legalDetails) {
        Legal legalEdit = legalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe no banco de dados empresa com o id = "  + id));
 
        legalEdit.setCnpj(legalDetails.getCnpj());

        Legal updatedLegal = legalRepository.save(legalEdit);
        return ResponseEntity.ok(updatedLegal);
    }

    // delete empresa rest api
    @DeleteMapping("/legal/{id}")
    public ResponseEntity<Map<String,Boolean>>deleteLegal(@PathVariable int id) {
        Legal legalDelete = legalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe no banco de dados empresa com o id = " + id));
        legalRepository.delete(legalDelete);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
