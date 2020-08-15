package com.example.listofemployeessqlite.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listofemployeessqlite.R;


public class EmployeesViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewName,textViewAge,textViewPhone;
    public ImageView imageViewEditEmployees;
    public LinearLayout linerLayoutForeground;
    public RelativeLayout relativeLayoutBackgraund;

    public EmployeesViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName= itemView.findViewById(R.id.textViewName);
        textViewAge= itemView.findViewById(R.id.textViewAge);
        textViewPhone= itemView.findViewById(R.id.textViewPhone);
        imageViewEditEmployees= itemView.findViewById(R.id.imageViewEditEmployees);
        linerLayoutForeground = itemView.findViewById(R.id.linerLayoutForeground);
        relativeLayoutBackgraund = itemView.findViewById(R.id.relativeLayoutBackground);





    }
}
