package com.example.tubes_rpldi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetBook {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Book> bookList;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
