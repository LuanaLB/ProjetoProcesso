package com.example.victor.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Experiencia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeEmpresa;
    private String cargo;
    private LocalDateTime periodoInicio;
    private LocalDateTime periodoFim;

    private String formaContratacao;
    private String tarefaExecutada;

    @ManyToOne
    private Candidato candidato;


}
