package com.sk.skala.myapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stock {
    String stockName;
    int stockPrice;

    public Stock() {
    }

    public Stock(String name, int price) {
        this.stockName = name;
        this.stockPrice = price;
    }

    public String toString() {
        return stockName + ":" + stockPrice;
    }
}
