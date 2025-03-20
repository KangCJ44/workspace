package com.sk.skala.myapp.service;

import com.sk.skala.myapp.model.Player;
import com.sk.skala.myapp.model.Stock;
import com.sk.skala.myapp.repository.PlayerRepository;
import com.sk.skala.myapp.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final StockRepository stockRepository;

    public PlayerService(PlayerRepository playerRepository, StockRepository stockRepository) {
        this.playerRepository = playerRepository;
        this.stockRepository = stockRepository;
    }

    // 페이징 처리를 통해 전체 플레이어 조회
    public Page<Player> getAllPlayers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return playerRepository.findAll(pageable);
    }

    // ID로 플레이어 조회
    public Player getPlayerById(String playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("플레이어를 찾을 수 없습니다: " + playerId));
    }

    // 플레이어 추가
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    // 주식 매수
    public Player buyStock(String playerId, int quantity, String stockName) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("플레이어를 찾을 수 없습니다: " + playerId));

        // 주식 이름을 기반으로 Stock 객체를 찾음 (findByStockNameLike 사용)
        Stock stock = stockRepository.findByStockNameLike(stockName)
                .orElseThrow(() -> new IllegalArgumentException("주식을 찾을 수 없습니다: " + stockName));

        // 주식 매수 처리
        player.buyStock(quantity, stock);
        return playerRepository.save(player);
    }

    // 주식 매도
    public Player sellStock(String playerId, int quantity, String stockName) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("플레이어를 찾을 수 없습니다: " + playerId));

        // 주식 이름을 기반으로 Stock 객체를 찾음 (findByStockNameLike 사용)
        Stock stock = stockRepository.findByStockNameLike(stockName)
                .orElseThrow(() -> new IllegalArgumentException("주식을 찾을 수 없습니다: " + stockName));

        // 주식 매도 처리
        player.sellStock(quantity, stock);
        return playerRepository.save(player);
    }
}
