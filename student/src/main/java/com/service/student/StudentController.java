package com.service.student;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @PostMapping
     public void registerStudent (@RequestBody StudentRegistrationRequest studentRegistrationRequest){
        log.info("new student registration {}", studentRegistrationRequest);
        studentService.registerStudent(studentRegistrationRequest);
    }

}
