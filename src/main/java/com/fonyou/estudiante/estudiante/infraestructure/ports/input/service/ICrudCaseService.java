package com.fonyou.estudiante.estudiante.infraestructure.ports.input.service;
import com.fonyou.estudiante.estudiante.application.dto.EstudianteDto;
import java.util.List;
import java.util.Optional;

public interface ICrudCaseService {
   EstudianteDto saveStuden(EstudianteDto estudiante);
   List<EstudianteDto> showAllStuden();
   Optional<EstudianteDto> showEstudentID(Long id);
   void deleteStuden(Long id);
}
