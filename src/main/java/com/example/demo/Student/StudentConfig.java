package com.example.demo.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.AUGUST;
import static java.time.Month.JANUARY;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            var ilhamMuradov = new Student("Ilham Muradov", "muradovilham039@gmail.com", LocalDate.of(2004, AUGUST, 31));

            var elnurVelizade = new Student("Elnur Velizade", "elnurvalizade033@gmail.com", LocalDate.of(2006, JANUARY, 15));

            repository.saveAll(List.of(ilhamMuradov, elnurVelizade));
        };

    }
}

