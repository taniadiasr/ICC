package com.stockrestservice.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Optional;

@Controller
@RequestMapping(path="/demo")
public class StocksController {

    @Autowired
    private StocksRepository stockRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewStock (@RequestParam String name, @RequestParam String quotes) {

        Stocks n = new Stocks();
        n.setName(name);
        n.setQuotes(quotes);
        stockRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Integer id) {

        return stockRepository.findById(id)
            .map(record -> ResponseEntity.ok().body(record))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Stocks> getAllStocks() {

        return stockRepository.findAll();
    }

    /*@PostMapping(path="/patch")
    public @ResponseBody String addNewQuote(@RequestParam Integer id, @RequestParam String toBeAdded) {
        Stocks n = new Stocks();
        stockRepository.findById(id);

        return "Updated";
    }*/
}