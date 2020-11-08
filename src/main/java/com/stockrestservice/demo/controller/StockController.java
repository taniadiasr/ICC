package com.stockrestservice.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockrestservice.demo.model.Stock;
import com.stockrestservice.demo.repository.StockRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class StockController {

    @Autowired
    StockRepository stockRepository;
    /*Get All Stocks! */
    @GetMapping("/stock")
    public ResponseEntity<List<Stock>> getAllStocks(@RequestParam(required = false) String name) {
        try {
            List<Stock> stocks = new ArrayList<Stock>();

            if (name == null)
                stockRepository.findAll().forEach(stocks::add);
            else
                stockRepository.findByName(name).forEach(stocks::add);

            if (stocks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(stocks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*Get by Id !*/
    @GetMapping("/stock/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable("id") long id) {
        Optional<Stock> stockData = stockRepository.findById(id);

        if (stockData.isPresent()) {
            return new ResponseEntity<>(stockData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*Create Stock*/
    @PostMapping("/stock")
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        try {
            Stock _stock = stockRepository
                    .save(new Stock(stock.getName(), stock.getQuotes()));
            return new ResponseEntity<>(_stock, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
