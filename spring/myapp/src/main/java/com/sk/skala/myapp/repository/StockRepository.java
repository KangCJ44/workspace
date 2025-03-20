package com.sk.skala.myapp.repository;

import com.sk.skala.myapp.model.Stock;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
	Page<Stock> findAll(Pageable pageable);

	Optional<Stock> findByStockNameLike(String keyword);
}
