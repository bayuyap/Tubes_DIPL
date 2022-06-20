package com.example.tubes_rpldi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.tubes_rpldi.API.ClientAPI;
import com.example.tubes_rpldi.Adapter.BookAdapter;
import com.example.tubes_rpldi.R;
import com.example.tubes_rpldi.model.Book;
import com.example.tubes_rpldi.model.BookList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FantasyActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    BookAdapter mAdapter;
    List<Book> data;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fantasy);
        mRecyclerView = findViewById(R.id.rv_fantasy);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent balik = new Intent(FantasyActivity.this, MainActivity.class);
                startActivity(balik);
                finish();
            }
        });
        tampil_fantasy();
    }

    public void tampil_fantasy(){
        data = new ArrayList<>();
        Call<BookList> fantasyCall = ClientAPI.getInstance().getMyApi().getFantasy();
        fantasyCall.enqueue(new Callback<BookList>() {
            @Override
            public void onResponse(Call<BookList> call, Response<BookList> response) {
                BookList bookList = response.body();
                Log.e("Success", "Code : " +String.valueOf(response.code()));
                data = new ArrayList<>(Arrays.asList(bookList.getResult()));
                Log.e("Data", Arrays.toString(bookList.getResult()));
                setData(data);

            }

            @Override
            public void onFailure(Call<BookList> call, Throwable t) {
                Log.e("Retrofit", "Error : " + t.getLocalizedMessage());
            }
        });

    }

    public void setData(List<Book> data) {
        mAdapter = new BookAdapter(data, this);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setAdapter(mAdapter);
    }
}