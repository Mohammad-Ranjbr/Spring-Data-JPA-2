package com.example.SpringDataJpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// In SQL, a schema acts as a namespace (namespace) that holds a collection of tables, views (views), functions, and other database objects.
// The uniqueConstraints parameter in the @Table annotation in JPA allows you to apply a Unique Constraint to one or more columns in the table.
// This constraint ensures that the values in the specified column or columns are unique at the table level and duplicate data is not entered in these columns.
// By using name, you can assign a unique name to the adverb. This name can be useful in error messages, logs, or for database management operations.

//@NamedQuery( Hibernate's suggestion is to use the jpa package
//        name = "Product.findByPrice", // Convention: EntityName.MethodName
//        query = "select p from Product p where p.price =:price"
//)

@NamedQueries( // Hibernate's suggestion is to use the jpa package
        {
                @NamedQuery(
                        name = "Product.findAllOrderByNameDesc",
                        query = "select p from Product p order by p.name desc"
                ),
                @NamedQuery(
                        name = "Product.findByPrice",
                        query = "select p from Product p where p.price =:price"
                )
        }
)

//@NamedNativeQuery( Hibernate's suggestion is to use the jpa package
//        name = "Product.findByDescription",
//        query = "select * from products p where p.description =:description",
//        resultClass = Product.class
//)

@NamedNativeQueries( // Hibernate's suggestion is to use the jpa package
        {
                @NamedNativeQuery(
                        name = "Product.findByDescription",
                        query = "select * from products p where p.description =:description",
                        resultClass = Product.class
                ),
                @NamedNativeQuery(
                        name = "Product.findAllOrderByAsc",
                        query = "select * from products order by name asc",
                        resultClass = Product.class
                )
        }
)

@Getter
@Setter
@Entity
@Builder
@ToString
@Table(
        name = "products",
        schema = "public",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "sku_unique",
                        columnNames = "stock_keeping_unit"
                )
        }
)
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    // strategy = GenerationType.AUTO : The GenerationType.AUTO is the default generation
    // type and lets the persistence provider choose the generation strategy.
    // If you use Hibernate as your persistence provider, it selects a generation strategy based
    // on the database-specification c dialect. For most popular databases, it selects GenerationType.SEQUENCE.
    // strategy = GenerationType.IDENTITY : It relies on an auto-incremented database column and lets the database generate a
    // new value with each insert operation. From a database point of view, this is very efficient because the auto-increment
    // columns are highly optimized, and it doesn’t require any additional statements. Not good for JDBC batch operations
    // strategy = GenerationType.SEQUENCE : Application: GenerationType.SEQUENCE is one of the primary key
    // generation strategies in JPA. This strategy uses a sequence in the database to generate uniques.
    // How it works: When using this strategy, JPA uses a sequence in the database to get the next value,
    // and this value is applied as the primary key (primary key) to the new record.
    // Performance impact: For each new record, an additional query (select) is performed to get the next value of
    // the sequence. But on applications, this operation will have a negative effect on performance, because these queries are light and fast.
    // Definition: @SequenceGenerator is an annotation in JPA that allows you to specify which sequence to use in the database and define related settings such as sequence name, schema, and allocation size.
    // name: the name used for the generator in JPA.
    // sequenceName: the name of the sequence in the database.
    // schema: If your database uses schemas, you can also specify the schema for the sequence.
    // allocationSize: The number of values that JPA requests from the sequence by default from the database.
    // This reduces the number of SELECT queries to get new values because it stores the values as preprocessed.
    // The default value for allocationSize is usually 50. This value means that JPA calls the sequence from
    // the database once and keeps 50 values in memory. This can provide a good balance between the number of queries and
    // memory usage. If your program terminates unexpectedly, memory allocated values that are not yet used will be lost,
    // causing gaps between primary key values. This is usually not a performance problem for the application, but may
    // require attention for some applications where the primary key is important.
    // The GenerationType.TABLE gets only rarely used nowadays.
    // It simulates a sequence by storing and updating its current value in a database table which requires
    // the use of pessimistic locks which put all transactions into a sequential order.
    // This slows down your application, and you should, therefore, prefer
    // the GenerationType.SEQUENCE, if your database supports sequences, which most popular databases do.
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    // @SequenceGenerator(name = "product_generator",
    //         sequenceName = "product_sequence_name",
    //         allocationSize = 1)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stock_keeping_unit", nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;
    private String description;
    private BigDecimal price;
    private boolean active;
    private String imageUrl;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

}
