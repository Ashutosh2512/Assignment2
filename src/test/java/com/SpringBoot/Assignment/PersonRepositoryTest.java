package com.SpringBoot.Assignment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.SpringBoot.Assignment.Repository.PersonRepository;
import com.SpringBoot.Assignment.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository pr;
	
	@Test
	@Order(1)
	@DisplayName("Test for findById method of the repository interface")
	public void findByIdTest() throws ParseException {
		Optional<Person> person=pr.findById(1);
		assert(person).equals(Optional.empty());
	}
	
	@Test
	@Order(2)
	@DisplayName("Test for findAll method of the repository interface")
	public void findAllTest() throws ParseException {
		List<Person> persons=pr.findAll();
		assert((Integer)persons.size()).equals(0);
	}
	
	@Test
	@Order(3)
	@DisplayName("Test for save method of the repository interface")
	public void saveTest() throws ParseException {
		Person person=new Person();
		person.setAvatar("GRAVAVATAR");
		person.setCountry("INDIA");
		person.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2002-01-11T18:30:00.000+00:00"));
		person.setEmail("abc@xyz.com");
		person.setId(1);
		person.setName("ABCD");
		Person p=pr.save(person);
		assert(p.getName()).equals(person.getName());
		assert(p.getEmail()).equals(person.getEmail());
		assert(p.getDob()).equals(person.getDob());
		assert(p.getAvatar()).equals(person.getAvatar());
		assert((Integer)p.getId()).equals(person.getId());
		assert(p.getCountry()).equals(person.getCountry());
	}
}
