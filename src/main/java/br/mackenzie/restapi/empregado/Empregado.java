package br.mackenzie.restapi.empregado;

import javax.persistence.*;

@Entity
@Table(name="empregados")
public class Empregado {

  @Id
  @GeneratedValue
  private Long id;
  private String nome;
  private String cargo;
  private float salario;
  
  public Empregado() {
    nome = "";
  }

  public Empregado(String nome,String cargo,float salario) {
    
    this.nome = nome;
    this.cargo = cargo;
    this.salario = salario;
  }
 
  public Long getId() { return this.id; }
  public String getNome() { return this.nome; }
  public String getCargo() { return this.cargo; }
  public float getSalario() { return this.salario; }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
  
  public String toString() {
    return "Empregado: " + nome + ", Cargo: " + cargo + ", Salario" + salario;
  }
}