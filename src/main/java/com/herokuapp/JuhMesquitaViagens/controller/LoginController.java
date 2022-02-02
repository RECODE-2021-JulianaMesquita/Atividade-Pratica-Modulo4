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
import com.herokuapp.JuhMesquitaViagens.model.Login;
import com.herokuapp.JuhMesquitaViagens.repository.LoginRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class LoginController {
    @Autowired
    private LoginRepository loginRepository;

    // get all usuarios cadastrados
    @GetMapping("/login")
    public List<Login> getAllLogin() {
        return loginRepository.findAll();
    }

    // create usuario rest api
    @PostMapping("/login")
    public Login createLogin(@RequestBody Login createLogin) {
        return loginRepository.save(createLogin);
    }

    // get usuario by id rest api
    @GetMapping("/login/{id}")
    public ResponseEntity <Login> getLoginById(@PathVariable Long id) {
        Login loginId = loginRepository.findById(id).orElseThrow(() - > new ResourceNotFoundException("Não existe no banco de dados usuario com o id = "  + id));
        return ResponseEntity.ok(loginId);
    }

    // update usuario rest api
    @PutMapping("/login/{id}")
     public ResponseEntity <Login> updateLogin(@PathVariable Long id, @RequestBody Login loginDetails) {
        Login loginEdit = loginRepository.findById(id).orElseThrow(() - > new ResourceNotFoundException("Não existe no banco de dados usuario com o id = "  + id));
 
        loginEdit.setIdAddress(loginDetails.getIdAddress());
        loginEdit.setName(loginDetails.getName());
        loginEdit.setEmail(loginDetails.getEmail());
        loginEdit.setPassword(loginDetails.getPassword());
        loginEdit.setPhone(loginDetails.getPhone());
        loginEdit.setAdministrator(loginDetails.isAdministrator());

        Login updatedlogin = loginRepository.save(loginEdit);
        return ResponseEntity.ok(updatedlogin);
    }

     // delete usuario rest api
     @DeleteMapping("/login/{id}")
     public ResponseEntity<Map<String,Boolean>>deletePackage(@PathVariable Long id) {
        Login loginDelete = loginRepository.findById(id).orElseThrow(() - > new ResourceNotFoundException("Não existe no banco de dados usuario com o id = " + id));
        loginRepository.delete(loginDelete);
         Map < String, Boolean > response = new HashMap < > ();
         response.put("deleted", Boolean.TRUE);
         return ResponseEntity.ok(response);
     }

}
