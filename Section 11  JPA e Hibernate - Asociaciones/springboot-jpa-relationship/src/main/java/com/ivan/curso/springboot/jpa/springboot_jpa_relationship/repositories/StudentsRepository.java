package com.ivan.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import com.ivan.curso.springboot.jpa.springboot_jpa_relationship.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentsRepository extends CrudRepository<Student, Long> {

    @Query("SELECT s FROM Student s left JOIN FETCH s.courses WHERE s.id = ?1")
    Optional<Student> findOneByIdCourse(Long id);


}
