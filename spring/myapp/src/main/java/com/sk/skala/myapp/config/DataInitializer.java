package com.sk.skala.myapp.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sk.skala.myapp.model.Player;
import com.sk.skala.myapp.model.Stock;
import com.sk.skala.myapp.repository.PlayerRepository;
import com.sk.skala.myapp.repository.StockRepository;

@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner initStocks(StockRepository stockRepository, PlayerRepository playerRepository) {
		return args -> {
			stockRepository.save(new Stock("TechCorp", 100.00));
			stockRepository.save(new Stock("GreenEnergy", 80.00));
			stockRepository.save(new Stock("HealthPlus", 120.00));
			stockRepository.save(new Stock("SkalaEdu", 150.00));

			playerRepository.save(new Player("Skala.Man", 10000));
			playerRepository.save(new Player("Smart.Woman", 10000));
		};
	}
}
