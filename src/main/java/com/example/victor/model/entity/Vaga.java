package com.example.victor.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
@JsonIgnoreType
public class Vaga implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cargo;
    private String descricao;
    private String contracao;
    private String remuneracao;

    @ManyToOne
   @JsonBackReference
    private Candidato candidato;

    @ManyToOne
    private Empresa empresa;


 @OneToMany(mappedBy = "vagas")
@JsonIgnoreProperties("candidaturas")
private List<Candidatura> candidaturas = new ArrayList<>(); // Inicialize a lista


    public Vaga(){}
    public Vaga(Long id) {
        this.id = id;
    }

    

}