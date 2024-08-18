package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
public class PaginationAndSortingTest {

    private final ProductRepository productRepository;

    @Autowired
    public PaginationAndSortingTest(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    // JUnit test for
    @Test
    @DisplayName("")
    public void givenPageNumberAndPageSize_whenFindAll_thenReturnProductPage() {

        // given - precondition or setup
        int pageNo = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNo,pageSize);

        // when - action or the behavior that we are going test
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> products = productPage.getContent();

        // then - verify the output
        products.forEach(System.out::println);
        Assertions.assertThat(products).isNotNull();
        System.out.println("Total Pages: " + productPage.getTotalPages());
        System.out.println("Total Elements: " + productPage.getTotalElements());
        System.out.println("Number Of Elements: " + productPage.getNumberOfElements());
        System.out.println("Size: " + productPage.getSize());
        System.out.println("Page Number: " + productPage.getNumber());
        System.out.println("Last Page: " + productPage.isLast());
        System.out.println("First Page: " + productPage.isFirst());

    }

}
