package com.sportzone.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportzone.entities.Participante;
import com.sportzone.entities.Torneio;
import com.sportzone.services.TorneioService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/Torneio")
public class TorneioController {

    private final TorneioService torneioService;

    @Autowired
    public TorneioController(TorneioService torneioService) {
        this.torneioService = torneioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Torneio> getTorneioById(@PathVariable Long id) {
        Torneio torneio = torneioService.getTorneioById(id);
        if (torneio != null) {
            return ResponseEntity.ok(torneio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Torneio>> getAllTorneio() {
        List<Torneio> torneios = torneioService.getAllTorneio();
        return ResponseEntity.ok(torneios);
    }


    @PostMapping("/")
    public ResponseEntity<Torneio> criarTorneio(@RequestBody Torneio torneio) {
        Torneio criarTorneio = torneioService.salvarTorneio(torneio);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarTorneio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Torneio> updateTorneio(@PathVariable Long id, @RequestBody Torneio torneio) {
        Torneio updatedTorneio = torneioService.updateTorneio(id, torneio);
        if (updatedTorneio != null) {
            return ResponseEntity.ok(updatedTorneio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTorneio(@PathVariable Long id) {
        boolean deleted = torneioService.deleteTorneio(id);
        if (deleted) {
            return ResponseEntity.ok().body("O Torneio foi excluído com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
