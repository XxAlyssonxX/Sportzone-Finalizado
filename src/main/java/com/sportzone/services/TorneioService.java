package com.sportzone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportzone.entities.Torneio;
import com.sportzone.repository.TorneioRepository;

@Service
public class TorneioService {

    private final TorneioRepository torneioRepository;

    @Autowired
    public TorneioService(TorneioRepository torneioRepository) {
        this.torneioRepository = torneioRepository;
    }

    public List<Torneio> getAllTorneio() {
        return torneioRepository.findAll();
    }

    public Torneio getTorneioById(Long id) {
        Optional<Torneio> torneio = torneioRepository.findById(id);
        return torneio.orElse(null);
    }

    public Torneio salvarTorneio(Torneio torneio) {
        return torneioRepository.save(torneio);
    }

    public Torneio updateTorneio(Long id, Torneio updatedTorneio) {
        Optional<Torneio> existingTorneio = torneioRepository.findById(id);
        if (existingTorneio.isPresent()) {
            updatedTorneio.setId(id);
            return torneioRepository.save(updatedTorneio);
        }
        return null;
    }

    public boolean deleteTorneio(Long id) {
        Optional<Torneio> existingTorneio = torneioRepository.findById(id);
        if (existingTorneio.isPresent()) {
            torneioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
