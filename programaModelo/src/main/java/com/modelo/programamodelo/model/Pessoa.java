package com.modelo.programamodelo.model;

public class Pessoa {
  private int codigo;
  private String nome;
  private String sobrenome;

  public Pessoa(int codigo, String nome, String sobrenome) {
    this.codigo = codigo;
    this.nome = nome;
    this.sobrenome = sobrenome;
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }



}
