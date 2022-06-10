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
import java.util.HashSet;
import java.util.Set;

/**
 * A ShoppingCard.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "shopping_card")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShoppingCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @JsonIgnoreProperties(value = { "comments", "orderProducts" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "shoppingCard")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "images", "orderProducts","comments", "categories", "shoppingCard" }, allowSetters = true)
    private Set<Product> products = new HashSet<>();
}
