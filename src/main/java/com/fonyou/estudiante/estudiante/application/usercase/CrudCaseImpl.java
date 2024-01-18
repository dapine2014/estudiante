package com.fonyou.estudiante.estudiante.application.usercase;
import com.fonyou.estudiante.estudiante.application.dto.EstudianteDto;
import com.fonyou.estudiante.estudiante.domain.model.Estudiante;
import com.fonyou.estudiante.estudiante.infraestructure.ports.input.repository.EstudianteRepository;
import com.fonyou.estudiante.estudiante.infraestructure.ports.input.service.ICrudCaseService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CrudCaseImpl implements ICrudCaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrudCaseImpl.class);

    @Autowired
    private EstudianteRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public EstudianteDto saveStuden(EstudianteDto estudiante) {

        LOGGER.info("## START SAVE STUDENT ##");
        Object response = repository.saveAndFlush(mapper.map(estudiante, Estudiante.class));
        LOGGER.info("## SAVED STUDEND ##");

        return mapper.map(response, EstudianteDto.class);
    }

    @Override
    public List<EstudianteDto> showAllStuden() {

        LOGGER.info("## START READ STUDENS ##");
        List<EstudianteDto> estudiantes = repository.findAll().stream().map(estudiante -> mapper.map(estudiante, EstudianteDto.class)).collect(Collectors.toList());
        LOGGER.info("## END OF READING STUDENTS ##");

        return estudiantes;
    }

    @Override
    public Optional<EstudianteDto> showEstudentID(Long id) {
        LOGGER.info("## START SEARCH STUDENT ##");
        Optional<Estudiante> response =  repository.findById(id);
        LOGGER.info("## END SEARCH STUDENT ##");

        return response.map((element) -> mapper.map(element, EstudianteDto.class));
    }

    @Override
    public void deleteStuden(Long id){
            repository.deleteById(id);
    }

    @Override
    public Boolean isZonaHoraria(String zona){

        LOGGER.info("## START VALIDATE ZONA ##");
        try {
            ZoneId.of(zona);

            return  true;
        } catch (Exception e){
            LOGGER.info("## ERROR IN  ZONA-HORARIA ##");

            return false;
        }

    }

}
