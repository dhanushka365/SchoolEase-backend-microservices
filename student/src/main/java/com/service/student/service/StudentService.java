package com.service.student.service;

import com.service.clients.notification.NotificationRequest;
import com.service.amqp.RabbitMQMessageProducer;
import com.service.student.security.FraudCheckResponse;
import com.service.student.dto.StudentRegistrationRequest;
import com.service.student.dto.StudentUpdateRequest;
import com.service.student.models.Student;
import com.service.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final RestTemplate restTemplate;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public void registerStudent(StudentRegistrationRequest request){
        Student student = Student.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        //todo: check if email valid
        //todo: check if email not taken
        //todo: store student in db
        studentRepository.saveAndFlush(student);


        //todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{studentId}",
                FraudCheckResponse.class,
                student.getEmail()
        );

        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("Fraudster found");
        }


        NotificationRequest notificationRequest = new NotificationRequest(
                student.getId(),
                student.getEmail(),
                String.format("Hi %s, welcome to SchoolEase..",
                        student.getFirstName())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

    }

    public void deleteStudent(Integer studentId){
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            studentRepository.delete(student);
        } else {
            throw new IllegalArgumentException("Student with ID " + studentId + " does not exist");
        }
    }


    public void updateStudent( Integer studentId , StudentUpdateRequest request){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student with ID " + studentId + " does not exist"));

        if (request.firstName() != null) {
            student.setFirstName(request.firstName());
        }

        if (request.lastName() != null) {
            student.setLastName(request.lastName());
        }

        if (request.email() != null) {
            student.setEmail(request.email());
        }

        studentRepository.save(student);
    }

    public Page<Student> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

}
