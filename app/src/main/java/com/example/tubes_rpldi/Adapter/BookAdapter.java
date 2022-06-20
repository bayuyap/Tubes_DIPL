package com.example.tubes_rpldi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tubes_rpldi.Activity.SynopsisActivity;
import com.example.tubes_rpldi.R;
import com.example.tubes_rpldi.connection.Config;
import com.example.tubes_rpldi.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    Context context;
    List<Book> mbookList;

    public BookAdapter(List<Book> bookList, Context context) {
        mbookList = bookList;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new MyViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (!mbookList.get(position).getId_book().isEmpty()){
            holder.tvIdBuku.setText(mbookList.get(position).getId_book());
            holder.tvIdBuku.setVisibility(View.GONE);
        }
        String pdf = mbookList.get(position).getPdf();
        holder.tvJudulBuku.setText(mbookList.get(position).getName());
        Glide.with(holder.itemView.getContext())
                .load(Config.IMAGE_URL + mbookList.get(position).getFoto())
                .apply(new RequestOptions().override(100, 125))
                .into(holder.ivCoverBuku);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_book = holder.tvIdBuku.getText().toString();
                Intent intent = new Intent(context, SynopsisActivity.class);
                intent.putExtra("id_book", id_book);
                intent.putExtra("pdf", pdf);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mbookList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvJudulBuku, tvIdBuku;
        public ImageView ivCoverBuku;
        public MyViewHolder( View itemView) {
            super(itemView);
            tvIdBuku = itemView.findViewById(R.id.tvIDBook);
            tvJudulBuku = itemView.findViewById(R.id.tvJudulBuku);
            ivCoverBuku = itemView.findViewById(R.id.ivCoverBuku);
        }
    }
}
