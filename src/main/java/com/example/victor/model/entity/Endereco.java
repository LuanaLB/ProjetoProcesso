package com.example.victor.model.entity;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Endereco implements Serializable {
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Id
  private Long id;

  private String rua;
  private String cidade;
  private String estado;
  private String tipo;
  private String uf;

  @ManyToOne
  private Empresa empresa;

  @ManyToOne
  private Candidato candidato;
}
