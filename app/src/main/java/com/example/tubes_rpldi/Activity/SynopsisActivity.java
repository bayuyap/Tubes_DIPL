package com.example.tubes_rpldi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tubes_rpldi.API.ClientAPI;
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
    String id, judul, sinopsis, foto,pdf;
    Button btnPinjam;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synopsis);
        tvTitle = findViewById(R.id.tvJudul);
        tvSinopsis = findViewById(R.id.tvSynopsis);
        imageCover = findViewById(R.id.imageBuku);
        btnPinjam = findViewById(R.id.btnBorrow);
        Intent intent = getIntent();
        id_book = intent.getStringExtra("id_book");
        Log.e("ID", id_book);
        pdf= intent.getStringExtra("pdf");
        Log.e("PDF", pdf);
        btnBack = findViewById(R.id.btnBack3);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent balik = new Intent(SynopsisActivity.this, MainActivity.class);
                startActivity(balik);
                finish();
            }
        });
        getBookDetails();
        btnPinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pinjam = new Intent(SynopsisActivity.this, BorrowActivity.class);
                pinjam.putExtra("ID", id);
                pinjam.putExtra("pdf",pdf);
                startActivity(pinjam);
            }
        });
    }

    private void getBookDetails() {

        Call<Book> bookDetailCall = ClientAPI.getInstance().getMyApi().getBookbyID(id_book);
        bookDetailCall.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Book data = response.body();
                Log.e("Data", String.valueOf(data.getName()));
                if (data != null) {
                    Log.e("Retrofit", "Detail data : " + data.getId_book() + "\n " + data.getName());
                    id = data.getId_book();
                    judul = data.getName();
                    sinopsis = data.getSynopsis();
                    foto = data.getFoto();
                    tvTitle.setText(judul);
                    tvSinopsis.setText(sinopsis);
                    Glide.with(imageCover)
                            .load(Config.IMAGE_URL + foto)
                            .into(imageCover);
                }

            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.e("Retrofit Get : ", t.toString());
            }
        });

    }
}