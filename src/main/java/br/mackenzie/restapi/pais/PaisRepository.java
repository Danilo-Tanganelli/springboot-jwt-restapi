package br.mackenzie.restapi.pais;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    public List<Pais> findByNomeIgnoreCase(String nome);
}
