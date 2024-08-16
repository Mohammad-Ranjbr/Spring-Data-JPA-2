package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Objects;

@SpringBootTest
class ProductRepositoryTest {

    private final ProductRepository productRepository;

    @Autowired
    public ProductRepositoryTest(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    // JUnit test for save product
    @Test
    @DisplayName("JUnit test for save product")
    public void givenProductObject_whenSAve_thenReturnProductObject() {

        // given - precondition or setup
        Product product = Product.builder()
                .name("product 1")
                .description("product 1 description")
                .sku("100ABC")
                .price(new BigDecimal(100))
                .active(true)
                .imageUrl("product1.png")
                .build();

        // when - action or the behavior that we are going test
        Product savedProduct = productRepository.save(product);

        // then - verify the output
        System.out.println(savedProduct);
        System.out.println(savedProduct.getId());
        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getSku()).isEqualTo(product.getSku());
        Assertions.assertThat(savedProduct.getName()).isEqualTo(product.getName());
        Assertions.assertThat(savedProduct.getPrice()).isEqualTo(product.getPrice());
        Assertions.assertThat(savedProduct.getImageUrl()).isEqualTo(product.getImageUrl());
        Assertions.assertThat(savedProduct.getDescription()).isEqualTo(product.getDescription());

    }

    // JUnit test for update product
    @Test
    @DisplayName("JUnit test for update product")
    public void givenProductObject_whenUpdate_thenReturnUpdatedProductObject() {

        // given - precondition or setup
        Long id = 1L;
        Product product = null;
        if(productRepository.findById(id).isPresent()){
            product = productRepository.findById(id).get();
            product.setName("updated product 1");
            product.setDescription("updated product 1 description");
        }

        // when - action or the behavior that we are going test
        Product updatedProduct = productRepository.save(Objects.requireNonNull(product));

        // then - verify the output
        System.out.println(updatedProduct);
        System.out.println(updatedProduct.getId());
        Assertions.assertThat(updatedProduct.getName()).isEqualTo(product.getName());
        Assertions.assertThat(updatedProduct.getDescription()).isEqualTo(product.getDescription());

    }

}