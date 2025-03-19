package com.sk.skala.myapp.controller;

import com.sk.skala.myapp.repository.StockRepository;
import com.sk.skala.myapp.service.StockService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import com.sk.skala.myapp.model.Stock;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RestController
@RequestMapping("/api")
public class StockController {
    
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getStockList() {
        List<Stock> list = stockService.getAllStocks();
        log.debug("Get All Stocks");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

        // 주식 정보 추가
    @PostMapping("/stocks")
    public ResponseEntity<Stock> createStock(@Valid @RequestBody Stock stock) {
        Stock newStock = stockService.createStock(stock);
        log.debug("Create New Stock");
        return new ResponseEntity<>(newStock, HttpStatus.CREATED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                               .map(error -> error.getField() + " " + error.getDefaultMessage())
                               .findFirst()
                               .orElse("Invalid request data");
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
