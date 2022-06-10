package com.difransel.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A OrderProduct.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "order_product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "price", precision = 21, scale = 2)
    private BigDecimal price;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "total", precision = 21, scale = 2)
    private BigDecimal total;

    @Column(name = "ip")
    private String ip;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToOne
    @JsonIgnoreProperties(value = { "comments", "orderProducts" }, allowSetters = true)
    private User user;

    @ManyToOne
    @JsonIgnoreProperties(value = { "images", "orderProducts","comments", "categories", "shoppingCard" }, allowSetters = true)
    private Product product;

    @ManyToOne
    @JsonIgnoreProperties(value = { "user", "orderProducts" }, allowSetters = true)
    private CustomerOrder customerOrder;


}
