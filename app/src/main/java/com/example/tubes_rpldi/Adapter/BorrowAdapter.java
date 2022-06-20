package com.example.tubes_rpldi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes_rpldi.R;
import com.example.tubes_rpldi.model.Borrow;

import java.util.List;

public class BorrowAdapter extends RecyclerView.Adapter<BorrowAdapter.viewHolder> {
    Context context;
    List<Borrow> borrowList;

    public BorrowAdapter(List<Borrow> borrowList, Context context) {
        this.context = context;
        this.borrowList = borrowList;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View borrowView = LayoutInflater.from(context).inflate(R.layout.item_borrow, parent, false);
        return new viewHolder(borrowView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        if (!borrowList.get(position).getId_member().isEmpty()){
            holder.tvIDMember.setText(borrowList.get(position).getId_member());
            holder.tvIDMember.setVisibility(View.GONE);
        }
        String pdf = borrowList.get(position).getBook_path();
        holder.tvBookPath.setText(pdf);
        holder.tvBookTitle.setText(borrowList.get(position).getName());
        holder.tvReturnDate.setText("Tanggal Kembali : " + borrowList.get(position).getDate_return());
        Log.e("Return Date : ", "Data is : " +borrowList.get(position).getDate_return());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent readbook = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf));
                context.startActivity(readbook);
            }
        });
    }

    @Override
    public int getItemCount() {
        return borrowList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public TextView tvIDMember, tvBookTitle, tvReturnDate, tvBookPath;
        public viewHolder(View itemView) {
            super(itemView);
            tvIDMember = itemView.findViewById(R.id.tv_idMember);
            tvBookTitle = itemView.findViewById(R.id.tv_name);
            tvReturnDate = itemView.findViewById(R.id.tv_returnDate);
            tvBookPath = itemView.findViewById(R.id.tvBookPath);
        }
    }
}
