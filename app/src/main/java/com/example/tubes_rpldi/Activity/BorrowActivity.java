package com.example.tubes_rpldi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tubes_rpldi.API.ClientAPI;
import com.example.tubes_rpldi.R;
import com.example.tubes_rpldi.connection.Config;
import com.example.tubes_rpldi.connection.SharedPrefManager;
import com.example.tubes_rpldi.model.Book;
import com.example.tubes_rpldi.model.Borrow;
import com.example.tubes_rpldi.model.Member;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowActivity extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
    String id_buku,url_pdf;
    TextView tvcurrentDate, tvReturnDate,tvDuration,tvJudul;
    String tglPinjam, tglKembali;
    LocalDate localDate = LocalDate.now();
    SimpleDateFormat format;
    Button pinjam, cancel;
    ImageView imgBuku;
    String id, judul, foto;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow);
        tvReturnDate = findViewById(R.id.tvBorrowDate);
        tvcurrentDate = findViewById(R.id.tvCurrentDate);
        tvDuration = findViewById(R.id.tvDuration);
        tvJudul = findViewById(R.id.tvJudul);
        imgBuku = findViewById(R.id.imageBuku);
        pinjam = findViewById(R.id.btnPinjam);
        cancel = findViewById(R.id.btnCancel);
        id_buku = getIntent().getStringExtra("ID");
        Log.e("Id Book : ", id_buku);
        url_pdf = getIntent().getStringExtra("pdf");
        Log.e("pdf", url_pdf);
        btnBack = findViewById(R.id.btnBack5);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent balik = new Intent(BorrowActivity.this, MainActivity.class);
                startActivity(balik);
                finish();
            }
        });
        getBookDetails();
        format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        tvcurrentDate.setText(String.valueOf(localDate));
        tvReturnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
        pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrowBook();
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             Intent cancel = new Intent(BorrowActivity.this, MainActivity.class);
              startActivity(cancel);
               finish();
            }
        });
    }
    private void getBookDetails() {

        Call<Book> bookDetailCall = ClientAPI.getInstance().getMyApi().getBookbyID(id_buku);
        bookDetailCall.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Book data = response.body();
                Log.e("Data", String.valueOf(data.getName()));
                if (data != null) {
                    Log.e("Retrofit", "Detail data : " + data.getId_book() + "\n " + data.getName());
                    judul = data.getName();
                    foto = data.getFoto();
                    tvJudul.setText(judul);
                    Glide.with(imgBuku)
                            .load(Config.IMAGE_URL + foto)
                            .into(imgBuku);
                }

            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.e("Retrofit Get : ", t.toString());
            }
        });

    }
    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                Calendar dateReturn = Calendar.getInstance();
                dateReturn.set(year, month, date);
                tvReturnDate.setText(format.format(dateReturn.getTime()));

                try {
                    Date pinjam = format.parse(tvcurrentDate.getText().toString());
                    tglPinjam = format.format(pinjam);
                    Log.e("Borrow", tglPinjam);
                    Date kembali = format.parse(tvReturnDate.getText().toString());
                    tglKembali = format.format(kembali);
                    Log.e("Kembali", tglKembali);
                    long timeDifference = Math.abs(pinjam.getTime() - kembali.getTime());
                    long differenceDates = timeDifference / (24 * 60 * 60 * 1000);
                    String dayDifference = Long.toString(differenceDates);
                    Log.e("Day Difference ", dayDifference);
                    tvDuration.setText(dayDifference + " Days");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void borrowBook(){
        Member member = SharedPrefManager.getInstance(this).getUser();
        String id_book = id_buku;
        String id_member = String.valueOf(member.getId_member());
        Borrow borrow = new Borrow(id_book,id_member,tglPinjam,tglKembali,Config.PDF_URL + url_pdf);
        Call<Borrow> borrowCall = ClientAPI.getInstance().getMyApi().borrowBook(borrow);
        borrowCall.enqueue(new Callback<Borrow>() {
            @Override
            public void onResponse(Call<Borrow> call, Response<Borrow> response) {
                if (response.isSuccessful()){
                    Log.e("Sukses", String.valueOf(response.body()));
                    Toast.makeText(getApplicationContext(),
                            "Pinjam Buku Sukses, Silahkan cek list pinjaman anda", Toast.LENGTH_LONG).show();
                    Intent doneBorrow = new Intent(BorrowActivity.this, MainActivity.class);
                    startActivity(doneBorrow);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Borrow> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}