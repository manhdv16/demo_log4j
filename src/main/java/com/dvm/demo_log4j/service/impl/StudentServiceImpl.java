package com.dvm.demo_log4j.service.impl;

import com.dvm.demo_log4j.dto.StudentDto;
import com.dvm.demo_log4j.entity.Student;
import com.dvm.demo_log4j.repository.StudentRepository;
import com.dvm.demo_log4j.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if(students.size() == 0) {
            return null;
        }
        return students;
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student getStudentByName(String name) {
        Student student = studentRepository.findByName(name);
        if(student == null) {
            return null;
        }
        return student;
    }

    @Override
    public List<Student> getStudentByAddress(String address) {
        List<Student> students = studentRepository.findByAddress(address);
        if(students.size() == 0) {
            return null;
        }
        return students;
    }

    @Override
    public List<Student> sortStudentsByName() {
        List<Student> students = studentRepository.findAll();
        if(students.size() == 0) {
            return null;
        }
        students.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
        return students;
    }

    @Override
    public String saveStudent(Student student) {
        studentRepository.save(student);
        return "Student saved successfully";
    }

    @Override
    public Boolean updateStudent(StudentDto student, int id) {
        Student s = studentRepository.findById(id).orElse(null);
        if(s == null) {
            return false;
        }
        if(student.getName() != s.getName()) s.setName(student.getName());
        if(student.getAddress() != s.getAddress()) s.setAddress(student.getAddress());
        if(student.getEmail() != s.getEmail()) s.setEmail(student.getEmail());
        if(student.getPhone() != s.getPhone()) s.setPhone(student.getPhone());
        studentRepository.save(s);
        return true;
    }

    @Override
    public Boolean deleteStudentById(int id) {
        Student s = studentRepository.findById(id).orElse(null);
        if(s == null) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    @Override
    public String validateStudent(Student student) {
        if(student.getName().equals("")){
            return "Name should not be empty";
        }
        if(student.getAddress().equals("")){
            return "Address should not be empty";
        }
        return "true";
    }
}
