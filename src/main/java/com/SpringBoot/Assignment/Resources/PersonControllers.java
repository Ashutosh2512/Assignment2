package com.SpringBoot.Assignment.Resources;

import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.Assignment.Service.PersonService;
import com.SpringBoot.Assignment.model.Person;

@RestController
public class PersonControllers {
	
	@Autowired
	private PersonService ps;
	
	@GetMapping(path = "/api/People/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable int id) {
		Person p;
		try {
			p=ps.getPerson(id);
		}catch(Exception e) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(p, HttpStatus.OK);
	}
	
	@GetMapping(path="/api/People")
	public ResponseEntity<List<Person>> getAllPerson(){
		return new ResponseEntity<List<Person>>(ps.getAllPersons(),HttpStatus.OK);
	}
	
	@PostMapping(path="/api/People")
	public ResponseEntity<Person> addPerson(@RequestBody Person person,@RequestHeader("User-Agent")  String useragent){
		try {
			ps.addPerson(person);
		}catch(Exception e){
			return new ResponseEntity<Person>(HttpStatus.IM_USED);
		}
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	
	@PatchMapping(path="/api/People/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable("id") Integer id,@RequestBody Person p,@RequestHeader("User-Agent")  String useragent){
		
		try {
			ps.updatePerson(p,id);
		}catch(Exception e) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(p, HttpStatus.OK);
	}

}
