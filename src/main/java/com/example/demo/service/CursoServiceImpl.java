package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ICursoDao;
import com.example.demo.entity.Curso;

@Service
public class CursoServiceImpl implements ICursoService {

	@Autowired
	ICursoDao cursoDao;
	
	@Override
	public List<Curso> findAll() {
		return (List<Curso>) cursoDao.findAll();
	}

	@Override
	public void saveCurso(Curso curso) {
		cursoDao.save(curso);
	}

	@Override
	public List<Curso> getCursosByProfesorId(long id) {
		return cursoDao.getCursosByProfesorId(id);
	}

}
