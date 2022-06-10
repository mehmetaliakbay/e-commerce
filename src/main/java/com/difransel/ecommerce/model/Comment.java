package com.difransel.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Comment.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rate")
    private String rate;

    @Column(name = "ip")
    private String ip;

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
    @JsonIgnoreProperties(value = { "images","orderProducts", "comments","categories","shoppingCard"}, allowSetters = true)
    private Product product;

}
