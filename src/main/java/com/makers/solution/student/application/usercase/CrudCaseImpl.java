package com.makers.solution.student.application.usercase;


import com.makers.solution.student.application.dto.StudentDto;
import com.makers.solution.student.application.service.ICrudCaseService;
import com.makers.solution.student.domain.model.Student;
import com.makers.solution.student.infraestructure.repository.StudentRepository;
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
    private StudentRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public StudentDto saveStuden(StudentDto student) {

        LOGGER.info("## START SAVE STUDENT ##");
        Object response = repository.saveAndFlush(mapper.map(student, Student.class));
        LOGGER.info("## SAVED STUDEND ##");

        return mapper.map(response, StudentDto.class);
    }

    @Override
    public List<StudentDto> showAllStuden() {

        LOGGER.info("## START READ STUDENS ##");
        List<StudentDto> students = repository.findAll().stream().map(student -> mapper.map(student, StudentDto.class)).collect(Collectors.toList());
        LOGGER.info("## END OF READING STUDENTS ##");

        return students;
    }

    @Override
    public Optional<StudentDto> showEstudentID(Long id) {
        LOGGER.info("## START SEARCH STUDENT ##");
        Optional<Student> response =  repository.findById(id);
        LOGGER.info("## END SEARCH STUDENT ##");

        return response.map((element) -> mapper.map(element, StudentDto.class));
    }

    @Override
    public void deleteStudent(Long id){
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
