package com.alex.epochmanage.repository;

import com.alex.epochmanage.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Integer> {

}
