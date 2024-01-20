package com.makers.solution.student.application.service;
import com.makers.solution.student.application.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface ICrudCaseService {
   StudentDto saveStuden(StudentDto student);
   List<StudentDto> showAllStuden();
   Optional<StudentDto> showEstudentID(Long id);
   Boolean isZonaHoraria(String zona);
   void deleteStudent(Long id);
}
