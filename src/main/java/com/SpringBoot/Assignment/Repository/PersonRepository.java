package com.SpringBoot.Assignment.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot.Assignment.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	Optional<Person> findById(Integer id);
}
