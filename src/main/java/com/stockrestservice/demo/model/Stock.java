package com.stockrestservice.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quotes")
    private String quotes;

    public Stock() {

    }

    public Stock(String name,
                 String quotes) {
        this.name = name;
        this.quotes = quotes;
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public void appendQuotes(String quotes) {

        if (this.quotes.isBlank()) {
            this.quotes = "";
        }
        this.quotes += quotes;
    }
    @Override
    public String toString() {
        return " Stock [id=" + id + ", name=" + name + ", quotes=" + quotes + "]";
    }
}
