package com.example.victor.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
@JsonIgnoreType
public class Candidato implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    
    private String nome;
    private String email;
    private String telefone;

    @OneToMany(mappedBy = "candidato")
    @JsonIgnoreProperties("candidato")
    private List<Experiencia> experiencias;

    @OneToMany(mappedBy = "candidato")
       @JsonManagedReference
    private List<Vaga> vagas = new ArrayList<>();

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("candidato")
    private List<Endereco> enderecos = new ArrayList<>();


    @OneToMany(mappedBy = "candidato")
    @JsonIgnoreProperties("candidaturas")
    private List<Candidatura> candidaturas = new ArrayList<>();


    public Candidato(){}
    public Candidato(Long id) {
        this.id = id;
    }


}
