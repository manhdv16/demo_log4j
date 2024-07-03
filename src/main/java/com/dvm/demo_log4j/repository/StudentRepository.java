package com.dvm.demo_log4j.repository;

import com.dvm.demo_log4j.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("select s from Student s where s.name = ?1")
    public Student findByName(String name);
    @Query("select s from Student s where s.address = ?1")
    public List<Student> findByAddress(String address);
}
