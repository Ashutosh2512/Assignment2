package com.SpringBoot.Assignment;


import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.SpringBoot.Assignment.Resources.PersonControllers;
import com.SpringBoot.Assignment.Service.PersonService;
import com.SpringBoot.Assignment.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonControllers.class)
public class PersonControllerTest {

	@MockBean
	private PersonService ps;
	
	@Autowired
	private MockMvc mockmvc;
	
	ObjectMapper mapper=new ObjectMapper();
	
	@Test
	@DisplayName("Test for Http GET request on path /api/People/{id}")
	public void getPersonTest() throws Exception {
		Person person=new Person();
		person.setAvatar("GRAVAVATAR");
		person.setCountry("INDIA");
		person.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2002-01-11T18:30:00.000+00:00"));
		person.setEmail("abc@xyz.com");
		person.setId(1);
		person.setName("ABCD");
		Mockito.when(ps.getPerson(1)).thenReturn(person);

		mockmvc.perform(MockMvcRequestBuilders.get("/api/People/1")).andDo(print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		 .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(person.getName()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(person.getEmail()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.dob").value("2002-01-10T18:30:00.000+00:00"))
		 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(person.getId()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$.avatar").value(person.getAvatar()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$.country").value(person.getCountry()));
	}
	
	@Test
	@DisplayName("Test for Http GET request on path /api/People")
	public void getAllPersonTest() throws Exception {
		Person person1=new Person();
		person1.setAvatar("GRAVAVATAR");
		person1.setCountry("INDIA");
		person1.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2002-01-11T18:30:00.000+00:00"));
		person1.setEmail("abc@xyz.com");
		person1.setId(1);
		person1.setName("ABCD");
		Person person2=new Person();
		person2.setAvatar("GRAVAVATAR");
		person2.setCountry("INDIA");
		person2.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2002-01-11T18:30:00.000+00:00"));
		person2.setEmail("abc@xyz.com");
		person2.setId(1);
		person2.setName("ABCD");
		Mockito.when(ps.getAllPersons()).thenReturn(Arrays.asList(person1,person2));
		
		mockmvc.perform(MockMvcRequestBuilders.get("/api/People")).andDo(print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
		 .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(person1.getName()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value(person1.getEmail()))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].dob").value("2002-01-10T18:30:00.000+00:00"))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(person1.getId()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[0].avatar").value(person1.getAvatar()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[0].country").value(person1.getCountry()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(person1.getName()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value(person1.getEmail()))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].dob").value("2002-01-10T18:30:00.000+00:00"))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(person1.getId()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[1].avatar").value(person1.getAvatar()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[1].country").value(person1.getCountry()));
	}
	
	@Test
	@DisplayName("Test for Http POST request on path /api/People")
	public void addPersonTest() throws Exception {
		Person person=new Person();
		person.setAvatar("GRAVAVATAR");
		person.setCountry("INDIA");
		person.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2002-01-11T18:30:00.000+00:00"));
		person.setEmail("abc@xyz.com");
		person.setId(1);
		person.setName("ABCD");
		
		Mockito.when(ps.addPerson(person)).thenReturn(person);
		
		mockmvc.perform(MockMvcRequestBuilders.post("/api/People").content(mapper.writeValueAsString(person)).
				header("User-Agent", "googlebot").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		 .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(person.getName()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(person.getEmail()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.dob").value("2002-01-10T18:30:00.000+00:00"))
		 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(person.getId()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$.avatar").value(person.getAvatar()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$.country").value(person.getCountry()));
	}
	
	@Test
	@DisplayName("Test for Http PATCH request on path /api/People/{id}")
	public void updatePersonTest() throws Exception {
		Person person=new Person();
		person.setAvatar("GRAVAVATAR");
		person.setCountry("INDIA");
		person.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2002-01-11T18:30:00.000+00:00"));
		person.setEmail("abc@xyz.com");
		person.setId(1);
		person.setName("ABCD");
		
		Mockito.when(ps.updatePerson(person,1)).thenReturn(person);
		
		mockmvc.perform(MockMvcRequestBuilders.patch("/api/People/1").content(mapper.writeValueAsString(person)).
				header("User-Agent", "googlebot").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		 .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(person.getName()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(person.getEmail()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.dob").value("2002-01-10T18:30:00.000+00:00"))
		 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(person.getId()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$.avatar").value(person.getAvatar()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$.country").value(person.getCountry()));
	}
}
