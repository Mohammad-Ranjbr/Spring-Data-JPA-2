package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// All the methods of this interface are implemented in SimpleJpaRepository and all of them are transactional.
// There is no need to put this annotation for these methods. Also, there is no need
// to put @Repository annotation because SimpleJpaRepository is marked with this annotation.

// all the methods belongs to JPA Repository Interface and all these
// methods internally uses entity manager API (JPA) to perform different operations with that database.
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // save() :
    // This method is accessible through the CrudRepository interface. When you save an entity using the save() method,
    // Spring Data JPA uses the EntityManager behind the scenes to perform the save operation.
    // If the entity has not yet been saved (the entity is new): Spring Data JPA uses the persist()
    // method on the EntityManager to save the new entity to the database.
    // If the entity has already been saved (the entity exists): Spring Data JPA uses the merge() method
    // on the EntityManager to match the changes made to the entity with the existing entity in the database.
    // In other words, the save() method can be used both to create a new record in the database and to update an existing record.

}
