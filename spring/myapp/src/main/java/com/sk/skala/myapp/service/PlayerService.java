package com.sk.skala.myapp.service;

import com.sk.skala.myapp.controller.HellloController;
import com.sk.skala.myapp.model.Player;
import com.sk.skala.myapp.repository.PlayerRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository, HellloController hellloController) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        log.debug("Get All Players");
        return playerRepository.getPlayerList();
    }

    public Player getPlayerById(String playerId) {
        log.debug("Get Players By ID");
        return playerRepository.findPlayer(playerId);
    }

    public Player createPlayer(Player player){
        playerRepository.addPlayer(player);
        return player;
    }
}