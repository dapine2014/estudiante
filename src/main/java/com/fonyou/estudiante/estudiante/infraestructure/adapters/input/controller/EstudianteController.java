package com.fonyou.estudiante.estudiante.infraestructure.adapters.input.controller;


import com.fonyou.estudiante.estudiante.application.dto.EstudianteDto;
import com.fonyou.estudiante.estudiante.infraestructure.ports.input.service.ICrudCaseService;
import com.fonyou.studens.studens.utils.Responce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fonyou.studens.studens.utils.Constant.*;

@RestController
@RequestMapping("/api")
public class EstudianteController {

    @Autowired
    private ICrudCaseService crudCaseService;

    @PostMapping("/saveupdate")
    public  ResponseEntity<Object> setCreateUpdateStudent(@RequestBody EstudianteDto student){
        Object response =  crudCaseService.saveStuden(student);
        return ResponseEntity.ok().body(new Responce(SUCCES_OK, RESPONSE_OK, response));
    }


    @GetMapping("/find/all")
    public ResponseEntity<Object> getAllStudent(){
        List<EstudianteDto> response  = crudCaseService.showAllStuden();
        if(!response.isEmpty()) {

            return ResponseEntity.ok().body(new Responce(SUCCES_OK, RESPONSE_OK, response));
        } else {

           return  ResponseEntity.badRequest().body(new Responce(SUCCES_ERROR, RESPONSE_NOT_OK, null));
        }
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<Object> getStudentByID(@PathVariable Long id){
        Object response =  crudCaseService.showEstudentID(id);
        return ResponseEntity.ok().body(new Responce(SUCCES_OK, RESPONSE_OK, response));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> setDeleteStudent(@PathVariable Long id){
        crudCaseService.deleteStuden(id);
        return  ResponseEntity.ok().body(new Responce(SUCCES_OK, RESPONSE_OK, DELETE_OK));
    }



}
