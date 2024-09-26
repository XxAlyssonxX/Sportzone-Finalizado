package com.sportzone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportzone.entities.Participante;
import com.sportzone.repository.ParticipanteRepository;
import com.sportzone.services.ParticipanteService;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {

    @Autowired
    private ParticipanteRepository participanteRepository; // Ajuste aqui
    
    @Autowired
    private ParticipanteService participanteService; // Serviço de participante

    @GetMapping("/{id}")
    public ResponseEntity<Participante> getParticipanteById(@PathVariable Long id) {
        Participante participante = participanteService.getParticipanteById(id);
        if (participante != null) {
            return ResponseEntity.ok(participante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Participante>> getAllParticipantes() {
        List<Participante> participantes = participanteService.getAllParticipante();
        return ResponseEntity.ok(participantes);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Participante participante) {
        // Verifica se o email já está cadastrado
        if (participanteRepository.findByEmail(participante.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }
        participanteRepository.save(participante);
        return ResponseEntity.ok("Cadastro realizado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String senha) {
        Participante p = participanteRepository.findByEmail(email);
        if (p != null && p.getSenha().equals(senha)) {
            return ResponseEntity.ok(p); // Retorna o objeto Participante como JSON
        }
        return ResponseEntity.badRequest().body("Credenciais inválidas");
    }
          
}
