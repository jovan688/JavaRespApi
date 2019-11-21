package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name ="lenguaje")
public class Lenguaje implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;
	
	@Column(name = "date")
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date date;
	
	@ManyToMany
	@JoinTable(name = "profesores_lenguajes",
		joinColumns = @JoinColumn(name = "lenguaje_id", referencedColumnName = "id")
		,inverseJoinColumns = @JoinColumn(name ="profesor_id", referencedColumnName = "id" ) 
	)
	private Set<Profesor> profesores= new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(Set<Profesor> profesores) {
		this.profesores = profesores;
	}
	
	
	
}
