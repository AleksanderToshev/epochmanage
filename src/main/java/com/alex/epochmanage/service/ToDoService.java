package com.alex.epochmanage.service;


import com.alex.epochmanage.model.ToDo;
import com.alex.epochmanage.repository.ToDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {

    //Everything from the ToDo video

    @Autowired
    ToDoRepo repo;

    public List<ToDo> getAllToDoItems() {
        ArrayList<ToDo> todoList = new ArrayList<>();
        repo.findAll().forEach(toDo -> todoList.add(toDo));
        return todoList;
    }
    public ToDo getToDoItemById(Integer id) {
        return repo.findById(id).get();
    }
    public boolean updateStatus(Integer id) {
        ToDo todo = getToDoItemById(id);
        todo.setStatus("Completed");
        return saveOrUpdateToDoItem(todo);
    }
    public boolean saveOrUpdateToDoItem(ToDo todo){
        ToDo updatedObj = repo.save(todo);

        return getToDoItemById(updatedObj.getId()) != null;
    }
    public boolean deleteToDoItem(Integer id) {
        if (getToDoItemById(id) != null) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

}
