package com.example.victor.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.victor.model.entity.Candidatura;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
}
