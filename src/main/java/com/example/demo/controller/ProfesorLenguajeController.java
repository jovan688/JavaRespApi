package com.example.demo.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Lenguaje;
import com.example.demo.entity.Profesor;
import com.example.demo.entity.ProfesorLenguaje;
import com.example.demo.service.ILenguajeService;
import com.example.demo.service.IProfesorService;

@RestController
@RequestMapping(value = "/api")
public class ProfesorLenguajeController {

	
	@Autowired
	IProfesorService service;
	
	@Autowired
	ILenguajeService servicelenguaje;
	
	@PostMapping(value ="/lenguaje_profesor")
	public ResponseEntity<?> obtenerListaProfesor(@RequestBody Profesor profesor){
		
		Profesor profesordb = service.findById(profesor.getId());
		if(profesordb== null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
		Collection<Lenguaje> lenguajes =  profesordb.getLenguajes();
		if(lenguajes.size()>0)
			return new ResponseEntity<>(lenguajes , HttpStatus.OK);
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value ="/save_lenguaje_profesor")
	public ResponseEntity<?> savelenguajeprofesor(@RequestBody ProfesorLenguaje profesorlenguaje){
		
		Profesor profesordb = service.findById(profesorlenguaje.getProfesor().getId());
		if(profesordb== null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
		Lenguaje lenguaje = servicelenguaje.findLengua(profesorlenguaje.getLenguaje().getId());
		profesordb.addLenguaje(lenguaje);
		service.save(profesordb);
		
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
}
