package com.example.tubes_rpldi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tubes_rpldi.R;
import com.example.tubes_rpldi.connection.ConnectionURL;
import com.example.tubes_rpldi.connection.RequestHandler;
import com.example.tubes_rpldi.connection.SharedPrefManager;
import com.example.tubes_rpldi.model.Member;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText etName, etUsername, etPassword;
    Button btnLogin, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnToLogin);
        btnRegister = findViewById(R.id.buttonRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(toLogin);
                overridePendingTransition(0,0);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        final String name, username, password;
        name = etName.getText().toString();
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            etName.setError("Please enter your name");
            etUsername.requestFocus();

        }

        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Please enter your username");
            etUsername.requestFocus();

        }

        if (TextUtils.isEmpty(password)) {
            etUsername.setError("Please enter your password");
            etPassword.requestFocus();

        }

        class registerUser extends AsyncTask<Void, Void, String>{
            ProgressBar progressBarregister;

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();

                HashMap<String,String > paramsRegister = new HashMap<>();
                paramsRegister.put("name",name);
                paramsRegister.put("username",username);
                paramsRegister.put("password", password);

                return requestHandler.sendPostRequest(ConnectionURL.URL_REGISTER, paramsRegister);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBarregister = (ProgressBar) findViewById(R.id.progressBar);
                progressBarregister.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBarregister.setVisibility(View.GONE);

                try {
                    JSONObject objectRegister = new JSONObject(s);
                    if (!objectRegister.getBoolean("error")){
                        Toast.makeText(getApplicationContext(), objectRegister.getString("message"), Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_LONG).show();
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }

    }
}