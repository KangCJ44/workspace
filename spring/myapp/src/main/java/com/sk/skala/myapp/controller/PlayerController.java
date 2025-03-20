package com.sk.skala.myapp.controller;

import com.sk.skala.myapp.model.Player;
import com.sk.skala.myapp.service.PlayerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // ✅ 플레이어 목록 조회 (페이징 추가)
    @GetMapping("/players")
    public ResponseEntity<List<Player>> getPlayers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Page<Player> playerPage = playerService.getAllPlayers(page, size);
        log.debug("Get All Players - Page: {}, Size: {}", page, size);
        return new ResponseEntity<>(playerPage.getContent(), HttpStatus.OK);
    }

    // ✅ ID로 플레이어 조회
    @GetMapping("/players/{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable @NotBlank String playerId) {
        try {
            Player player = playerService.getPlayerById(playerId);
            log.debug("Get Player By ID: {}", playerId);
            return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error("Player not found with ID: {}", playerId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ✅ 플레이어 생성
    @PostMapping("/players")
    public ResponseEntity<Player> createPlayer(@Valid @RequestBody Player player) {
        Player newPlayer = playerService.createPlayer(player);
        log.debug("Create New Player: {}", player.getPlayerId());
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

     // ✅ 주식 매수
    @PostMapping("/players/{playerId}/buy")
    public ResponseEntity<?> buyStock(
            @PathVariable @NotBlank String playerId,
            @RequestParam @Positive int quantity,
            @RequestParam @NotBlank String stockName) {  // stockName 추가
        try {
            Player updatedPlayer = playerService.buyStock(playerId, quantity, stockName);
            log.debug("Player {} bought {} stocks of {}", playerId, quantity, stockName);
            return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error("Error buying stocks for player {}: {}", playerId, e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // ✅ 주식 매도
    @PostMapping("/players/{playerId}/sell")
    public ResponseEntity<?> sellStock(
            @PathVariable @NotBlank String playerId,
            @RequestParam @Positive int quantity,
            @RequestParam @NotBlank String stockName) {  // stockName 추가
        try {
            Player updatedPlayer = playerService.sellStock(playerId, quantity, stockName);
            log.debug("Player {} sold {} stocks of {}", playerId, quantity, stockName);
            return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error("Error selling stocks for player {}: {}", playerId, e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // ✅ 예외 처리 (유효성 검사 실패 시)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .findFirst()
                .orElse("Invalid request data");
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
