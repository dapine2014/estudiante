package com.makers.solution.student.application.usercase;

import com.makers.solution.student.application.dto.StudentDto;
import com.makers.solution.student.domain.model.Student;
import com.makers.solution.student.infraestructure.repository.StudentRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest
class CrudCaseImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private CrudCaseImpl crudCase;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void saveStudenTest() {
        // Mocking
        StudentDto studentDto = new StudentDto();
        studentDto.setNombre("Estudiante de prueba");
        studentDto.setEdad(17);
        studentDto.setCiudad("bogota");
        studentDto.setZonaHoraria("bogota/lima");

        Student studentEntity = new Student();
        studentEntity.setNombre("Estudiante de prueba");
        when(studentRepository.saveAndFlush(any(Student.class))).thenReturn(studentEntity);
        // Test
        StudentDto result = crudCase.saveStuden(studentDto);

        // Verificación
        assertEquals(studentDto.getNombre(), result.getNombre());
        verify(studentRepository, times(1)).saveAndFlush(any(Student.class));
    }

    @Test
    void showAllStudenTest() {
        // Mocking
        Student studentEntity = new Student();
        studentEntity.setNombre("Estudiante de prueba");

        when(studentRepository.findAll()).thenReturn(Arrays.asList(studentEntity));

        // Test
        List<StudentDto> result = crudCase.showAllStuden();

        // Verificación
        assertEquals(1, result.size());
        assertEquals(studentEntity.getNombre(), result.get(0).getNombre());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void showEstudentIDTest() {
        // Mocking
        Long estudianteId = 1L;
        Student estudianteEntity = new Student();
        estudianteEntity.setId(estudianteId);
        estudianteEntity.setNombre("Estudiante de prueba");

        StudentDto studentDto = new StudentDto();
        studentDto.setId(estudianteId);
        studentDto.setNombre("Estudiante de prueba");

        when(studentRepository.findById(estudianteId)).thenReturn(Optional.of(estudianteEntity));
        when(modelMapper.map(estudianteEntity, StudentDto.class)).thenReturn(studentDto);

        // Test
        Optional<StudentDto> result = crudCase.showEstudentID(estudianteId);

        // Imprimir valores para diagnóstico
        System.out.println("Valor esperado: " + studentDto);
        System.out.println("Valor real: " + result);

        // Verificación
        assertEquals(studentDto.getNombre(), result.get().getNombre());
        verify(studentRepository, times(1)).findById(estudianteId);
    }
}