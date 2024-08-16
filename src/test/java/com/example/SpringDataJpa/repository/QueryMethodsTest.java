package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QueryMethodsTest {

    private final ProductRepository productRepository;

    @Autowired
    public QueryMethodsTest(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    // JUnit test for find by name method
    @Test
    @DisplayName("JUnit test for find by name method")
    public void givenProductName_whenFindByName_thenReturnProductObject() {

        // given - precondition or setup
        String productName = "product 1";

        // when - action or the behavior that we are going test
        Product product = productRepository.findByName(productName);

        // then - verify the output
        System.out.println(product);
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getName()).isEqualTo("product 1");

    }

}
