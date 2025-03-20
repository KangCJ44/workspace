package com.sk.skala.myapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStock {

    private Stock stock;      // Stock 객체 (주식 이름 및 가격)
    private int quantity;     // 보유 수량
}
