package com.sk.skala.myapp.controller;

import java.util.List;
import com.sk.skala.myapp.model.Stock;
import com.sk.skala.myapp.repository.PlayerRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlayerController {
    private final PlayerRepository playerRepository;

    @GetMapping("/players")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    // public ResponseEntity<List<Stock>> getPlayer(){
    //     List<Stock> list = playerRepository.getPlayerkList();
    //     return new ResponseEntity<>(list, HttpStatus.OK);
    // }
}
