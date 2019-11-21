package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Curso")
public class Curso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long curso_id;
	
	private String nombre;
	
	@Column(name = "profesor_id")
	private long profesorId;

	
	public Curso() {
		
		
	}

	public long getCurso_id() {
		return curso_id;
	}

	public void setCurso_id(long curso_id) {
		this.curso_id = curso_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getProfesorId() {
		return profesorId;
	}

	public void setProfesorId(long profesorId) {
		this.profesorId = profesorId;
	}


	
	
}
