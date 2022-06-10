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
import java.util.HashSet;
import java.util.Set;

/**
 * A Order.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "customer_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomerOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "total", precision = 21, scale = 2)
    private BigDecimal total;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "note")
    private String note;

    @Column(name = "ip")
    private String ip;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @JsonIgnoreProperties(value = { "comments", "orderProducts" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "customerOrder")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "user", "product", "customerOrder" }, allowSetters = true)
    private Set<OrderProduct> orderProducts = new HashSet<>();
}
