package com.fonyou.estudiante.estudiante.application.usercase;

import com.fonyou.estudiante.estudiante.application.dto.EstudianteDto;
import com.fonyou.estudiante.estudiante.domain.model.Estudiante;
import com.fonyou.estudiante.estudiante.infraestructure.ports.input.repository.EstudianteRepository;
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
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private CrudCaseImpl crudCase;

    @Mock
    private ModelMapper modelMapper;



    @Test
    void saveStudenTest() {
        // Mocking
        EstudianteDto estudianteDto = new EstudianteDto();
        estudianteDto.setNombre("Estudiante de prueba");
        estudianteDto.setEdad(17);
        estudianteDto.setCiudad("bogota");
        estudianteDto.setZonaHoraria("bogota/lima");

        Estudiante estudianteEntity = new Estudiante();
        estudianteEntity.setNombre("Estudiante de prueba");
        when(estudianteRepository.saveAndFlush(any(Estudiante.class))).thenReturn(estudianteEntity);
        // Test
        EstudianteDto result = crudCase.saveStuden(estudianteDto);

        // Verificación
        assertEquals(estudianteDto.getNombre(), result.getNombre());
        verify(estudianteRepository, times(1)).saveAndFlush(any(Estudiante.class));
    }

    @Test
    void showAllStudenTest() {
        // Mocking
        Estudiante estudianteEntity = new Estudiante();
        estudianteEntity.setNombre("Estudiante de prueba");

        when(estudianteRepository.findAll()).thenReturn(Arrays.asList(estudianteEntity));

        // Test
        List<EstudianteDto> result = crudCase.showAllStuden();

        // Verificación
        assertEquals(1, result.size());
        assertEquals(estudianteEntity.getNombre(), result.get(0).getNombre());
        verify(estudianteRepository, times(1)).findAll();
    }

    @Test
    void showEstudentIDTest() {
        // Mocking
        Long estudianteId = 1L;
        Estudiante estudianteEntity = new Estudiante();
        estudianteEntity.setId(estudianteId);
        estudianteEntity.setNombre("Estudiante de prueba");

        EstudianteDto estudianteDto = new EstudianteDto();
        estudianteDto.setId(estudianteId);
        estudianteDto.setNombre("Estudiante de prueba");

        when(estudianteRepository.findById(estudianteId)).thenReturn(Optional.of(estudianteEntity));
        when(modelMapper.map(estudianteEntity, EstudianteDto.class)).thenReturn(estudianteDto);

        // Test
        Optional<EstudianteDto> result = crudCase.showEstudentID(estudianteId);

        // Imprimir valores para diagnóstico
        System.out.println("Valor esperado: " + estudianteDto);
        System.out.println("Valor real: " + result);

        // Verificación
        assertEquals(estudianteDto.getNombre(), result.get().getNombre());
        verify(estudianteRepository, times(1)).findById(estudianteId);
    }
}