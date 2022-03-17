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

@CrossOrigin(origins = "https://juhmesquitaviagens-front-end.herokuapp.com")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginRepository loginRepository;

    // get all usuarios cadastrados
    @GetMapping("/alllogin")
    public List<Login> getAllLogin() {
        return loginRepository.findAll();
    }

    // create usuario rest api
    @PostMapping("/create")
    public Login createLogin(@RequestBody Login createLogin) {
        return loginRepository.save(createLogin);
    }

    // get usuario by id rest api
    @GetMapping("/login/{id}")
    public ResponseEntity <Login> getLoginById(@PathVariable int id) {
        Login loginId = loginRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe no banco de dados usuario com o id = "  + id));
        return ResponseEntity.ok(loginId);
    }

    // update usuario rest api
    @PutMapping("/login/{id}")
     public ResponseEntity <Login> updateLogin(@PathVariable int id, @RequestBody Login loginDetails) {
        Login loginEdit = loginRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe no banco de dados usuario com o id = "  + id));
 
        loginEdit.setAddress(loginDetails.getAddress());
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
     public ResponseEntity<Map<String,Boolean>>deletePackage(@PathVariable int id) {
        Login loginDelete = loginRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe no banco de dados usuario com o id = " + id));
        loginRepository.delete(loginDelete);
         Map < String, Boolean > response = new HashMap < > ();
         response.put("deleted", Boolean.TRUE);
         return ResponseEntity.ok(response);
     }

}
