package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    // JUnit test for find By Name Or Description
    @Test
    @DisplayName("JUnit test for find By Name Or Description")
    public void givenProductNameAndDescription_whenFindByNameOrDescription_thenProductList() {

        // given - precondition or setup
        String name = "product 1";
        String description = "product 2 description";

        // when - action or the behavior that we are going test
        List<Product> products = productRepository.findByNameOrDescription(name,description);

        // then - verify the output
        products.forEach(System.out::println);
        Assertions.assertThat(products).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(2);

    }

    // JUnit test for find By Name And Description
    @Test
    @DisplayName("JUnit test for find By Name Or Description")
    public void givenProductNameAndDescription_whenFindByNameAndDescription_thenProductList() {

        // given - precondition or setup
        String name = "product 1";
        String description = "product 1 description";

        // when - action or the behavior that we are going test
        List<Product> products = productRepository.findByNameAndDescription(name,description);

        // then - verify the output
        products.forEach(System.out::println);
        Assertions.assertThat(products).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(1);

    }

}
