package com.example.tubes_rpldi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tubes_rpldi.API.ClientAPI;
import com.example.tubes_rpldi.API.InterfaceAPI;
import com.example.tubes_rpldi.Adapter.BookAdapter;
import com.example.tubes_rpldi.R;
import com.example.tubes_rpldi.connection.SharedPrefManager;
import com.example.tubes_rpldi.model.Book;
import com.example.tubes_rpldi.model.BookList;
import com.example.tubes_rpldi.model.Member;
import com.example.tubes_rpldi.model.GetBook;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    TextView tvName;
    RecyclerView mRecyclerView;
    BookAdapter mAdapter;
    List<Book> data;
    ImageButton btnLogout;
    CardView cvNovel,cvFantasy,cvBiography;
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogout = findViewById(R.id.btnKeluar);
        mRecyclerView = findViewById(R.id.rv_book);
        cvNovel = findViewById(R.id.cv_novel);
        cvFantasy = findViewById(R.id.cv_fantasy);
        cvBiography = findViewById(R.id.cv_biography);
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.borrowList:
                        startActivity(new Intent(getApplicationContext(), BorrowListActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return true;
            }
        });
        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        tvName = findViewById(R.id.tvName);

        Member member = SharedPrefManager.getInstance(this).getUser();

        tvName.setText(member.getName());

        refresh();
        cvNovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity.this, NovelActivity.class));
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });


    }

    public void refresh(){
        data = new ArrayList<>();
        Call<BookList> BookCall = ClientAPI.getInstance().getMyApi().getBooks();
        BookCall.enqueue(new Callback<BookList>() {
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
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,6));
        mRecyclerView.setAdapter(mAdapter);
    }
}