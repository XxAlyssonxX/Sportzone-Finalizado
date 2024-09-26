package com.sportzone.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportzone.entities.Inscricao;
import com.sportzone.entities.Participante;
import com.sportzone.entities.Torneio;
import com.sportzone.repository.InscricaoRepository;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    public Inscricao inscreverParticipante(Participante participante, Torneio torneio) {
        Inscricao inscricao = new Inscricao();
        inscricao.setParticipante(participante);
        inscricao.setTorneio(torneio);
        inscricao.setDataInscricao(LocalDate.now());
        return inscricaoRepository.save(inscricao);
    }

    public List<Inscricao> listarInscricoesPorParticipante(Long participanteId) {
        return inscricaoRepository.findByParticipanteId(participanteId);
    }
}
