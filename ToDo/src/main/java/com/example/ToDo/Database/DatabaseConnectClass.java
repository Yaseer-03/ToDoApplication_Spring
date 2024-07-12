package com.example.ToDo.Database;

import com.example.ToDo.Model.todoItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnectClass {

    private static final String url = "jdbc:mysql://localhost:3306/project";
    private static final String username = "root";
    private static final String password = "Yaseer@7386861647";

    // To retrieve the whole data
    public static List<todoItem> getData() {
        List<todoItem> items = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM todo";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String status = rs.getString("status");
                System.out.println("ID: " + id + ", Title: " + title + ", Status: " + status);
                items.add(new todoItem(id, title, status));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    // Retrieving data based on id
    public static todoItem getDataById(int id){
        try(Connection con = DriverManager.getConnection(url, username, password);){
            String get = "select * from todo where id = ?";
            PreparedStatement st = con.prepareStatement(get);
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                int todoId = rs.getInt("id");
                String title = rs.getString("title");
                String status = rs.getString("status");
                return new todoItem(todoId, title, status);
            } else {
                System.out.println("No todoItem found with id: " + id);
            }
        }
        catch(Exception e){
            System.out.println("in catch");
            System.out.println(e);
        }
        return null;
    }

    // Deleting data based on ID
    public static String deleteDataById(int id){
        try(Connection con = DriverManager.getConnection(url, username, password);){
            String get = "delete from todo where id = ?";
            PreparedStatement st = con.prepareStatement(get);
            st.setInt(1,id);
            int rowsAffected = st.executeUpdate();
            return (rowsAffected > 0) ? "Data deleted is successfully": "Invalid Id";
        }
        catch(Exception e){
            System.out.println("In catch");
            System.out.println(e);
        }
        return "null";
    }

    //Adding ToDo
    public static void addData(int id, String title, String status) {
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String insert = "INSERT INTO todo (id, title, status) VALUES (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(insert);
            st.setInt(1, id);
            st.setString(2, title);
            st.setString(3,status);
            int rowsInserted = st.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new todo was inserted successfully.");
            } else {
                System.out.println("Failed to insert new todoItem.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Updating data based on id;
    public static void updateData(todoItem updateItem){
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String update = "UPDATE todo SET status = ? WHERE id = ?";
            PreparedStatement st = con.prepareStatement(update);
            st.setString(1, updateItem.getStatus());
            st.setInt(2, updateItem.getId());
            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("TodoItem with ID " + updateItem.getId() + " was updated successfully.");
            } else {
                System.out.println("Failed to update todoItem with ID " + updateItem.getId() + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
