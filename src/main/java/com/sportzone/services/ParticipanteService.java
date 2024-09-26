package com.sportzone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportzone.entities.Participante;
import com.sportzone.repository.ParticipanteRepository;

@Service
public class ParticipanteService {

private final ParticipanteRepository ParticipanteRepository;
	
	@Autowired
	public ParticipanteService(ParticipanteRepository ParticipanteRepository) {
		this.ParticipanteRepository = ParticipanteRepository;
	}

	public List<Participante> getAllParticipante() {
		return ParticipanteRepository.findAll();
	}

	public Participante getParticipanteById(Long id) {
		Optional<Participante> Participante = ParticipanteRepository.findById(id);
		return Participante.orElse(null);
	}
	//Query Method
	//public List<Participante> getPorData(String data){
		//return ParticipanteRepository.findByData(data);
	//}

	public Participante salvarParticipante(Participante Participante) {
		return ParticipanteRepository.save(Participante);
	}

	public Participante updateParticipante(Long id, Participante updatedParticipante) {
		Optional<Participante> existingParticipante = ParticipanteRepository.findById(id);
		if (existingParticipante.isPresent()) {
			updatedParticipante.setId(id);
			return ParticipanteRepository.save(updatedParticipante);
		}
		return null;
	}

	public boolean deleteParticipante(Long id) {
		Optional<Participante> existingParticipante = ParticipanteRepository.findById(id);
		if (existingParticipante.isPresent()) {
			ParticipanteRepository.deleteById(id);
			return true;
		}
		return false;
	}



}
