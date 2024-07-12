package com.example.ToDo.Service;
import java.util.Arrays;

import com.example.ToDo.Database.DatabaseConnectClass;
import com.example.ToDo.Model.todoItem;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Service
public class todoService {
      List<todoItem> items = new ArrayList<>();


    public List<todoItem> getToDos() {
        return DatabaseConnectClass.getData();
    }

    public todoItem getToDoById(int id) {
//        return items.stream().filter(i -> i.getId() == id).findFirst().orElse(new todoItem(404 ,"Item doesn't found"));
        return DatabaseConnectClass.getDataById(id);
    }

    public void addToDoItem(todoItem todo) {
        DatabaseConnectClass.addData(todo.getId(), todo.getTitle(), todo.getStatus());
    }

    public void deleteToDoItem(int id){
        DatabaseConnectClass.deleteDataById(id);
    }

    public void updateToDoItem(todoItem updateItem) {
         DatabaseConnectClass.updateData(updateItem);
    }
}
