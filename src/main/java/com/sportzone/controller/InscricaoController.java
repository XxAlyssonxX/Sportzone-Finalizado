package com.sportzone.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportzone.entities.Inscricao;
import com.sportzone.entities.Participante;
import com.sportzone.entities.Torneio;
import com.sportzone.repository.ParticipanteRepository;
import com.sportzone.repository.TorneioRepository;
import com.sportzone.services.InscricaoService;

@RestController
@RequestMapping("/inscricao")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private TorneioRepository torneioRepository;

    @PostMapping("/inscrever")
    public ResponseEntity<?> inscrever(@RequestParam Long participanteId, @RequestParam Long torneioId) {
        Participante participante = participanteRepository.findById(participanteId).orElse(null);
        Torneio torneio = torneioRepository.findById(torneioId).orElse(null);

        if (participante == null || torneio == null) {
            return ResponseEntity.badRequest().body("Participante ou Torneio não encontrado");
        }

        inscricaoService.inscreverParticipante(participante, torneio);
        return ResponseEntity.ok("Inscrição realizada com sucesso");
    }

    @GetMapping("/minhas-inscricoes")
    public ResponseEntity<?> listarInscricoes(@RequestParam Long participanteId) {
        List<Inscricao> inscricoes = inscricaoService.listarInscricoesPorParticipante(participanteId);
        return ResponseEntity.ok(inscricoes);
    }
}
