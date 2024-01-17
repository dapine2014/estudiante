package com.fonyou.estudiante.estudiante.infraestructure.ports.input.repository;

import com.fonyou.estudiante.estudiante.domain.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}