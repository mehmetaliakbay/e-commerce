package com.difransel.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private UserA usera;

    @OneToMany(mappedBy = "shoppingCard")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "images", "orderProducts", "categories", "shoppingCard" }, allowSetters = true)
    private Set<Product> products = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ShoppingCard id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public ShoppingCard amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UserA getUsera() {
        return this.usera;
    }

    public void setUsera(UserA userA) {
        this.usera = userA;
    }

    public ShoppingCard usera(UserA userA) {
        this.setUsera(userA);
        return this;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        if (this.products != null) {
            this.products.forEach(i -> i.setShoppingCard(null));
        }
        if (products != null) {
            products.forEach(i -> i.setShoppingCard(this));
        }
        this.products = products;
    }

    public ShoppingCard products(Set<Product> products) {
        this.setProducts(products);
        return this;
    }

    public ShoppingCard addProduct(Product product) {
        this.products.add(product);
        product.setShoppingCard(this);
        return this;
    }

    public ShoppingCard removeProduct(Product product) {
        this.products.remove(product);
        product.setShoppingCard(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingCard)) {
            return false;
        }
        return id != null && id.equals(((ShoppingCard) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShoppingCard{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            "}";
    }
}
