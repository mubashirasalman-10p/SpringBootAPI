package com.demo.crudapi.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.crudapi.model.Student;

public interface Repo extends JpaRepository<Student, Integer> {
	 
}