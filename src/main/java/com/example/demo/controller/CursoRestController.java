package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Curso;
import com.example.demo.entity.DummyClass;
import com.example.demo.entity.Profesor;
import com.example.demo.service.ICursoService;
import com.example.demo.service.IProfesorService;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@RequestMapping("/api")
public class CursoRestController {

	@Autowired
	private ICursoService service;
	
	@Autowired
	private IProfesorService profesorservice;
	
	@GetMapping("/cursos")
	public ResponseEntity<?> listaCursos()
	{
		List<Curso> cursos = service.findAll();
		if(cursos.isEmpty())
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(cursos,HttpStatus.OK);
		
		
	}
	
	@PostMapping("/crear_curso")
	public ResponseEntity<?> agregarCurso(@RequestBody Curso curso)
	{ 
		Profesor profesordb= profesorservice.findById(curso.getProfesorId());
		if(profesordb == null)
			return new ResponseEntity<>("No existe el profesor",HttpStatus.NOT_FOUND);
		
		service.saveCurso(curso);
		
		return new ResponseEntity<>(curso,HttpStatus.CREATED);
	}
	
	@PostMapping("/cursos_profesor")
	public ResponseEntity<?> verCursosProfesor(@RequestBody Profesor	 profesor)
	{
		
		List<Curso> cursos = service.getCursosByProfesorId(profesor.getId());
		if(cursos.size()==0)
			return new ResponseEntity<>(cursos,HttpStatus.NOT_FOUND);
		
		
		return new ResponseEntity<>(cursos,HttpStatus.CREATED);
	}
	
	@GetMapping("obtenerAverage")
	public ResponseEntity<?> obtenerAverage(@RequestHeader int...avrgs )
	{
		if(avrgs.length == 0) 
			return new ResponseEntity<>(Optional.empty(),HttpStatus.CREATED);
		
		int sum = 0;
		for (int i : avrgs) {
			sum+= i;
		}
		
		return new ResponseEntity<>(Optional.of(((double)sum/avrgs.length)),HttpStatus.CREATED);
	}
	
	@GetMapping("filtrarPorId")
	public ResponseEntity<?> filtrarById( )
	{
		List<DummyClass> list = null;
		
		
		list = getValues()
		.stream()
		.filter(m -> m.getAmount() > 50)
		.filter(m-> m.isActive())
		
		.collect(Collectors.toList());
		
		return new ResponseEntity<>(list,HttpStatus.CREATED);
	}
	
	
	
	public List<DummyClass> getValues(){
		
		List<DummyClass> list = new ArrayList<DummyClass>();
		
		list.add(new DummyClass( 1,"Jhon Mack",30.55,true));
		list.add(new DummyClass( 5,"Pagie Psman",32.88,true));
		list.add(new DummyClass( 6,"Jhon Mack",69.69,false));
		list.add(new DummyClass( 5,"Celia Swam",100.66,true));
		list.add(new DummyClass( 8,"White Black",10.55,false));
		list.add(new DummyClass( 6,"Pap Switch",85.55,false));
		list.add(new DummyClass( 7,"Isla Patrick",39.55,true));
		list.add(new DummyClass( 4,"Joe Migcg",60.55,false));
		list.add(new DummyClass( 6,"Patrice Mack",78.65,false));
		list.add(new DummyClass( 9,"Ellio Pawr",99.95,true));
		list.add(new DummyClass( 5,"Lite Sowan",86.85,true));
		list.add(new DummyClass( 9,"Loire Lag",10.95,false));
		list.add(new DummyClass( 2,"Karol Blah",40.55,false));
		list.add(new DummyClass( 5,"Joana Smith",77.55,true));
		list.add(new DummyClass( 5,"",77.55,true));
		
		
		
		return list;
	}
	/*
	@GetMapping("/footballapi")
	public ResponseEntity<?> getfootball()
	{
		Response response =  null;

		Proxy proxy = new
		OkHttpClient.Builder builder = new OkHttpClient.Builder().proxy(proxy);
		OkHttpClient client = builder.build();
		Request request = new Request.Builder()
						.url("https://api-football-v1.p.rapidapi.com/v2/predictions/157462")
						.addHeader("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
						.addHeader("x-rapidapi-key", "20fa1ae9d9msh56fb69391023c04p1d8e92jsnd118b1606a3c")
						.get()
						.build();
		try 
		{
			//Proxy proxy = new 
			response = client.newCall(request).execute();
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		//return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}*/
	
}
