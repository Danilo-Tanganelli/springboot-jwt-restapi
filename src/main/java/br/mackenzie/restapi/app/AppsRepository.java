package br.mackenzie.restapi.app;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppsRepository extends JpaRepository<Apps, Long> {
    List<Apps> findByDevIgnoreCase(String dev);
 
}
