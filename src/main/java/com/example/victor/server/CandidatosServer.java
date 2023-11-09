package com.example.victor.server;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.victor.model.entity.Candidato;
import com.example.victor.model.entity.Experiencia;
import com.example.victor.model.entity.Vaga;
import com.example.victor.model.repository.CandidatoRepository;
import com.example.victor.model.repository.ExperienciaRepository;
import com.example.victor.model.repository.VagaRepository;

@Service
public class CandidatosServer {
    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private ExperienciaRepository experienciaRepository;
    @Autowired
    private VagaRepository vagaRepository;

    public List<Candidato> candidatos() {
        return candidatoRepository.findAll();

    }


    public Optional<Candidato> candidato(@PathVariable Long id) {
        return candidatoRepository.findById(id);
    }


    public Candidato save(@RequestBody Candidato candidato) {
        return candidatoRepository.save(candidato);
    }


    public void candidatoDeletar(@PathVariable Long id) {
        candidatoRepository.deleteById(id);
    }


    public Optional<Candidato> editar(@RequestBody Candidato candidato, @PathVariable Long id) {
        return candidatoRepository.findById(id).map(c -> {
            c.setNome(candidato.getNome());
            c.setEmail(candidato.getEmail());
            c.setTelefone(candidato.getTelefone());
            return candidatoRepository.save(c);
        });

    }

    public Experiencia adcionarExperiencia(Candidato candidatos, Experiencia experiencia) {
        experiencia.setCandidato(candidatos);
        return this.experienciaRepository.save(experiencia);
    }

    public List<Candidato> buscarCidade(String cidade) {
        List<Candidato> candidatos = this.candidatoRepository.findByEnderecosCidade(cidade);

        return candidatos;
    }

    public List<Candidato> buscarEstado(String estado) {
        List<Candidato> candidatos = this.candidatoRepository.findByEnderecosEstado(estado);

        return candidatos;
    }


    public List<Candidato> buscarCargo(String cargo) {
        List<Candidato> candidatos = this.candidatoRepository.findByExperienciasCargo(cargo);

        return candidatos;
    }

    public Candidato getCandidatoPorId(Long id) {
        return this.candidatoRepository.findById(id).orElse(null);
    }

    public Vaga adcionarVaga(Candidato candidato , Vaga vaga){
        vaga.setCandidato(candidato);
        return this.vagaRepository.save(vaga);
    }
}

