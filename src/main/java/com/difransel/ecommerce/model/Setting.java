package com.difransel.ecommerce.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Setting.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "setting")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Setting implements Serializable {

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

    @Column(name = "company")
    private String company;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "smtpserver")
    private String smtpserver;

    @Column(name = "smtpemail")
    private String smtpemail;

    @Column(name = "smtppassword")
    private String smtppassword;

    @Column(name = "smtpport")
    private String smtpport;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "twitter")
    private String twitter;

    @Column(name = "aboutus")
    private String aboutus;

    @Column(name = "contact")
    private String contact;

    @Column(name = "jhi_references")
    private String references;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}
