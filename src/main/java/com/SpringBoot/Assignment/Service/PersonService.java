package com.SpringBoot.Assignment.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.Assignment.Repository.PersonRepository;
import com.SpringBoot.Assignment.model.Person;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository pr;
	
	public PersonService() {
		
	}
	public PersonService(PersonRepository pr) {
		this.pr = pr;
		
	}
	
	public Person addPerson(Person p) throws Exception {
		Optional<Person> person=pr.findById(p.getId());
		if(person.isPresent()) {
			throw new Exception("Person already Exists");
		}
		pr.save(p);
		return p;
	}
	public Person updatePerson(Person p, int id) throws Exception {
		Optional<Person> person=pr.findById(id);
		if(!person.isPresent()) {
			throw new Exception("Person doesn't Exist");
		}
		p.setId(id);
		pr.save(p);
		return p;
	}
	public Person getPerson(int id) throws Exception {
		Optional<Person> person=pr.findById(id);
		if(!person.isPresent()) {
			throw new Exception("Person doesn't Exist");
		}
		return person.get();
	}
	public List<Person> getAllPersons(){
		return pr.findAll();
	}

}
