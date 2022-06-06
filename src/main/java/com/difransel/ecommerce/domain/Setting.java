package com.difransel.ecommerce.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Setting.
 */
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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Setting id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Setting title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public Setting keywords(String keywords) {
        this.setKeywords(keywords);
        return this;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return this.description;
    }

    public Setting description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return this.company;
    }

    public Setting company(String company) {
        this.setCompany(company);
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return this.address;
    }

    public Setting address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public Setting phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return this.fax;
    }

    public Setting fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return this.email;
    }

    public Setting email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSmtpserver() {
        return this.smtpserver;
    }

    public Setting smtpserver(String smtpserver) {
        this.setSmtpserver(smtpserver);
        return this;
    }

    public void setSmtpserver(String smtpserver) {
        this.smtpserver = smtpserver;
    }

    public String getSmtpemail() {
        return this.smtpemail;
    }

    public Setting smtpemail(String smtpemail) {
        this.setSmtpemail(smtpemail);
        return this;
    }

    public void setSmtpemail(String smtpemail) {
        this.smtpemail = smtpemail;
    }

    public String getSmtppassword() {
        return this.smtppassword;
    }

    public Setting smtppassword(String smtppassword) {
        this.setSmtppassword(smtppassword);
        return this;
    }

    public void setSmtppassword(String smtppassword) {
        this.smtppassword = smtppassword;
    }

    public String getSmtpport() {
        return this.smtpport;
    }

    public Setting smtpport(String smtpport) {
        this.setSmtpport(smtpport);
        return this;
    }

    public void setSmtpport(String smtpport) {
        this.smtpport = smtpport;
    }

    public String getFacebook() {
        return this.facebook;
    }

    public Setting facebook(String facebook) {
        this.setFacebook(facebook);
        return this;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return this.instagram;
    }

    public Setting instagram(String instagram) {
        this.setInstagram(instagram);
        return this;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return this.twitter;
    }

    public Setting twitter(String twitter) {
        this.setTwitter(twitter);
        return this;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getAboutus() {
        return this.aboutus;
    }

    public Setting aboutus(String aboutus) {
        this.setAboutus(aboutus);
        return this;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }

    public String getContact() {
        return this.contact;
    }

    public Setting contact(String contact) {
        this.setContact(contact);
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getReferences() {
        return this.references;
    }

    public Setting references(String references) {
        this.setReferences(references);
        return this;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public String getStatus() {
        return this.status;
    }

    public Setting status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Setting createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public Setting updatedAt(Instant updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Setting)) {
            return false;
        }
        return id != null && id.equals(((Setting) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Setting{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", keywords='" + getKeywords() + "'" +
            ", description='" + getDescription() + "'" +
            ", company='" + getCompany() + "'" +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            ", fax='" + getFax() + "'" +
            ", email='" + getEmail() + "'" +
            ", smtpserver='" + getSmtpserver() + "'" +
            ", smtpemail='" + getSmtpemail() + "'" +
            ", smtppassword='" + getSmtppassword() + "'" +
            ", smtpport='" + getSmtpport() + "'" +
            ", facebook='" + getFacebook() + "'" +
            ", instagram='" + getInstagram() + "'" +
            ", twitter='" + getTwitter() + "'" +
            ", aboutus='" + getAboutus() + "'" +
            ", contact='" + getContact() + "'" +
            ", references='" + getReferences() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
