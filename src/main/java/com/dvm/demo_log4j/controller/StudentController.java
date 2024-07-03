package com.dvm.demo_log4j.controller;

import com.dvm.demo_log4j.dto.StudentDto;
import com.dvm.demo_log4j.entity.Student;
import com.dvm.demo_log4j.response.ApiResponse;
import com.dvm.demo_log4j.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private static Logger LOGGER = LogManager.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping("students/{id}")
    public ResponseEntity<?> viewStudentId(@PathVariable int id) {
        Student u = studentService.getStudentById(id);
        if(u == null) {
            LOGGER.error("Student not found");
            return ResponseEntity.ok("Student not found");
        }
        ApiResponse<Student> response = new ApiResponse<>(1, 200, "Student found", u);
        LOGGER.info("Student found:", u);
        return ResponseEntity.ok(response);
    }
    @GetMapping("students/name")
    public ResponseEntity<?> viewStudentByName(@RequestParam String name) {
        Student u = studentService.getStudentByName(name);
        if(u == null) {
            LOGGER.error("Student not found");
            return ResponseEntity.ok("Student not found");
        }
        ApiResponse<Student> response = new ApiResponse<>(1, 200, "Student found", u);
        LOGGER.info("Student found:", u);
        return ResponseEntity.ok(response);
    }
    @GetMapping("students/address")
    public ResponseEntity<?> viewStudentByAddress(@RequestParam String address) {
        List<Student> list = studentService.getStudentByAddress(address);
        if(list.size() == 0) {
            LOGGER.error("Student not found");
            return ResponseEntity.ok("Student not found");
        }
        LOGGER.info("Student found:", list);
        ApiResponse<List<Student>> response = new ApiResponse<>(1, 200, "Student found", list);
        return ResponseEntity.ok(response);
    }
    @GetMapping("students/sortByName")
    public ResponseEntity<?> sortStudentsByName() {
        List<Student> listStudent =  studentService.sortStudentsByName();
        if(listStudent.size() == 0) {
            LOGGER.error("Student not found");
            return ResponseEntity.ok("Student not found");
        }
        LOGGER.info("Student sorted by name:", listStudent);
        ApiResponse<List<Student>> response = new ApiResponse<>(1, 200, "Student found", listStudent);
        return ResponseEntity.ok(response);
    }
    @PostMapping("students")
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        String mess = studentService.validateStudent(student);
        if(!mess.equals("true")){
            LOGGER.error(mess);
            return ResponseEntity.ok(mess);
        }
        String message = studentService.saveStudent(student);
        LOGGER.info(message);
        return ResponseEntity.ok(message);
    }
    @PutMapping("students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody StudentDto student) {
        Boolean isUpdateSuccess = studentService.updateStudent(student, id);
        if(!isUpdateSuccess) {
            LOGGER.error("Student not found");
            return ResponseEntity.ok("Student not found");
        }
        LOGGER.info("student updated", student);
        return ResponseEntity.ok("Student updated successfully");
    }
    @DeleteMapping("students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        Boolean isDeleteSuccess = studentService.deleteStudentById(id);
        if(!isDeleteSuccess) {
            LOGGER.error("Student not found");
            return ResponseEntity.ok("Student not found");
        }
        return ResponseEntity.ok("Student deleted successfully");
    }
}
