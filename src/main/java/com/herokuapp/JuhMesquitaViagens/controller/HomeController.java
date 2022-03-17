package com.herokuapp.JuhMesquitaViagens.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/")
public class HomeController {
	@GetMapping
	public String getHello() {
		return "Estou funcionando!  Aplicação online Front-End: https://juhmesquitaviagens-front-end.herokuapp.com/";
	}
}
