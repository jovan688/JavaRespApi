package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IProfesorDao;
import com.example.demo.entity.Profesor;

@Service
public class ProfesorServiceImpl implements IProfesorService {

	@Autowired
	private IProfesorDao profesorDao;
	

	@Override
	@Transactional(readOnly = true ) 
	public List<Profesor> findAll() {
		return (List<Profesor>) profesorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true ) 
	public Profesor findProfesor(Profesor profesor) {
		return (Profesor) profesorDao.findByEmail(profesor.getEmail());
	}

	@Override
	@Transactional(readOnly = true ) 
	public Profesor checkProfesorLogin(Profesor profesor) {
		return profesorDao.findByEmailAndPassword(profesor.getEmail(), profesor.getPassword());
	}

	@Override
	@Transactional
	public void deleteProfesor(Profesor profesor) {
		profesorDao.deleteById(profesor.getId());
	}

	@Override
	@Transactional
	public Profesor updateProfesor(Profesor profesor) {
		return profesorDao.save(profesor);
	}

	@Override
	@Transactional(readOnly = true ) 
	public Optional<Profesor> findProfesorById(long id) {
		return (Optional<Profesor>) profesorDao.findById(id);
	}

	@Override
	@Transactional
	public void deleteProfesor(Long id) {
		profesorDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true ) 
	public Profesor findById(Long id) {
		return profesorDao.findById(id).orElse(null);
	}

	@Override
	public Profesor findBySQl(Long id) {
		return profesorDao.findbyIdSQl(id);
	}

	@Override
	@Transactional
	public void save(Profesor profesor) {
		profesorDao.save(profesor);	
	}

	@Override
	public Optional<Profesor>  findAnyNombre() {
		
		return ((List<Profesor>) profesorDao.findAll()).stream().findAny();
	}

	@Override
	public List<Profesor> findAnyMatch(String nombrebusqueda) {
		
		return ((List<Profesor>) profesorDao.findAll())
				.stream()
				.filter(x -> x.getNombre().contains(nombrebusqueda))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void deleteAll() {
		profesorDao.deleteAll();
	}


}
