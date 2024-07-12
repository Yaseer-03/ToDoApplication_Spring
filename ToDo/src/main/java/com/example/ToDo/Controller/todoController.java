package com.example.ToDo.Controller;

import com.example.ToDo.Model.todoItem;
import com.example.ToDo.Service.todoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class todoController {
    @Autowired
    todoService service;

    @GetMapping("/")
    public String greet(){
        return "Hello !";
    }

    @GetMapping("/ToDo")
    public List<todoItem> getToDos(){
        return service.getToDos();
    }

    @GetMapping("/ToDo/{id}")
    public todoItem getToDoById(@PathVariable int id){
        return service.getToDoById(id);
    }

    @PostMapping("/ToDo")
    public String addToDoItem(@RequestBody todoItem todo){
        service.addToDoItem(todo);
        return "ToDo added successfully";
    }
    @PutMapping("/ToDo/{id}")
    public String updateToDoItem(@RequestBody todoItem updateItem){
        service.updateToDoItem(updateItem);
        return "Status updated successfully";
    }

    @DeleteMapping("/ToDo/{id}")
    public String deleteToDoItem(@PathVariable int id){
        service.deleteToDoItem(id);
        return "Deleted successfully";
    }
}
