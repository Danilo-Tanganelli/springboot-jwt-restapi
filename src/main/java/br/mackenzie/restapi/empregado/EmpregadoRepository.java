package br.mackenzie.restapi.empregado;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {
    public List<Empregado> findByNomeIgnoreCase(String nome);
}
