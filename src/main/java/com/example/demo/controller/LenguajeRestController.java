package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Lenguaje;
import com.example.demo.service.ILenguajeService;

@RestController
@RequestMapping("/api")
public class LenguajeRestController {

	@Autowired
	private ILenguajeService service ; 
	
	@RequestMapping(value = "lenguajes")
	public ResponseEntity<?> getLenguajes(){
		
		List<Lenguaje> list = service.findAll();
		if(list.size() ==0)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	@PostMapping(value = "crear_lenguaje")
	public ResponseEntity<?> agregarLenguaje(@RequestBody Lenguaje lenguaje){
		
		
		service.saveLenguaje(lenguaje);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
}
