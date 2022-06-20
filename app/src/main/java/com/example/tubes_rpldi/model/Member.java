package com.example.tubes_rpldi.model;

public class Member {
    private int id_member;
    private String name, username,password;

    public Member(int id_member, String name, String username) {
        this.id_member = id_member;
        this.name = name;
        this.username = username;
    }


    public int getId_member() {
        return id_member;
    }

    public void setId_member(int id_member) {
        this.id_member = id_member;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
