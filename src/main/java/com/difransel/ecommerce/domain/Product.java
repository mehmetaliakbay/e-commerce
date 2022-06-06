package com.difransel.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties(value = { "usera", "product", "order" }, allowSetters = true)
    private Set<OrderProduct> orderProducts = new HashSet<>();

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
    @JsonIgnoreProperties(value = { "usera", "products" }, allowSetters = true)
    private ShoppingCard shoppingCard;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Product title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public Product keywords(String keywords) {
        this.setKeywords(keywords);
        return this;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return this.description;
    }

    public Product description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public Product image(String image) {
        this.setImage(image);
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return this.detail;
    }

    public Product detail(String detail) {
        this.setDetail(detail);
        return this;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Product price(BigDecimal price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public Product amount(Integer amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSlug() {
        return this.slug;
    }

    public Product slug(String slug) {
        this.setSlug(slug);
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public Product status(Boolean status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Product createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public Product updatedAt(Instant updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Images> getImages() {
        return this.images;
    }

    public void setImages(Set<Images> images) {
        if (this.images != null) {
            this.images.forEach(i -> i.setProduct(null));
        }
        if (images != null) {
            images.forEach(i -> i.setProduct(this));
        }
        this.images = images;
    }

    public Product images(Set<Images> images) {
        this.setImages(images);
        return this;
    }

    public Product addImages(Images images) {
        this.images.add(images);
        images.setProduct(this);
        return this;
    }

    public Product removeImages(Images images) {
        this.images.remove(images);
        images.setProduct(null);
        return this;
    }

    public Set<OrderProduct> getOrderProducts() {
        return this.orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        if (this.orderProducts != null) {
            this.orderProducts.forEach(i -> i.setProduct(null));
        }
        if (orderProducts != null) {
            orderProducts.forEach(i -> i.setProduct(this));
        }
        this.orderProducts = orderProducts;
    }

    public Product orderProducts(Set<OrderProduct> orderProducts) {
        this.setOrderProducts(orderProducts);
        return this;
    }

    public Product addOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);
        orderProduct.setProduct(this);
        return this;
    }

    public Product removeOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.remove(orderProduct);
        orderProduct.setProduct(null);
        return this;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Product categories(Set<Category> categories) {
        this.setCategories(categories);
        return this;
    }

    public Product addCategory(Category category) {
        this.categories.add(category);
        category.getProducts().add(this);
        return this;
    }

    public Product removeCategory(Category category) {
        this.categories.remove(category);
        category.getProducts().remove(this);
        return this;
    }

    public ShoppingCard getShoppingCard() {
        return this.shoppingCard;
    }

    public void setShoppingCard(ShoppingCard shoppingCard) {
        this.shoppingCard = shoppingCard;
    }

    public Product shoppingCard(ShoppingCard shoppingCard) {
        this.setShoppingCard(shoppingCard);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", keywords='" + getKeywords() + "'" +
            ", description='" + getDescription() + "'" +
            ", image='" + getImage() + "'" +
            ", detail='" + getDetail() + "'" +
            ", price=" + getPrice() +
            ", amount=" + getAmount() +
            ", slug='" + getSlug() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
