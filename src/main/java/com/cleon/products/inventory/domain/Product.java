package com.cleon.products.inventory.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * vbala created on 4/4/2020
 * Inside the package - com.cleon.products.inventory.domain
 **/
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    /**
     * Internal id generated by the DB. This
     * will not be exposed to outside users of the
     * APIs
     */
    @Id
    //@GeneratedValue(generator = "UUID")
    //@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGGenerator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name="product_sequence", sequenceName = "product_sequence",
            initialValue = 30001, allocationSize = 1)
    @Column(updatable = false, nullable = false)
    private Long productId;

    /**
     * Product Number is the unique number for each product
     * This is the number that will be exposed to outside world
     */
    @Column(columnDefinition = "varchar", updatable = false, nullable = false, unique = true)
    private String productNumber;

    /**
     * Name of the product
     */
    @Column(length = 50, columnDefinition = "varchar", nullable = false, unique = true)
    private String productName;

    /**
     * The product type that is associated with the product
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType productType;


    /**
     * The price of the product
     */
    @Positive
    @Column(precision = 3, columnDefinition = "number", nullable = false)
    private BigDecimal productPrice;

    /**
     * The serial number of the product
     */
    @NotBlank
    @Column(length = 25, columnDefinition = "varchar", nullable = false, unique = true)
    private String serialNumber;

    /**
     * The date and time on which the product was created
     */
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    /**
     * The last occurrence when the product was updated
     */
    @UpdateTimestamp
    @Column(updatable = false)
    private Timestamp lastModifiedDate;

    /**
     * Links the product to its inventory
     */
    @OneToOne(mappedBy = "product")
    private ProductInventory productInventory;


}