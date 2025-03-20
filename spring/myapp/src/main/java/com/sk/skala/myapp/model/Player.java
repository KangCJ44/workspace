package com.sk.skala.myapp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sk.skala.myapp.tools.JsonTool;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    private String playerId;

    private double playerMoney;

    @JsonIgnore
    private String playerStocks;

    public Player(String id, double money) {
        this.playerId = id;
        this.playerMoney = money;
    }

    // 플레이어의 주식 목록을 반환
    public List<PlayerStock> getPlayerStockList() {
        if (this.playerStocks != null) {
            return JsonTool.toList(playerStocks, PlayerStock.class);
        } else {
            return new ArrayList<>();
        }
    }

    // 플레이어의 주식 목록을 설정
    public void setPlayerStockList(List<PlayerStock> list) {
        this.playerStocks = JsonTool.toString(list);
    }

    // 주식 매수 (주식 목록에 추가)
    public void buyStock(int quantity, Stock stock) {
        List<PlayerStock> playerStockList = getPlayerStockList();

        // 해당 주식이 이미 있다면 수량을 증가
        for (PlayerStock playerStock : playerStockList) {
            if (playerStock.getStock().getStockName().equals(stock.getStockName())) {
                playerStock.setQuantity(playerStock.getQuantity() + quantity);
                setPlayerStockList(playerStockList);
                return;
            }
        }

        // 주식 목록에 없다면 새로 추가
        playerStockList.add(new PlayerStock(stock, quantity));
        setPlayerStockList(playerStockList);
    }

    // 주식 매도 (주식 목록에서 제거)
    public void sellStock(int quantity, Stock stock) {
        List<PlayerStock> playerStockList = getPlayerStockList();

        // 해당 주식이 있는지 확인
        for (PlayerStock playerStock : playerStockList) {
            if (playerStock.getStock().getStockName().equals(stock.getStockName())) {
                if (playerStock.getQuantity() >= quantity) {
                    playerStock.setQuantity(playerStock.getQuantity() - quantity);
                    if (playerStock.getQuantity() == 0) {
                        playerStockList.remove(playerStock);  // 주식 수량이 0이면 목록에서 삭제
                    }
                    setPlayerStockList(playerStockList);
                    return;
                } else {
                    throw new IllegalArgumentException("주식 수량이 부족합니다.");
                }
            }
        }
        throw new IllegalArgumentException("해당 주식이 없습니다.");
    }
}
