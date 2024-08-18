package com.example.SpringDataJpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    // Source: Order , Target: Address
    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    // private Address billingAddress;

    // In a bidirectional relationship, each entity (source and target) has a relation field that refers to each other,
    // and the target entity table contains the foreign key. The resource entity must use the mappedBy attribute to define a bidirectional one-to-one mapping.
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Address billingAddress;

    // the unidirectional @OneToMany association is simpler it`s just the parent-side that defines
    // the relationship. in unidirectional mapping we use only @OneTOMany annotation.
    //the bidirectional association requires the child entity mapping to provide a @ManyToOne annotation
    // witch is responsible for controlling the association. in bidirectional mapping we use @OneToMany and @ManyToOne annotation.
    // unidirectional one to many
    // default fetch type for one to many is lazy
    // in unidirectional hibernate first save target entity then updates it to add foreign key but in bidirectional it is not like this
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Set<OrderItem> orderItems = new HashSet<>();

    public BigDecimal getTotalPrice(){
        BigDecimal amount = new BigDecimal(0);
        for(OrderItem item:this.orderItems){
            amount = amount.add(item.getPrice());
        }
        return amount;
    }

}
