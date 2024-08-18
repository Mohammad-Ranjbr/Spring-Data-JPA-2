package com.example.SpringDataJpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderTrackingNumber;
    private int totalQuantity;
    private BigDecimal totalPrice;
    private String status;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    // In a unidirectional relationship, the source entity has a relation field that refers to the target entity,
    // and the source entity's table contains the foreign key. In a unidirectional communication,
    // the source entity cannot be accessed in the target class because we did not create an object from the source class in the target class.
    // In a two-way relationship, each entity (source and target) has a relation field that refers to each other,
    // and the target entity table contains the foreign key. The resource entity must use the mappedBy attribute to define a two-way one-to-one mapping.
    // Source: Order , Target: Address
    @OneToOne
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;


}
