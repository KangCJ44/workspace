package com.sk.skala.myapp.controller;

import java.util.List;
import com.sk.skala.myapp.model.Player;
import com.sk.skala.myapp.repository.PlayerRepository;
import com.sk.skala.myapp.service.PlayerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@RestController
@RequestMapping("/api")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/players")
    public ResponseEntity<List<Player>> getPlayer(){
        List<Player> list = playerRepository.getPlayerList();
        log.debug("Get All Players");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/players/{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable @NotBlank String playerId) {
        Player player = playerService.getPlayerById(playerId);
        if (player != null) {
            log.debug("Get Player By ID");
            return new ResponseEntity<>(player, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/players")
    public ResponseEntity<Player> createPlayer(@Valid @RequestBody Player player) {
        Player newPlayer = playerService.createPlayer(player);
        log.debug("Create New Player");
        return new ResponseEntity<>(newPlayer, HttpStatus.OK);
    }
    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                               .map(error -> error.getField() + " " + error.getDefaultMessage())
                               .findFirst()
                               .orElse("Invalid request data");
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
