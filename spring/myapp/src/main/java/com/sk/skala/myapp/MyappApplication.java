package com.sk.skala.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sk.skala.myapp.service.SkalaStockMarket;

@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);

		SkalaStockMarket skalaStockMarket = new SkalaStockMarket();
        skalaStockMarket.start();
	}

}
