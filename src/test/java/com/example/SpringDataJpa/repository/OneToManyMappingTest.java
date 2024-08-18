package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Address;
import com.example.SpringDataJpa.entity.Order;
import com.example.SpringDataJpa.entity.OrderItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;

@SpringBootTest
public class OneToManyMappingTest {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OneToManyMappingTest(OrderRepository orderRepository, ProductRepository productRepository){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // JUnit test for save order (Persist Cascade Type)
    @Test
    @DisplayName("JUnit test for save order (Persist Cascade Type)")
    public void givenOrderAndOrderItemObject_whenSaveOrder_thenSaveOrderAndOrderItem() {

        // given - precondition or setup
        Order order = Order.builder()
                .orderTrackingNumber("1000ABC")
                .totalQuantity(2)
                .status("IN PROGRESS")
                .build();

        OrderItem orderItem1 = OrderItem.builder()
                .quantity(2)
                .imageUrl("image1.png")
                .product(productRepository.findById(1L).get())
                .build();
        orderItem1.setPrice(orderItem1.getProduct().getPrice().multiply(new BigDecimal(2)));
        OrderItem orderItem2 = OrderItem.builder()
                .quantity(3)
                .imageUrl("image2.png")
                .product(productRepository.findById(2L).get())
                .build();
        orderItem2.setPrice(orderItem2.getProduct().getPrice().multiply(new BigDecimal(3)));

        order.setOrderItems(Set.of(orderItem1,orderItem2));

        Address address = Address.builder()
                .city("Tehran")
                .street("Azadi")
                .state("Najah")
                .country("Iran")
                .zipCode("14142525")
                .build();

        address.setOrder(order);
        order.setBillingAddress(address);
        order.setTotalPrice(order.getTotalPrice());

        // when - action or the behavior that we are going test
        Order savedOrder = orderRepository.save(order);

        // then - verify the output
        Assertions.assertThat(savedOrder).isNotNull();

    }

}
