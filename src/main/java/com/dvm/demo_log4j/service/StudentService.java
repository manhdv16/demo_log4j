package com.dvm.demo_log4j.service;

import com.dvm.demo_log4j.dto.StudentDto;
import com.dvm.demo_log4j.entity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudents();
    public Student getStudentById(int id);
    public Student getStudentByName(String name);
    public List<Student> getStudentByAddress(String address);
    public List<Student> sortStudentsByName();
    public String saveStudent(Student student);
    public Boolean updateStudent(StudentDto student, int id);
    public Boolean deleteStudentById(int id);
    public String validateStudent(Student student);
}
