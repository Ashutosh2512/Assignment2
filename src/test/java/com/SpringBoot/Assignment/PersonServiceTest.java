package com.SpringBoot.Assignment;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.SpringBoot.Assignment.Repository.PersonRepository;
import com.SpringBoot.Assignment.Service.PersonService;
import com.SpringBoot.Assignment.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {

	@MockBean
	private PersonRepository pr;
	
	
	@InjectMocks
	private PersonService ps;
	
	
	@Test
	@DisplayName("Test for getPerson method)")
	public void getPersonTest() throws Exception {
		ps=new PersonService(pr);
		Person person=new Person();
		person.setAvatar("GRAVAVATAR");
		person.setCountry("INDIA");
		person.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2002-01-11T18:30:00.000+00:00"));
		person.setEmail("abc@xyz.com");
		person.setId(1);
		person.setName("ABCD");
		Mockito.when(pr.findById(person.getId())).thenReturn(Optional.of(person));
		Person p=ps.getPerson(1);
		assert(p.getName()).equals(person.getName());
		assert(p.getAvatar()).equals(person.getAvatar());
		assert(p.getCountry()).equals(person.getCountry());
		assert(p.getDob()).equals(person.getDob());
		assert(p.getEmail()).equals(person.getEmail());
	}
	
	@Test
	@DisplayName("Test for getAllPerson()")
	public void getAllPersonsTest() throws ParseException {
		ps=new PersonService(pr);
		Person person=new Person();
		person.setAvatar("GRAVAVATAR");
		person.setCountry("INDIA");
		person.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2002-01-11T18:30:00.000+00:00"));
		person.setEmail("abc@xyz.com");
		person.setId(1);
		person.setName("ABCD");
		ArrayList<Person> list=new ArrayList<Person>();
		list.add(person);
		Mockito.when(pr.findAll()).thenReturn(list);
		List<Person> response=ps.getAllPersons();
		assert((Integer)response.size()).equals(1);
		assert(response.get(0).getName()).equals(person.getName());
		assert(response.get(0).getAvatar()).equals(person.getAvatar());
		assert(response.get(0).getCountry()).equals(person.getCountry());
		assert(response.get(0).getDob()).equals(person.getDob());
		assert(response.get(0).getEmail()).equals(person.getEmail());
	}
	
	@Test
	@DisplayName("Test for updatePerson method")
	public void updatePersonTest() throws Exception {
		ps=new PersonService(pr);
		Person person=new Person();
		person.setAvatar("GRAVAVATAR");
		person.setCountry("INDIA");
		person.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2002-01-11T18:30:00.000+00:00"));
		person.setEmail("abc@xyz.com");
		person.setId(1);
		person.setName("ABCD");
		Mockito.when(pr.save(person)).thenReturn(person);
		Mockito.when(pr.findById(1)).thenReturn(Optional.of(person));
		Person response=ps.updatePerson(person, 1);
		assert(response.getName()).equals(person.getName());
		assert(response.getAvatar()).equals(person.getAvatar());
		assert(response.getCountry()).equals(person.getCountry());
		assert(response.getDob()).equals(person.getDob());
		assert(response.getEmail()).equals(person.getEmail());
	}
	
	@Test
	@DisplayName("Test for addPerson method")
	public void addPersonTest() throws Exception {
		ps=new PersonService(pr);
		Person person=new Person();
		person.setAvatar("GRAVAVATAR");
		person.setCountry("INDIA");
		person.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2002-01-11T18:30:00.000+00:00"));
		person.setEmail("abc@xyz.com");
		person.setId(1);
		person.setName("ABCD");
		Mockito.when(pr.save(person)).thenReturn(person);
		Mockito.when(pr.findById(1)).thenReturn(null);
		Person response=ps.addPerson(person);
		assert(response.getName()).equals(person.getName());
		assert(response.getAvatar()).equals(person.getAvatar());
		assert(response.getCountry()).equals(person.getCountry());
		assert(response.getDob()).equals(person.getDob());
		assert(response.getEmail()).equals(person.getEmail());
	}
	
}
