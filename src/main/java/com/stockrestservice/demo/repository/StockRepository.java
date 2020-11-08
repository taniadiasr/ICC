package com.stockrestservice.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockrestservice.demo.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
    //List<Stock> findById(long id);
    List<Stock> findByName(String name);
}