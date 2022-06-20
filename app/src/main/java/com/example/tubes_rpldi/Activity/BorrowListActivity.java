package com.example.tubes_rpldi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.tubes_rpldi.API.ClientAPI;
import com.example.tubes_rpldi.Adapter.BorrowAdapter;
import com.example.tubes_rpldi.R;
import com.example.tubes_rpldi.connection.SharedPrefManager;
import com.example.tubes_rpldi.model.Borrow;
import com.example.tubes_rpldi.model.Member;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowListActivity extends AppCompatActivity {
    ImageButton btnBack;
    RecyclerView mRecyclerView;
    List<Borrow> data;
    BorrowAdapter adapter;
    Member member = SharedPrefManager.getInstance(this).getUser();
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_list);
        mRecyclerView = findViewById(R.id.rvBorrow);
        btnBack = findViewById(R.id.btnBack2);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent balik = new Intent(BorrowListActivity.this, MainActivity.class);
                startActivity(balik);
                finish();
            }
        });
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.borrowList:
                        return true;
                }
                return true;
            }
        });
        getBorrowList();

    }

    private void getBorrowList() {
        data = new ArrayList<>();
        Call<Borrow> borrowListCall= ClientAPI.getInstance().getMyApi().listBorrow(String.valueOf(member.getId_member()));
        borrowListCall.enqueue(new Callback<Borrow>() {
            @Override
            public void onResponse(Call<Borrow> call, Response<Borrow> response) {
                Borrow borrow = response.body();
                Log.e("Success", "Code : " +String.valueOf(response.code()));
                data = new ArrayList<>(Arrays.asList(borrow.getResult()));
                Log.e("Data", Arrays.toString(borrow.getResult()));
                setData(data);
            }

            @Override
            public void onFailure(Call<Borrow> call, Throwable t) {
                Log.e("Retrofit", "Error : " + t.getLocalizedMessage());
            }
        });
    }

    private void setData(List<Borrow> data) {
        adapter = new BorrowAdapter(data, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(adapter);

    }
}