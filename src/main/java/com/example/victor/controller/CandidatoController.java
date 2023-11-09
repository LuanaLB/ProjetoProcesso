package com.example.victor.controller;

import com.example.victor.model.entity.*;
import com.example.victor.server.CandidatosServer;
import com.example.victor.server.EmpresaServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//@CrossOrigin(origins = "*")@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/candidatos")
public class CandidatoController {
    @Autowired
    private CandidatosServer candidatosServer;

    @PostMapping
    public ResponseEntity<Candidato> salvarCandidato(@RequestBody Candidato candidato) {
        Candidato novoCandidato = candidatosServer.save(candidato);
        return ResponseEntity.ok().body(novoCandidato);
    }

    @GetMapping
    public List<Candidato> obterTodasCandidato() {
        return candidatosServer.candidatos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidato> obterCandidatoPorId(@PathVariable Long id) {
        Optional<Candidato> candidato = candidatosServer.candidato(id);
        return candidato.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidato> editarcandidato(@PathVariable Long id, @RequestBody Candidato candidato) {
        Optional<Candidato> candidatoAtualizada = candidatosServer.editar(candidato, id);
        return candidatoAtualizada.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCandidato(@PathVariable Long id) {
        candidatosServer.candidatoDeletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/adicionar")
    public ResponseEntity adicionar(@RequestBody Experiencia experiencia, @PathVariable Long id) {
        var candidato = this.candidatosServer.getCandidatoPorId(id);

        if (candidato == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato não encontrado");

        }

        var adcionar = this.candidatosServer.adcionarExperiencia(candidato, experiencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(adcionar);
    }


    @GetMapping("/busca/porCidade/{cidade}")
    public ResponseEntity<List<Candidato>> buscarCidadeCandidato(@PathVariable String cidade) {

        List<Candidato> candidato = this.candidatosServer.buscarCidade(cidade);
        return ResponseEntity.ok(candidato);
    }


    @GetMapping("/busca/porEstado/{estado}")
    public ResponseEntity<List<Candidato>> buscarEstadoCandidato(@PathVariable String estado) {

        List<Candidato> candidato = this.candidatosServer.buscarEstado(estado);
        return ResponseEntity.ok(candidato);
    }

    @GetMapping("/busca/cargo/{cargo}")
    public ResponseEntity<List<Candidato>> buscarCargoCandidato(@PathVariable String cargo) {

        List<Candidato> candidato = this.candidatosServer.buscarCargo(cargo);
        return ResponseEntity.ok(candidato);
    }

    @PostMapping("/adicionarvaga/{id}/{cadidatoId}")
    public ResponseEntity adicionar( Vaga vaga,@RequestBody Candidato candidato, @PathVariable Long id,@PathVariable Long cadidatoId) {
        var c = this.candidatosServer.getCandidatoPorId(cadidatoId);

        if (candidato == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato não encontrado");

        }

        var adcionar = this.candidatosServer.adcionarVaga(candidato,vaga);
        return ResponseEntity.status(HttpStatus.CREATED).body(adcionar);
    }
}
