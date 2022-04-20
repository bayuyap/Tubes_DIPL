package com.example.tubes_rpldi.API;

import com.example.tubes_rpldi.model.BookDetail;
import com.example.tubes_rpldi.model.GetBook;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface InterfaceAPI {
    @GET("Member_Buku")
    Call<GetBook> getBooks();

    @GET("Member_Buku?id_book={id_book}")
    Call<BookDetail> getBookbyID(@Path("id_book") String id_book);
}
