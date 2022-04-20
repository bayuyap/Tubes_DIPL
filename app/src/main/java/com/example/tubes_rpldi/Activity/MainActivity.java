package com.example.tubes_rpldi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.tubes_rpldi.API.ClientAPI;
import com.example.tubes_rpldi.API.InterfaceAPI;
import com.example.tubes_rpldi.Adapter.BookAdapter;
import com.example.tubes_rpldi.R;
import com.example.tubes_rpldi.connection.SharedPrefManager;
import com.example.tubes_rpldi.model.Book;
import com.example.tubes_rpldi.model.Member;
import com.example.tubes_rpldi.model.GetBook;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView tvName;
    InterfaceAPI mInterfaceAPI;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    LinearLayoutManager horizontalLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rv_book);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        horizontalLayout = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(horizontalLayout);
        mInterfaceAPI = ClientAPI.getClient().create(InterfaceAPI.class);
        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        tvName = findViewById(R.id.tvName);

        Member member = SharedPrefManager.getInstance(this).getUser();

        tvName.setText(member.getName());

        refresh();
    }

    public void refresh(){
        Call<GetBook> BookCall = mInterfaceAPI.getBooks();
        BookCall.enqueue(new Callback<GetBook>() {
            @Override
            public void onResponse(Call<GetBook> call, Response<GetBook> response) {
                List<Book> bookList = response.body().getBookList();
                Log.e("Retrofit", "Jumlah Data Buku : " +String.valueOf(bookList.size()));
                mAdapter = new BookAdapter(bookList,getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetBook> call, Throwable t) {
                Log.e("Retrofit get ", t.toString());
            }
        });
    }
}