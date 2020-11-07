package com.stockrestservice.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Stocks> getAllStocks() {
        // This returns a JSON or XML with the users
        return stockRepository.findAll();
    }
}