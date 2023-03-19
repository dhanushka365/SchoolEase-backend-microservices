package com.service.student.controllers;

import com.service.student.dto.StudentRegistrationRequest;
import com.service.student.dto.StudentUpdateRequest;
import com.service.student.models.Student;
import com.service.student.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @PostMapping("/registration/")
     public void registerStudent (@RequestBody StudentRegistrationRequest studentRegistrationRequest){
        log.info("new student registration {}", studentRegistrationRequest);
        studentService.registerStudent(studentRegistrationRequest);
    }

    //localhost:8080/api/v1/students/view/?page=1&size=10
    @GetMapping("/view/")
    public Page<Student> getStudents(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return studentService.getStudents(PageRequest.of(page, size));
    }

    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable Integer studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/update/{studentId}")
    public  void updateStudent(@PathVariable Integer studentId, @RequestBody StudentUpdateRequest studentUpdateRequest ){
        studentService.updateStudent(studentId ,studentUpdateRequest);
    }
}
