package br.mackenzie.restapi.app;

import javax.persistence.*;

@Entity
@Table(name="apps")
public class Apps {
  @Id @GeneratedValue
  private long id;
  private String nome;
  private String dev;
  private long downloads;

  

  public Apps() {
    nome = "";
  }

  public Apps(long id, String nome, String dev, long downloads) {
    this.id = id;
    this.nome = nome;
    this.dev = dev;
    this.downloads = downloads;
  }

  public long getId() { return this.id; }
  public String getNome() { return this.nome; }
  public String getDev() { return this.dev; }
  public long getDownloads() { return this.downloads; }

  //id não tem set por ser um dado gerado automaticamente
  public void setNome(String nome){
    this.nome = nome;
  }
  public void setDev(String dev){
    this.dev = dev;
  }
  public void setDownloads(long downloads) {
    this.downloads = downloads;
  }
  //downloads não tem metodo set por ser um dado gerado pela ação de clicar no "botão download" no html
  
  public String toString() {
    return "O aplicativo " + nome + " foi desenvolvido por " + dev +" e atualmente tem "+downloads+" downloads.";
  }
}