package com.webmodels.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CeneoProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String link;
    private String category;
    private double price;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createdAt;

    public CeneoProduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @Override
    public String toString() {
        return "CeneoProduct{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}