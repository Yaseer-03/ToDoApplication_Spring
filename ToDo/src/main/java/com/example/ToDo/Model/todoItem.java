package com.example.ToDo.Model;

import org.springframework.stereotype.Component;

@Component
public class todoItem {
    private int id;
    private String title;
    private String status;

    public todoItem() {
    }

    public todoItem(int id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status  = status;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setStatus(String status) { this.status = status;}
    public String getStatus() { return status;}

    @Override
    public String toString(){
        return "ID is: " +  id + "Title: " + title;
    }


}