package com.example.tubes_rpldi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tubes_rpldi.API.InterfaceAPI;
import com.example.tubes_rpldi.R;
import com.example.tubes_rpldi.connection.Config;
import com.example.tubes_rpldi.model.Book;
import com.example.tubes_rpldi.model.BookDetail;
import com.example.tubes_rpldi.model.GetBook;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SynopsisActivity extends AppCompatActivity {
    TextView tvTitle, tvSinopsis;
    InterfaceAPI mInterfaceAPI;
    ImageView imageCover;
    String id_book;
    String id, judul, sinopsis, foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synopsis);
        tvTitle = findViewById(R.id.tvJudul);
        tvSinopsis = findViewById(R.id.tvSynopsis);
        imageCover = findViewById(R.id.imageBuku);
        Intent intent = getIntent();
        id_book = intent.getStringExtra("id_book");

        getBookDetails();
    }

    private void getBookDetails() {

        Call<BookDetail> bookDetailCall =mInterfaceAPI.getBookbyID(id_book);
        bookDetailCall.enqueue(new Callback<BookDetail>() {
            @Override
            public void onResponse(Call<BookDetail> call, Response<BookDetail> response) {
                BookDetail dataBook = response.body();
                if (dataBook != null) {
                    Log.e("Retrofit", "Detail data : " + dataBook.getBook().getName() );
                    id = dataBook.getBook().getId_book();
                    judul = dataBook.getBook().getName();
                    sinopsis = dataBook.getBook().getSynopsis();
                    foto = dataBook.getBook().getFoto();
                    tvTitle.setText(id);
                    tvSinopsis.setText(sinopsis);
                    Glide.with(imageCover)
                            .load(Config.IMAGE_URL + foto)
                            .apply(new RequestOptions().override(250,350))
                            .into(imageCover);
                }
            }

            @Override
            public void onFailure(Call<BookDetail> call, Throwable t) {
                Log.e("Retrofit get ", t.toString());
            }
        });

    }
}