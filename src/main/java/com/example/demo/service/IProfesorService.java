package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Profesor;

public interface IProfesorService {

	public List<Profesor> findAll();
	
	public void save(Profesor profesor);
	
	public Profesor findProfesor( Profesor profesor);
	
	public Profesor checkProfesorLogin( Profesor profesor);
	
	public void deleteProfesor(Profesor profesor);
	
	public Profesor updateProfesor(Profesor profesor);
	
	public Optional<Profesor> findProfesorById(long id);
	
	public void deleteProfesor(Long id);
	
	public void deleteAll();
	
	public Profesor findById(Long id);
	
	public Profesor findBySQl(Long id);
	
	public Optional<Profesor> findAnyNombre();
	
	public List<Profesor> findAnyMatch(String nombrebusqueda);
	
}
