package com.service.student;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable Integer studentId){
        studentService.deleteStudent(studentId);
    }

}
