package com.stockrestservice.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class StocksController {

    @Autowired
    private StocksRepository stockRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewStock (@RequestParam String content) {

        Stocks n = new Stocks();
        n.setContent(content);
        stockRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Stocks> getAllStocks() {
        // This returns a JSON or XML with the users
        return stockRepository.findAll();
    }
}