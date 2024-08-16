package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        Assertions.assertThat(updatedProduct).isNotNull();
        Assertions.assertThat(updatedProduct.getName()).isEqualTo(product.getName());
        Assertions.assertThat(updatedProduct.getDescription()).isEqualTo(product.getDescription());

    }

    // JUnit test for find product by id
    @Test
    @DisplayName("JUnit test for find product by id")
    public void givenProductId_whenFindById_thenReturnProductObject() {

        // given - precondition or setup
        Long id = 1L;

        // when - action or the behavior that we are going test
        Product product = null;
        if(productRepository.findById(id).isPresent()){
            product = productRepository.findById(id).get();
        }

        // then - verify the output
        System.out.println(product);
        System.out.println(Objects.requireNonNull(product).getId());
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getName()).isEqualTo("updated product 1");

    }

    // JUnit test for save all method
    @Test
    @DisplayName("JUnit test for save all method")
    public void givenProductList_whenSaveAll_thenReturnProductList() {

        // given - precondition or setup
        Product product1 = Product.builder()
                .name("product 2")
                .description("product 2 description")
                .sku("110ABC")
                .price(new BigDecimal(110))
                .active(true)
                .imageUrl("product2.png")
                .build();
        Product product2 = Product.builder()
                .name("product 3")
                .description("product 3 description")
                .sku("120ABC")
                .price(new BigDecimal(120))
                .active(true)
                .imageUrl("product3.png")
                .build();
        List<Product> products = List.of(product1,product2);

        // when - action or the behavior that we are going test
        productRepository.saveAll(products);

        // then - verify the output
        Assertions.assertThat(productRepository.count()).isEqualTo(3);

    }

    // JUnit test for findAll method
    @Test
    @DisplayName("JUnit test for findAll method")
    public void given_whenFindAll_thenReturnProductList() {

        // given - precondition or setup

        // when - action or the behavior that we are going test
        List<Product> productList = productRepository.findAll();

        // then - verify the output
        productList.forEach((p) -> System.out.println(p.getName()));
        Assertions.assertThat(productList).isNotNull();
        Assertions.assertThat(productList.size()).isEqualTo(3);

    }

    // JUnit test for delete by id
    @Test
    @DisplayName("JUnit test for delete by id")
    public void givenProductId_whenDeleteById_thenDeleted() {

        // given - precondition or setup
        Long id = 1L;

        // when - action or the behavior that we are going test
        productRepository.deleteById(id);
        Optional<Product> product = productRepository.findById(id);

        // then - verify the output
        Assertions.assertThat(product).isEmpty();

    }

    // JUnit test for delete method
    @Test
    @DisplayName("JUnit test for delete method")
    public void givenProductObject_whenDelete_thenDeleted() {

        // given - precondition or setup
        Long id = 4L;
        Product product =null;
        if(productRepository.findById(id).isPresent()){
            product = productRepository.findById(id).get();
        }

        // when - action or the behavior that we are going test
        productRepository.delete(Objects.requireNonNull(product));
        Optional<Product> getProduct = productRepository.findById(id);

        // then - verify the output
        Assertions.assertThat(getProduct).isEmpty();

    }

    // JUnit test for
    @Test
    @DisplayName("")
    public void given_whenDeleteAll_thenDeletedAllProduct() {

        // given - precondition or setup

        // when - action or the behavior that we are going test
        productRepository.deleteAll();
        Long rowCount = productRepository.count();

        // then - verify the output
        Assertions.assertThat(rowCount).isEqualTo(0);

    }

}