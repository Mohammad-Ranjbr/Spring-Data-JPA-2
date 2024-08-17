package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// All the methods of this interface are implemented in SimpleJpaRepository and all of them are transactional.
// There is no need to put this annotation for these methods. Also, there is no need
// to put @Repository annotation because SimpleJpaRepository is marked with this annotation.git

// all the methods belongs to JPA Repository Interface and all these
// methods internally uses entity manager API (JPA) to perform different operations with that database.

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);
    List<Product> findByNameOrDescription(String name, String description);
    List<Product> findByNameAndDescription(String name, String description);
    Product findDistinctByName(String name);
    List<Product> findByPriceGreaterThan(BigDecimal price);
    List<Product> findByPriceLessThan(BigDecimal price);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameLike(String name);
    List<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);
    List<Product> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Product> findByNameIn(List<String> names);
    List<Product> findFirst2ByOrderByNameAsc();
    List<Product> findTop3ByOrderByPriceDesc();

    // JPQL stands for the Java Persistence Query Language. It is de ned in the JPA specification
    // and is an object-oriented query language used to perform database operations on persistent entities.
    // Hibernate, or any other JPA implementation, has to transform the JPQL query into SQL. The syntax
    // of a JPQL FROM clause is similar to SQL but uses the entity model instead of table or column names.
    // It can be used with any type of relation database.

    @Query("select p from Product p where p.name = ?1 or p.description = ?2")
    List<Product> findByNameOrDescriptionJPQLIndexParam(String name, String description);

    @Query("select p from Product p where p.name =:name or p.description =:description")
    List<Product> findByNameOrDescriptionJPQLNamedParam(@Param("name") String name, @Param("description") String description);

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
