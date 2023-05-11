package br.mackenzie.restapi.empregado;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api")
public class EmpregadoController {
  
  
  @Autowired
  private EmpregadoRepository repository;

  @GetMapping("/empregados")
  public List<Empregado> getEmpregados() {
    return repository.findAll();
  }

    @GetMapping("/empregados/{id}")
    Optional<Empregado> getEmpregadoById(@PathVariable long id) {
      return repository.findById(id);
}
  
  @PostMapping("/empregados")
  public Empregado postEmpregado(@RequestBody Empregado empregado) {
    return repository.save(empregado);
  }

  @DeleteMapping("/empregados/{id}")
  public void deleteEmpregado(@PathVariable long id){
    repository.deleteById(id);
  }

  
  @PutMapping("/empregados/{id}")
      Optional<Empregado> updateEmpregado(@RequestBody Empregado empregadoRequest, @PathVariable long id) {

        Optional<Empregado> opt = repository.findById(id);
        if(opt.isPresent()) {
          Empregado empregado = opt.get();
          empregado.setNome(empregadoRequest.getNome());
          empregado.setCargo(empregadoRequest.getCargo());
          empregado.setSalario(empregadoRequest.getSalario());
          repository.save(empregado);
          
        }else{throw new  ResponseStatusException(HttpStatus.NOT_FOUND,"Erro ao alterar dados do empregado de id: " + id);}
        return opt;
          
          
      }

   @GetMapping("/empregados/procurar")
    public List<Empregado> getEmpregadoFromNome(@RequestParam String nome) {
      return repository.findByNomeIgnoreCase(nome);
    }  

  
}
