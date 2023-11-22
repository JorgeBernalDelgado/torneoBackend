package com.belen.torneo.repository;

import com.belen.torneo.domain.Deportista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeportistaRepository extends JpaRepository<Deportista, Integer> {
}
