package com.sportzone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sportzone.entities.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Participante findByEmail(String email);  // Método para buscar por email
}
