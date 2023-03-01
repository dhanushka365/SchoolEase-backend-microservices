package com.service.student;

import com.service.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final RestTemplate restTemplate;
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

    }
}
