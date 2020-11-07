package com.stockrestservice.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Stocks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String quotes;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    //TODO: add unique property to DB column and replicate in the API
    public void setName(String name) {
        this.name = name;
    }
    public String getQuotes() {
        return quotes;
    }
    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

}
