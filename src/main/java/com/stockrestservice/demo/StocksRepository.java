package com.stockrestservice.demo;

import org.springframework.data.repository.CrudRepository;

import com.stockrestservice.demo.Stocks;

public interface StocksRepository extends CrudRepository<Stocks, Integer> {

}
