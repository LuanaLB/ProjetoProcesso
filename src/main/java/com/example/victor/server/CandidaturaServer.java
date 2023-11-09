package com.example.victor.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.victor.model.entity.Candidato;
import com.example.victor.model.entity.Candidatura;
import com.example.victor.model.entity.Vaga;
import com.example.victor.model.repository.CandidaturaRepository;
import com.example.victor.model.repository.VagaRepository;

@Service
public class CandidaturaServer {
  
   @Autowired
    private CandidaturaRepository candidaturaRepository;
    @Autowired
    private VagaRepository vagaRepository;

  
    public Candidatura candidatar(Candidatura candidaturaRequest , Vaga vaga , Candidato candidato) {
      candidaturaRequest.setVagas(vaga);
      candidaturaRequest.setCandidato(candidato);
      vaga.getCandidaturas().add(candidaturaRequest); // Add the candidatura to the Vaga's collection
      candidato.getCandidaturas().add(candidaturaRequest); // Add the candidatura to the Pessoa's collection
      vagaRepository.save(vaga); // Save the updated Vaga
      return candidaturaRepository.save(candidaturaRequest);
    }
    
  
 public void buscar(){
  this.candidaturaRepository.findAll();
 }
}
