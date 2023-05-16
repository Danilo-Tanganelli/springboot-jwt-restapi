package br.mackenzie.restapi.app;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AppsController {
  @Autowired
  private AppsRepository repository;

  @PostMapping("/apps")
  public Apps postApps(@RequestBody Apps apps) {
    return repository.save(apps);
  }

  @GetMapping("/apps")
  public List<Apps> getApps() {
    return repository.findAll();
  }

  @GetMapping("/apps/procurar")
  public List<Apps> getJogosFromTime(@RequestParam String dev) {
    return repository.findByDevIgnoreCase(dev);
  }


  @GetMapping("/apps/{id}")
  public Optional<Apps> getApps(@PathVariable long id) {
    return repository.findById(id);
  }

  @PutMapping("/apps/{id}")
  Optional<Apps> updateJogo(@RequestBody Apps body, @PathVariable long id) {
    Optional<Apps> opt = repository.findById(id);

    if (opt.isPresent()) {
      Apps app = opt.get();
      app.setNome(body.getNome());
      app.setDev(body.getDev());
      app.setDownloads(body.getDownloads());
    
      repository.save(app);
    }

    return opt;
  }

  @DeleteMapping("/apps/{id}")
  void deleteApps(@PathVariable long id) {
    repository.deleteById(id);
  }
}
