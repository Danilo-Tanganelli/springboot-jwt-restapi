package br.mackenzie.restapi.jogo;


import javax.persistence.*;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.relational.core.mapping.Table;



@Entity
@Table(name="jogos")
public class Jogo {
@GeneratedValue
@javax.persistence.Id
private long id;



 

  public String timeA;
  public String timeB;
  private int golsA;
  private int golsB;



  public String getTimeA() {
    return timeA;
  }

  public void setTimeA(String timeA) {
    this.timeA = timeA;
  }

  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }

  public String getTimeB() {
    return timeB;
  }

  public void setTimeB(String timeB) {
    this.timeB = timeB;
  }

  public int getGolsA() {
    return golsA;
  }

  public void setGolsA(int golsA) {
    this.golsA = golsA;
  }

  public int getGolsB() {
    return golsB;
  }

  public void setGolsB(int golsB) {
    this.golsB = golsB;
  }

  public Jogo() {
    timeA = "";
  }

  public Jogo(String timeA, String timeB, int golsA, int golsB) {
    this.timeA = timeA;
    this.timeB = timeB;
    this.golsA = golsA;
    this.golsB = golsB;

  }


  // public String toString() {
  //   return "Aluno " + nome + ", TIA: " + id;
  // }
}