package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Component
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        var studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalArgumentException("Student with email " + student.getEmail() + " already exists");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentID) {
        boolean exists = studentRepository.existsById(studentID);
        if (!exists) {
            throw new IllegalArgumentException("Student with id " + studentID + " does not exist");
        }
        studentRepository.deleteById(studentID);
    }

    @Transactional
    public void updateStudent(Long studentID, String name, String email) {
        Student student = studentRepository.findById(studentID).orElseThrow(() -> new IllegalArgumentException("Student with id " + studentID + " does not exist"));
        if (name != null && !name.equals(student.getName()) && !name.isEmpty()) {
            student.setName(name);
        }
        if (email != null && !email.equals(student.getEmail()) && !email.isEmpty()) {
            var studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalArgumentException("Student with email " + email + " already exists");
            }
            student.setEmail(email);
        }
    }

    public Student getStudent(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student with id " + id + " does not exist"));
    }
}
