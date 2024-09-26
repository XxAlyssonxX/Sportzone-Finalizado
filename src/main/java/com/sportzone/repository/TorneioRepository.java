package com.sportzone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportzone.entities.Torneio;


public interface TorneioRepository extends JpaRepository <Torneio,Long>  {

}
