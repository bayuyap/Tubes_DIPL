package com.example.tubes_rpldi.model;

import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("id_book")
    private String id_book;
    @SerializedName("name")
    private String name;
    @SerializedName("author")
    private String author;
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("synopsis")
    private String synopsis;
    @SerializedName("foto")
    private String foto;
    @SerializedName("pdf")
    private String pdf;



    public Book() {
    }

    public Book(String id_book, String name, String author, String publisher, String synopsis, String foto) {
        this.id_book = id_book;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.synopsis = synopsis;
        this.foto = foto;
    }

    public Book(String id_book, String name, String synopsis, String foto) {
        this.id_book = id_book;
        this.name = name;
        this.synopsis = synopsis;
        this.foto = foto;
    }

    public String getId_book() {
        return id_book;
    }

    public void setId_book(String id_book) {
        this.id_book = id_book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
