package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// All the methods of this interface are implemented in SimpleJpaRepository and all of them are transactional.
// There is no need to put this annotation for these methods. Also, there is no need
// to put @Repository annotation because SimpleJpaRepository is marked with this annotation.
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
