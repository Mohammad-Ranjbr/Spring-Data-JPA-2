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
public class OneToOneBidirectionalMappingTest {

    private final AddressRepository addressRepository;

    @Autowired
    public OneToOneBidirectionalMappingTest(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    // JUnit test for save address (Persist Cascade Type)
    @Test
    @DisplayName("JUnit test for save address (Persist Cascade Type)")
    public void givenAddressAndOrderObject_whenSaveOrder_thenSaveAddressAndOrder() {

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

        address.setOrder(order);

        // when - action or the behavior that we are going test
        Address savedAddress = addressRepository.save(address);

        // then - verify the output
        Assertions.assertThat(savedAddress).isNotNull();

    }

    // JUnit test for update address (Merge Cascade Type)
    @Test
    @DisplayName("JUnit test for update address (Merge Cascade Type)")
    public void givenAddressObject_whenUpdateAddress_thenReturnUpdatedOrderAndAddress() {

        // given - precondition or setup
        Address address = (addressRepository.findById(1L).isPresent()) ? addressRepository.findById(1L).get() : null;
        Objects.requireNonNull(address).setZipCode("25252525");
        address.getOrder().setStatus("DELIVERED");

        // when - action or the behavior that we are going test
        Address savedAddress = addressRepository.save(address);

        // then - verify the output
        Assertions.assertThat(savedAddress).isNotNull();
        Assertions.assertThat(savedAddress.getZipCode()).isEqualTo("25252525");

    }

    // JUnit test for get address
    @Test
    @DisplayName("JUnit test for get address")
    public void givenAddressId_whenFindByID_thenGivenOrderAndAddressObject() {

        // given - precondition or setup
        Long id = 1L;

        // when - action or the behavior that we are going test
        Address address = (addressRepository.findById(id).isPresent()) ? addressRepository.findById(id).get() : null;

        // then - verify the output
        System.out.println(address);
        Assertions.assertThat(address).isNotNull();

    }

    // JUnit test for delete address (Remove Cascade Type)
    @Test
    @DisplayName("JUnit test for delete address (Remove Cascade Type)")
    public void givenAddressId_whenDeleteAddress_thenDeletedOrderAndAddress() {

        // given - precondition or setup
        Long id = 1L;

        // when - action or the behavior that we are going test
        addressRepository.deleteById(id);
        Optional<Address> address = addressRepository.findById(id);

        // then - verify the output
        Assertions.assertThat(address).isEmpty();

    }

}
