package br.mackenzie.restapi.app;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppsRepository extends JpaRepository<Apps, Long> {
}
