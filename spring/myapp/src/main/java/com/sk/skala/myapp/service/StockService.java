package com.sk.skala.myapp.service;

import com.sk.skala.myapp.model.Stock;
import com.sk.skala.myapp.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAllStocks() {
        return stockRepository.getStockList();
    }

    public Stock createStock(Stock stock) {
        stockRepository.getStockList().add(stock);
        stockRepository.saveStockList();
        return stock;
    }
}