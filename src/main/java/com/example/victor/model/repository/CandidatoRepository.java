package com.example.victor.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.victor.model.entity.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    List<Candidato> findByEnderecosCidade(String cidade);
    List<Candidato> findByEnderecosEstado(String estado);
    List<Candidato> findByExperienciasCargo(String cargo);


}
