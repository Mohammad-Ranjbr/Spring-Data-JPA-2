package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Address;
import com.example.SpringDataJpa.entity.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest
public class OneToOneUnidirectionalMappingTest {

    private final OrderRepository orderRepository;

    @Autowired
    public OneToOneUnidirectionalMappingTest(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    // JUnit test for save order (Persist Cascade Type)
    @Test
    @DisplayName("JUnit test for save order (Persist Cascade Type)")
    public void givenOrderAndAddressObject_whenSaveOrder_thenSaveOrderAndAddress() {

        // given - precondition or setup
        Order order = Order.builder()
                .orderTrackingNumber("1000ABC")
                .totalQuantity(5)
                .status("IN PROGRESS")
                .totalPrice(new BigDecimal(1000))
                .build();

        Address address = Address.builder()
                .city("Tehran")
                .street("Azadi")
                .state("Najah")
                .country("Iran")
                .zipCode("14142525")
                .build();

        order.setBillingAddress(address);

        // when - action or the behavior that we are going test
        Order savedOrder = orderRepository.save(order);

        // then - verify the output
        Assertions.assertThat(savedOrder).isNotNull();

    }

    // JUnit test for update order (Merge Cascade Type)
    @Test
    @DisplayName("JUnit test for update order (Merge Cascade Type)")
    public void givenOrderObject_whenUpdateOrder_thenReturnUpdatedOrderAndAddress() {

        // given - precondition or setup
        Order order = (orderRepository.findById(1L).isPresent()) ? orderRepository.findById(1L).get() : null;
        Objects.requireNonNull(order).setStatus("DELIVERED");
        order.getBillingAddress().setZipCode("14141414");

        // when - action or the behavior that we are going test
        Order savedOrder = orderRepository.save(order);

        // then - verify the output
        Assertions.assertThat(savedOrder).isNotNull();
        Assertions.assertThat(savedOrder.getStatus()).isEqualTo("DELIVERED");

    }

    // JUnit test for delete order (Remove Cascade Type)
    @Test
    @DisplayName("JUnit test for delete order (Remove Cascade Type)")
    public void givenOrderId_whenDeleteOrder_thenDeletedOrderAndAddress() {

        // given - precondition or setup
        Long id = 1L;

        // when - action or the behavior that we are going test
        orderRepository.deleteById(id);
        Optional<Order> order = orderRepository.findById(id);

        // then - verify the output
        Assertions.assertThat(order).isEmpty();

    }

    // JUnit test for get order
    @Test
    @DisplayName("JUnit test for delete order (Remove Cascade Type)")
    public void givenOrderId_whenFindByID_thenGivenOrderAndAddressObject() {

        // given - precondition or setup
        Long id = 2L;

        // when - action or the behavior that we are going test
        Order order = (orderRepository.findById(id).isPresent()) ? orderRepository.findById(id).get() : null;

        // then - verify the output
        System.out.println(order);
        Assertions.assertThat(order).isNotNull();

    }

}
