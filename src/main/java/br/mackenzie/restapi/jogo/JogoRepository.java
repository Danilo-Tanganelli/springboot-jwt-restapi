package br.mackenzie.restapi.jogo;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;


public interface JogoRepository extends JpaRepository<Jogo, Long> {
    List<Jogo> findByTimeAIgnoreCaseOrTimeBIgnoreCase(String timeA, String timeB);
}