package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class NamedQueriesTest {

    private final ProductRepository productRepository;

    @Autowired
    public NamedQueriesTest(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    // JUnit test for find By Price Named Query
    @Test
    @DisplayName("JUnit test for find By Price Named Query")
    public void givenProductPrice_whenFindByPrice_thenReturnProductObject() {

        // given - precondition or setup
        BigDecimal price = new BigDecimal(100);

        // when - action or the behavior that we are going test
        Product product = productRepository.findByPrice(price);

        // then - verify the output
        System.out.println(product);
        Assertions.assertThat(product).isNotNull();

    }

    // JUnit test for find All Order By Name Desc
    @Test
    @DisplayName("JUnit test for find All Order By Name Desc")
    public void given_whenFindAllOrderByNameDesc_thenReturnProductList() {

        // given - precondition or setup

        // when - action or the behavior that we are going test
        List<Product> products = productRepository.findAllOrderByNameDesc();

        // then - verify the output
        products.forEach(System.out::println);
        Assertions.assertThat(products).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(3);

    }

    // JUnit test for find By Description
    @Test
    @DisplayName("JUnit test for find By Description")
    public void givenProductDescription_whenFindByDescription_thenReturnProductObject() {

        // given - precondition or setup
        String description = "product 1 description";

        // when - action or the behavior that we are going test
        Product product = productRepository.findByDescription(description);

        // then - verify the output
        System.out.println(product);
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getDescription()).isEqualTo("product 1 description");

    }

    // JUnit test for find All Order By Asc
    @Test
    @DisplayName("JUnit test for find All Order By Asc")
    public void givenProductDescription_whenFindAllOrderByAsc_thenReturnProductObject() {

        // given - precondition or setup

        // when - action or the behavior that we are going test
        List<Product> products = productRepository.findAllOrderByAsc();

        // then - verify the output
        products.forEach(System.out::println);
        Assertions.assertThat(products).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(3);

    }

}
