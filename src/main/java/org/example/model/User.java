package org.example.model;

public class User {
    private int id;
    private String username;
    private int roleId;

    public User(int id, String username, int roleId) {
        this.id = id;
        this.username = username;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getRoleId() {
        return roleId;
    }
}
