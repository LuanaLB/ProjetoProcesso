package com.example.victor.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Empresa implements Serializable {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private String nome;
  private String email;
  private String telefone;
  private String cnpj;
  private String descricao;

  @OneToMany(mappedBy = "empresa")
  @JsonIgnoreProperties("empresa")
  private List<Vaga> vagas = new ArrayList<>();

  @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
  @JsonIgnoreProperties("empresa")
  private List<Endereco> enderecos = new ArrayList<>();
}