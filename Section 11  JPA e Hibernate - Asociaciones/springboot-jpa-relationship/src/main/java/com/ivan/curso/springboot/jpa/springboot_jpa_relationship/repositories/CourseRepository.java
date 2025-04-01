package com.ivan.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import com.ivan.curso.springboot.jpa.springboot_jpa_relationship.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
