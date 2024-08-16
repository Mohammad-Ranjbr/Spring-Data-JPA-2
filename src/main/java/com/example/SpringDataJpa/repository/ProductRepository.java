package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// All the methods of this interface are implemented in SimpleJpaRepository and all of them are transactional.
// There is no need to put this annotation for these methods. Also, there is no need
// to put @Repository annotation because SimpleJpaRepository is marked with this annotation.

// all the methods belongs to JPA Repository Interface and all these
// methods internally uses entity manager API (JPA) to perform different operations with that database.

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);
    List<Product> findByNameOrDescription(String name, String description);
    List<Product> findByNameAndDescription(String name, String description);

    // save() :
    // This method is accessible through the CrudRepository interface. When you save an entity using the save() method,
    // Spring Data JPA uses the EntityManager behind the scenes to perform the save operation.
    // If the entity has not yet been saved (the entity is new): Spring Data JPA uses the persist()
    // method on the EntityManager to save the new entity to the database.
    // If the entity has already been saved (the entity exists): Spring Data JPA uses the merge() method
    // on the EntityManager to match the changes made to the entity with the existing entity in the database.
    // In other words, the save() method can be used both to create a new record in the database and to update an existing record.

    // The JPA Criteria API is an API in the Java Persistence API (JPA) that allows you to create dynamic and type-safe
    // queries programmatically without the need to write JPQL (Java Persistence Query Language) code directly.
    // (Spring Data JPA is a layer above JPA and uses all its APIs)
    // How to use Query Method :
    // 1- We write Query Method using Spring Data JPA
    // 2- Spring Data JPA parse Query method and creates JPA Criteria :
    // Parsing Query Method Names:
    // Subject: The first part of the method name, such as find...By or exists...By, specifies the subject of the query. For example:
    // findBy: seeks to find (find) entities with specified conditions.
    // existsBy: Checks whether an entity with the specified condition exists.
    // Predicate: The second part of the method name forms the query conditions. This part shows the filters that should be applied in the query.
    // For example, if a method named findByNameAndAge is defined, Spring Data JPA understands this as a query that filters entities by name and age.
    // 3- JPA criteria creates JPQL query and executes it : Finally, the created query is executed using JPQL to retrieve or check the desired results from the database.
    // Hibernate (JPA provider will translate JPQL query into SQL query and execute with respect to the database)
    // Query Method Structure : public <Query-method-return-type> Query-method-Subject-keyword--Query-method-Predicate-keyword(Method parameters);

    // Returning Values From Query Methods
    // A query method can return only one result or more than one result.
    // 1. if we are writing a query that should return only one result, we can return the following types:
    // • Basic type. Our query method will return the found basic type or null.
    // • Entity. Our query method will return an entity object or null.
    // • Guava / Java 8 Optional<T>. Our query method will return an Optional that contains the found object or an empty Optional.
    // 2. if we are writing a query method that should return more than one result, we can return the following types:
    // • List<T>. Our query method will return a list that contains the query results or an empty list.
    // • Stream<T>. Our query method will return a Stream that can be used to access the query results or an empty Stream.

}
