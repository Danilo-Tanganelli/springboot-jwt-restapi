package br.mackenzie.restapi.pais;

import javax.persistence.*;

@Entity
@Table(name="paises")
public class Pais {
  @Id @GeneratedValue
  private long id;
  private String nome;
  private String continente;
  private long populacao;

  

  public Pais() {
    nome = "";
  }

  public Pais(String nome, String continente, long populacao) {
    this.nome = nome;
    this.continente = continente;
    this.populacao = populacao;
  }

  public long getId() { return this.id; }
  public String getNome() { return this.nome; }
  public String getContinente() { return this.continente; }
  public long getPopulacao() { return this.populacao; }

  public void setNome(String nome){
    this.nome = nome;
  }
  public void setContinente(String continente){
    this.continente = continente;
  }
  public void setPopulacao(long populacao) {
    this.populacao = populacao;
  }
}