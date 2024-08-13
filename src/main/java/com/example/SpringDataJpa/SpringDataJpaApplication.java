package com.example.SpringDataJpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaApplication {

	// Spring Data JPA: Reduce the amount of boilerplate code required to implement data access object (DAO) layer.
	// is an abstraction layer on top of JPA to reduce the amount of boilerplate code required to implement data access object(DAO).
	// JPA: is just a specification that facilitates object relational mapping to manage relational data in java application.
	// JPA is a standard for ORM in Java supported by Hibernate and other implementations.
	// Hibernate: is a JPA implementation , hibernate generates SQL query and executes using JDBC.
	// Hibernate is an ORM (Object-Relational Mapping) that allows Java programmers to work with
	// databases using objects in Java instead of using SQL directly. Hibernate handles the conversion of Java objects to database tables and vice versa.
	// Spring data JPA is not a jpa provider. it simply "hides" the java persistence api (and the jpa provider) behind its repository abstraction
	// Database <- (SQL) -> JDBC Driver -> Hibernate -> JPA -> Spring Data JPA

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

}
