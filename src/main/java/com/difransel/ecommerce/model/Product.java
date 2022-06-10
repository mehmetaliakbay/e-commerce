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
 * A Product.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "detail")
    private String detail;

    @Column(name = "price", precision = 21, scale = 2)
    private BigDecimal price;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "slug")
    private String slug;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "product" }, allowSetters = true)
    private Set<Images> images = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "user", "product", "customerOrder" }, allowSetters = true)
    private Set<OrderProduct> orderProducts = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "user", "product" }, allowSetters = true)
    private Set<Comment> comments = new HashSet<>();
    @ManyToMany
    @JoinTable(
        name = "rel_product__category",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "ids", "parentIds", "products" }, allowSetters = true)
    private Set<Category> categories = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "user", "products" }, allowSetters = true)
    private ShoppingCard shoppingCard;


}
