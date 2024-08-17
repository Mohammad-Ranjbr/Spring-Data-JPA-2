package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    // JUnit test for find Distinct By Name
    @Test
    @DisplayName("JUnit test for find Distinct By Name")
    public void givenProductName_whenFindDistinctByName_thenReturnProductObject() {

        // given - precondition or setup
        String name = "product 1";

        // when - action or the behavior that we are going test
        Product product = productRepository.findDistinctByName(name);

        // then - verify the output
        System.out.println(product);
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getName()).isEqualTo("product 1");

    }

    // JUnit test for findBy Price Greater Than
    @Test
    @DisplayName("JUnit test for findBy Price Greater Than")
    public void givenProductPrice_whenFindByPriceGreaterThan_thenReturnProductList() {

        // given - precondition or setup
        BigDecimal price = new BigDecimal(100);

        // when - action or the behavior that we are going test
        List<Product> products = productRepository.findByPriceGreaterThan(price);

        // then - verify the output
        System.out.println(products);
        Assertions.assertThat(products).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(2);

    }

    // JUnit test for findBy Price Less Than
    @Test
    @DisplayName("JUnit test for findBy Price Less Than")
    public void givenProductPrice_whenFindByPriceLessThan_thenReturnProductList() {

        // given - precondition or setup
        BigDecimal price = new BigDecimal(120);

        // when - action or the behavior that we are going test
        List<Product> products = productRepository.findByPriceLessThan(price);

        // then - verify the output
        System.out.println(products);
        Assertions.assertThat(products).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(2);

    }

    // JUnit test for find By Name Containing
    @Test
    @DisplayName("JUnit test for find By Name Containing")
    public void givenProductName_whenFindByNameContaining_thenReturnProductList() {

        // given - precondition or setup
        String name = "product";

        // when - action or the behavior that we are going test
        List<Product> products = productRepository.findByNameContaining(name); // containing -> %name%

        // then - verify the output
        System.out.println(products);
        Assertions.assertThat(products).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(3);

    }

    // JUnit test for find By Name Like
    @Test
    @DisplayName("JUnit test for find By Name Like")
    public void givenProductName_whenFindByNameLike_thenReturnProductList() {

        // given - precondition or setup
        String name = "%product%";

        // when - action or the behavior that we are going test
        List<Product> products = productRepository.findByNameLike(name);

        // then - verify the output
        System.out.println(products);
        Assertions.assertThat(products).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(3);

    }

    // JUnit test for find By Price Between
    @Test
    @DisplayName("JUnit test for find By Price Between")
    public void givenPriceRange_whenFindByPriceBetween_thenReturnProductList() {

        // given - precondition or setup
        BigDecimal startPrice = new BigDecimal(100);
        BigDecimal endPrice = new BigDecimal(120);

        // when - action or the behavior that we are going test
        List<Product> products = productRepository.findByPriceBetween(startPrice,endPrice); // between -> ? =< price =< ?

        // then - verify the output
        System.out.println(products);
        Assertions.assertThat(products).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(3);

    }

    // JUnit test for find By DateCreated Between
    @Test
    @DisplayName("JUnit test for find By DateCreated Between")
    public void givenDateCreatedRange_whenFindByDateCreatedBetween_thenReturnProductList() {

        // given - precondition or setup
        LocalDateTime startDate = LocalDateTime.of(2024,8,17,10,0,7);
        LocalDateTime endDate = LocalDateTime.of(2024,8,17,10,0,14);

        // when - action or the behavior that we are going test
        List<Product> products = productRepository.findByDateCreatedBetween(startDate,endDate);

        // then - verify the output
        System.out.println(products);
        Assertions.assertThat(products).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(3);

    }

}
