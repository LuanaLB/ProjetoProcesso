package com.example.victor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.victor.model.entity.Candidato;
import com.example.victor.model.entity.Candidatura;
import com.example.victor.model.entity.Vaga;
import com.example.victor.server.CandidaturaServer;

@RestController
@RequestMapping("/candidaturas")
public class CandidaturaController {

  @Autowired
  private CandidaturaServer candidaturaServer;

  @PostMapping("/candidatar/{candidatoId}/{vagaId}")
  public ResponseEntity<Candidatura> candidatar(@PathVariable Long candidatoId, @PathVariable Long vagaId,
      @RequestBody Candidatura candidatura) {
    // Crie um objeto Candidatura com base nos IDs recebidos como parâmetros
    var candidato = new Candidato(candidatoId); // Substitua pela lógica apropriada
    var vaga = new Vaga(vagaId); // Substitua pela lógica apropriada

    // Chame o método candidatar do seu serviço
    Candidatura c = candidaturaServer.candidatar(candidatura, vaga, candidato);

    return ResponseEntity.status(HttpStatus.CREATED).body(c);
  }


  @GetMapping
  public void b(){
    this.candidaturaServer.buscar();
  }

}
