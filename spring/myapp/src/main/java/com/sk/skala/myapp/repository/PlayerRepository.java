package com.sk.skala.myapp.repository;

import com.sk.skala.myapp.model.Player;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
	Page<Player> findAll(Pageable pageable);
}
