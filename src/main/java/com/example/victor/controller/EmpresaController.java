package com.example.victor.controller;
import java.util.List;
import java.util.Optional;

import com.example.victor.model.entity.Empresa;
import com.example.victor.model.entity.Endereco;
import com.example.victor.model.entity.Vaga;
import com.example.victor.server.EmpresaServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaServer empresaServer;

    @PostMapping
    public ResponseEntity<Empresa> salvarEmpresa(@RequestBody Empresa empresa) {
        Empresa novaEmpresa = empresaServer.save(empresa);
        return ResponseEntity.ok().body(novaEmpresa);
    }

    @GetMapping
    public List<Empresa> obterTodasEmpresas() {
        return empresaServer.empresas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obterEmpresaPorId(@PathVariable Long id) {
        var empresa = empresaServer.empresa(id);
        return empresa.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> editarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        Optional<Empresa> empresaAtualizada = empresaServer.editar(empresa, id);
        return empresaAtualizada.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Long id) {
        empresaServer.empresaDeletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarnome/{nome}")
    public  List<Empresa> obeterEmpresaPornome(@PathVariable String nome){
        return empresaServer.bucarPorNome(nome);
    }

    @GetMapping("/buscarestado/{estado}")
    public  List<Empresa> obterEmpresaPorestado(@PathVariable String estado){
        return empresaServer.buscarPorEstado(estado);
    }

    @PostMapping("/{id}/criarVaga")
    public ResponseEntity criarVaga(@RequestBody Vaga vaga , @PathVariable Long id) {
        var empresa = this.empresaServer.obterEmpresaPorId(id).orElse(null);

        if (empresa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrado");

        }

        var adcionar = this.empresaServer.criarVaga(empresa, vaga);
        return ResponseEntity.status(HttpStatus.CREATED).body(adcionar);
    }
    @PostMapping("/{id}/criarEndereco")
    public ResponseEntity criarEndereco(@RequestBody Endereco endereco , @PathVariable Long id) {
        var empresa = this.empresaServer.obterEmpresaPorId(id).orElse(null);

        if (empresa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrado");

        }

        var adcionar = this.empresaServer.criarEndereco(empresa, endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(adcionar);
    }

}

