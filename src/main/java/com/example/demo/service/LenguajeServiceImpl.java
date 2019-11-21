package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ILenguajeDao;
import com.example.demo.entity.Lenguaje;

@Service
public class LenguajeServiceImpl implements ILenguajeService {

	@Autowired
	private ILenguajeDao lenguajeDao;
	
	@Override
	@Transactional
	public List<Lenguaje> findAll() {
		return (List<Lenguaje>) lenguajeDao.findAll();
	}

	@Override
	@Transactional
	public void saveLenguaje(Lenguaje lenguaje) {
		lenguajeDao.save(lenguaje);
	}

	@Override
	public Lenguaje findLengua(long id) {
		return lenguajeDao.findByIdSQL(id);
	}

}
