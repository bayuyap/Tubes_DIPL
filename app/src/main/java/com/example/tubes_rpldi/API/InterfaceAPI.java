package com.example.tubes_rpldi.API;

import com.example.tubes_rpldi.model.Book;
import com.example.tubes_rpldi.model.BookDetail;
import com.example.tubes_rpldi.model.BookList;
import com.example.tubes_rpldi.model.Borrow;
import com.example.tubes_rpldi.model.GetBook;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InterfaceAPI {
    @GET("Member_Buku")
    Call<BookList> getBooks();

    @GET("Member_Buku")
    Call<Book> getBookbyID(@Query("id_book") String id_book);

    @GET("Member_Buku?genre=novel")
    Call<BookList> getNovels();

    @GET("Member_Buku?genre=fantasy")
    Call<BookList> getFantasy();

    @GET("Member_Buku?genre=biography")
    Call<BookList> getBiography();

    @POST("Transaction")
    Call<Borrow> borrowBook(@Body Borrow borrow);

    @GET("Transaction")
    Call<Borrow> listBorrow(@Query("id_member") String id_member);
}
