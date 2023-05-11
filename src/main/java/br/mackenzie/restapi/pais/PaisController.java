package br.mackenzie.restapi.pais;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class PaisController {
  @Autowired
  private PaisRepository repository;

  @PostMapping("/pais")
  public Pais postPais(@RequestBody Pais pais) {
    return repository.save(pais);
  }

  @GetMapping("/pais")
  public List<Pais> getPaises() {
    return repository.findAll();
  }

  @GetMapping("/pais/procurar")
  public List<Pais> getNomeFromPais(@RequestParam String nome) {
    return repository.findByNomeIgnoreCase(nome);
  }

  @GetMapping("/pais/{id}")
  public Optional<Pais> getPaisbyId(@PathVariable long id) {
    return repository.findById(id);
  }

  @PutMapping("/pais/{id}")
  Optional<Pais> updateJogo(@RequestBody Pais body, @PathVariable long id) {
    Optional<Pais> opt = repository.findById(id);

    if (opt.isPresent()) {
      Pais pais = opt.get();
      pais.setNome(body.getNome());
      pais.setContinente(body.getContinente());
      pais.setPopulacao(body.getPopulacao());
    
      repository.save(pais);
    }

    return opt;
  }

  @DeleteMapping("/pais/{id}")
  void deleteApps(@PathVariable long id) {
    repository.deleteById(id);
  }
}
