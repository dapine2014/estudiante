package com.makers.solution.student.infraestructure.controller;

import com.makers.solution.student.application.dto.StudentDto;
import com.makers.solution.student.application.service.ICrudCaseService;
import com.makers.solution.student.utils.Responce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.makers.solution.student.utils.Constant.*;
import java.util.List;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000/","http://172.19.0.1:3000/"}, methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE })
public class StudentController {

    @Autowired
    private ICrudCaseService crudCaseService;

    @PostMapping("/saveupdate")
    public  ResponseEntity<Object> setCreateUpdateStudent(@RequestBody StudentDto student){

        Object response = crudCaseService.saveStuden(student);
        if(!(response == null)) {

            return ResponseEntity.ok().body(new Responce(SUCCES_OK, RESPONSE_OK, response));

        } else {

            return  ResponseEntity.badRequest().body(new Responce(SUCCES_ERROR,RESPONSE_ERROR,null));
        }
    }


    @GetMapping({"/find/all","/"})
    public ResponseEntity<Object> getAllStudent(){
        List<StudentDto> response  = crudCaseService.showAllStuden();
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
        crudCaseService.deleteStudent(id);
        return  ResponseEntity.ok().body(new Responce(SUCCES_OK, RESPONSE_OK, DELETE_OK));
    }



}
