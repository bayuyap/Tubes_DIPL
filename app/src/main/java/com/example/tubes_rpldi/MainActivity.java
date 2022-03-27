package com.example.tubes_rpldi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tubes_rpldi.connection.SharedPrefManager;
import com.example.tubes_rpldi.model.Member;

public class MainActivity extends AppCompatActivity {
    TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        tvName = findViewById(R.id.tvName);

        Member member = SharedPrefManager.getInstance(this).getUser();

        tvName.setText(member.getName());
    }
}