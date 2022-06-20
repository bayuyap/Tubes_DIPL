package com.example.tubes_rpldi.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Borrow{

    @SerializedName("id_book")
    public String id_book;
    @SerializedName("id_member")
    public  String id_member;
    @SerializedName("date_borrow")
    public String date_borrow;
    @SerializedName("date_return")
    public String date_return;
    @SerializedName("book_path")
    public String book_path;
    @SerializedName("name")
    public String name;

    private Borrow[] result;

    public Borrow(String id_book, String id_member, String borrow_date, String return_date, String book_path) {
        this.id_book = id_book;
        this.id_member = id_member;
        this.date_borrow = borrow_date;
        this.date_return = return_date;
        this.book_path = book_path;
    }

    public Borrow(String name, String id_member, String return_date, String book_path) {
        this.name = name;
        this.id_member = id_member;
        this.date_return = return_date;
        this.book_path = book_path;
    }

    public String getId_book() {
        return id_book;
    }

    public void setId_book(String id_book) {
        this.id_book = id_book;
    }

    public String getId_member() {
        return id_member;
    }

    public void setId_member(String id_member) {
        this.id_member = id_member;
    }

    public String getDate_borrow() {
        return date_borrow;
    }

    public void setDate_borrow(String date_borrow) {
        this.date_borrow = date_borrow;
    }

    public String getDate_return() {
        return date_return;
    }

    public void setDate_return(String date_return) {
        this.date_return = date_return;
    }

    public String getBook_path() {
        return book_path;
    }

    public void setBook_path(String book_path) {
        this.book_path = book_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Borrow[] getResult() {
        return result;
    }

    public void setResult(Borrow[] result) {
        this.result = result;
    }
}
