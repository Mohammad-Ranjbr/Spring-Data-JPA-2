package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Product;
import com.example.SpringDataJpa.entity.ProductCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class ProductCategoryRepositoryTest {

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryRepositoryTest(ProductCategoryRepository productCategoryRepository){
        this.productCategoryRepository = productCategoryRepository;
    }

    // JUnit test for save product category
    @Test
    @DisplayName("JUnit test for save product category")
    public void givenProductCategoryAndProductObject_whenSaveProductCategory_thenSavedBoth() {

        // given - precondition or setup
        ProductCategory productCategory = ProductCategory.builder()
                .categoryName("book")
                .categoryDescription("book description")
                .build();

        Product product1 = Product.builder()
                .name("Spring Security In Action")
                .price(new BigDecimal(100))
                .imageUrl("ss.png")
                .sku("ABCD")
                .active(true)
                .productCategory(productCategory)
                .build();

        Product product2 = Product.builder()
                .name("Spring Boot In Action")
                .price(new BigDecimal(99))
                .imageUrl("sb.png")
                .sku("ABCD100")
                .active(true)
                .productCategory(productCategory)
                .build();

        productCategory.setProducts(List.of(product1,product2));

        // when - action or the behavior that we are going test
        ProductCategory savedProductCategory = productCategoryRepository.save(productCategory);

        // then - verify the output
        Assertions.assertThat(savedProductCategory).isNotNull();

    }

}