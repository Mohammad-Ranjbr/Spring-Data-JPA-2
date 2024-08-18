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
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PaginationAndSortingTest {

    private final ProductRepository productRepository;

    @Autowired
    public PaginationAndSortingTest(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    // JUnit test for paging
    @Test
    @DisplayName("JUnit test for paging")
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

    // JUnit test for sorting
    @Test
    @DisplayName("JUnit test for sorting")
    public void givenSortByAndSortDir_whenFindAll_thenReturnProductPage() {

        // given - precondition or setup
        String sortBy = "price";
        String sortDir = "desc";
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // when - action or the behavior that we are going test
        List<Product> products = productRepository.findAll(sort);

        // then - verify the output
        products.forEach(System.out::println);
        Assertions.assertThat(products).isNotNull();

    }

    // JUnit test for sorting with multiple fields
    @Test
    @DisplayName("JUnit test for sorting with multiple fields")
    public void givenSortByFieldsAndSortDir_whenFindAll_thenReturnProductList() {

        // given - precondition or setup
        String sortPrice = "price";
        String sortName = "name";
        String sortDir = "desc";
        Sort sortByName = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortName).ascending() : Sort.by(sortName).descending();
        Sort sortByPrice = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortPrice).ascending() : Sort.by(sortPrice).descending();
        Sort groupBySort = sortByName.and(sortByPrice);

        // when - action or the behavior that we are going test
        List<Product> products = productRepository.findAll(groupBySort);

        // then - verify the output
        products.forEach(System.out::println);
        Assertions.assertThat(products).isNotNull();

    }

    // JUnit test for pagination and sorting together
    @Test
    @DisplayName("JUnit test for pagination and sorting together")
    public void givenPageableAndSort_whenFindAll_thenReturnProductList() {

        // given - precondition or setup
        int pageNo = 0;
        int pageSize = 5;
        String sortDir = "desc";
        String sortBy = "price";

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

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
