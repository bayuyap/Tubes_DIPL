package com.example.tubes_rpldi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends AppCompatActivity {
    TextInputEditText etUsername, etPassword;
    Button btnLogin,btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        btnRegister = findViewById(R.id.btnToRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(toRegister);
                overridePendingTransition(0,0);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemberLogin();
            }
        });
    }

    private void MemberLogin() {
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Please enter your username");
            etUsername.requestFocus();

        }

        if (TextUtils.isEmpty(password)) {
            etUsername.setError("Please enter your password");
            etPassword.requestFocus();

        }

        class MemberLogin extends AsyncTask<Void,Void,String>{
            ProgressBar progressBarlogin;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBarlogin = (ProgressBar) findViewById(R.id.progressBar);
                progressBarlogin.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBarlogin.setVisibility(View.GONE);

                try {
                    JSONObject objLogin = new JSONObject(s);
                    if (!objLogin.getBoolean("error")){
                        Toast.makeText(getApplicationContext(), objLogin.getString("message"), Toast.LENGTH_SHORT).show();

                        JSONObject memberJSON = objLogin.getJSONObject("member");

                        Member member = new Member(
                                memberJSON.getInt("id_member"),
                                memberJSON.getString("name"),
                                memberJSON.getString("username"));

                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(member);
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();

                HashMap<String,String> loginParams = new HashMap<>();
                loginParams.put("username", username);
                loginParams.put("password", password);
                return requestHandler.sendPostRequest(ConnectionURL.URL_LOGIN, loginParams);
            }


        }
        MemberLogin memberLogin = new MemberLogin();
        memberLogin.execute();
    }
}