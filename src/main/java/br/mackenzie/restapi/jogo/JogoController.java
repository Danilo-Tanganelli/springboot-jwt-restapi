package br.mackenzie.restapi.jogo;

import java.util.*;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class JogoController {
  @Autowired
  private JogoRepository repository;

  @GetMapping("/jogos")
  public List<Jogo> getJogos() {
    return repository.findAll();
  }

  @GetMapping("/jogos/procurar")
  public List<Jogo> getJogosFromTime(@RequestParam String time) {
    return repository.findByTimeAIgnoreCaseOrTimeBIgnoreCase(time, time);
  }

  @GetMapping("/jogos/{id}")
  public ResponseEntity<Jogo> getJogoById(@PathVariable long id) {
    Optional<Jogo> jogo = repository.findById(id);
    if (jogo.isPresent()) {
      return ResponseEntity.ok(jogo.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/jogos")
  public Jogo postNewJogo(@RequestBody Jogo jogoBody) {
    Jogo novoJogo = new Jogo(jogoBody.getTimeA(), jogoBody.getTimeB(), jogoBody.getGolsA(), jogoBody.getGolsB());
    getJogos().add(novoJogo);
    return repository.save(novoJogo);
  }

  @PutMapping("/jogos/{id}")
  Optional<Jogo> updateJogo(@RequestBody Jogo jogoBody, @PathVariable long id) {
    Optional<Jogo> opt = repository.findById(id);

    if (opt.isPresent()) {
      Jogo jogo = opt.get();
      opt.get().setTimeA(jogoBody.getTimeA());
      jogo.setTimeB(jogoBody.getTimeB());
      jogo.setGolsA(jogoBody.getGolsA());
      jogo.setGolsB(jogoBody.getGolsB());
      repository.save(jogo);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return opt;
  }

  @DeleteMapping("/jogos/{id}")
  void deleteJogo(@PathVariable long id) {
    Optional<Jogo> opt = repository.findById(id);
    if(opt.isPresent()){ 
      repository.deleteById(id);
    }else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
   
  }

}
