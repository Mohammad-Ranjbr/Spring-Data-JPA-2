package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JQPLQueryTest {

    private final ProductRepository productRepository;

    @Autowired
    public JQPLQueryTest(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    // JUnit test for find By Name Or Description JPQL Index Param
    @Test
    @DisplayName("JUnit test for find By Name Or Description JPQL Index Param")
    public void givenProductNameAndDescription_whenFindByNameOrDescriptionJPQLIndexParam_thenReturnProductObject() {

        // given - precondition or setup
        String name = "product 1";
        String description = "product 2 description";

        // when - action or the behavior that we are going test
        List<Product> products = productRepository.findByNameOrDescriptionJPQLIndexParam(name,description);

        // then - verify the output
        System.out.println(products);
        Assertions.assertThat(products).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(2);

    }

    // JUnit test for find By Name Or Description JPQL Named Param
    @Test
    @DisplayName("JUnit test for find By Name Or Description JPQL Named Param")
    public void givenProductNameAndDescription_whenFindByNameOrDescriptionJPQLNamedParam_thenReturnProductObject() {

        // given - precondition or setup
        String name = "product 1";
        String description = "product 2 description";

        // when - action or the behavior that we are going test
        List<Product> products = productRepository.findByNameOrDescriptionJPQLNamedParam(name,description);

        // then - verify the output
        System.out.println(products);
        Assertions.assertThat(products).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(2);

    }

    // JUnit test for find By Name Or Description SQL Index Param
    @Test
    @DisplayName("JUnit test for find By Name Or Description SQL Index Param")
    public void givenProductNameAndDescription_whenFindByNameOrDescriptionSQLIndexParam_thenReturnProductObject() {

        // given - precondition or setup
        String name = "product 1";
        String description = "product 2 description";

        // when - action or the behavior that we are going test
        List<Product> products = productRepository.findByNameOrDescriptionSQLIndexParam(name,description);

        // then - verify the output
        System.out.println(products);
        Assertions.assertThat(products).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(2);

    }

}
