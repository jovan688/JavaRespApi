package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Profesor;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.MProfesor;
import com.example.demo.service.IProfesorService;

@RestController
@RequestMapping("/api")
public class ProfesorRestController {

	@Autowired
	private IProfesorService service;
	
	@GetMapping("/profesores")
	@ResponseStatus(HttpStatus.OK)
	public List<Profesor> getProfesor(){
		return service.findAll();
	}
	@PostMapping("/find_profesor")
	public ResponseEntity<?> findProfesor(@RequestBody Profesor profesor)
	{
		Profesor profesordb= service.findProfesor(profesor);
		if(profesordb == null) {
			return new ResponseEntity<Void>( HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( profesordb , HttpStatus.OK);
		
	}
	
	@PostMapping("/loginProfesor")
	public ResponseEntity<?> loginProfesor(@RequestBody Profesor profesor)
	{
		Profesor profesordb= service.checkProfesorLogin(profesor);
		if(profesordb == null) {
			return new ResponseEntity<Void>( HttpStatus.NOT_FOUND);
		}
	
		List<Profesor> profesores = new ArrayList<Profesor>();
		profesores.add(profesordb);
		
	
		return new ResponseEntity<>( Mapper.convertirLista(profesores ) , HttpStatus.OK);
	}
	
	@PostMapping("/sign_up")
	public ResponseEntity<Void> addProfesor(@RequestBody Profesor profesor){
		if(service.findProfesor(profesor) == null )
		{
			service.save(profesor);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>( HttpStatus.CONFLICT);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Void> updateProfesor(@PathVariable(value = "id")Long id, @RequestBody Profesor profesor)
	{
		Profesor profersordb = service.findById(id);
		
		if(profersordb != null) 
		{
			profersordb.setEmail(profesor.getEmail ());
			profersordb.setNombre(profesor.getNombre());
			service.updateProfesor(profersordb);
			return new ResponseEntity<Void>( HttpStatus.OK);
		}
		else
			return new ResponseEntity<Void>( HttpStatus.NOT_FOUND);
		
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProfesor(@PathVariable(value = "id") Long id , @RequestBody Profesor profesor)
	{

		Profesor profesordb =service.findById(id);
		if(profesordb == null) {
			return new ResponseEntity<Void>( HttpStatus.NOT_FOUND);
		}
		service.deleteProfesor(id);
		
		
		return new ResponseEntity<Void>( HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteProfesor()
	{

		service.deleteAll();
		
		return new ResponseEntity<Void>( HttpStatus.OK);
	}
	
	@PutMapping("/update/update_sql")
	public ResponseEntity<?> updateProfesor(@RequestBody Profesor profesor)
	{
		Profesor profersordb = service.findBySQl(profesor.getId());
		
		if(profersordb != null) 
		{
			profersordb.setEmail(profesor.getEmail ());
			profersordb.setNombre(profesor.getNombre());
			service.updateProfesor(profersordb);
			return new ResponseEntity<Profesor>( profersordb ,HttpStatus.OK);
		}
		else
			return new ResponseEntity<Void>( HttpStatus.NOT_FOUND);
		
		
	}
	@PostMapping("/delete_post")
	public ResponseEntity<Void> deleteProfesor( @RequestBody Profesor profesor)
	{
		Profesor profesordb =service.findById(profesor.getId());
		if(profesordb == null) {
			return new ResponseEntity<Void>( HttpStatus.NOT_FOUND);
		}
		service.deleteProfesor(profesordb);
		return new ResponseEntity<Void>( HttpStatus.OK);
	}

	@GetMapping("/findAny")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Profesor> getfindAny(){
		return service.findAnyNombre();
	}
	
	@GetMapping("/findAnyMachText")
	@ResponseStatus(HttpStatus.OK)
	public List<Profesor> findAnyMatch(@RequestParam(value = "nombrebusqueda") String nombrebusqueda){
		return service.findAnyMatch(nombrebusqueda);
	}
	
}
