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

    // ✅ 모든 주식 목록 조회
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();  // JPA 방식으로 변경
    }

    // ✅ 주식 생성 로직 수정
    public Stock createStock(Stock stock) {
        try {
            return stockRepository.save(stock); // JPA의 save() 사용
        } catch (Exception e) {
            System.out.println("ERROR: 주식 생성 중 문제가 발생했습니다. " + e.getMessage());
            throw new RuntimeException("주식 생성 실패", e);
        }
    }
}
