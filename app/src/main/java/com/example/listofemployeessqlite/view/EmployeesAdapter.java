package com.example.listofemployeessqlite.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listofemployeessqlite.R;
import com.example.listofemployeessqlite.databinding.EmployeesAdapterLayoutBinding;
import com.example.listofemployeessqlite.persistence.EmployeesModel;

import java.util.ArrayList;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.ViewHolder> {

    private ArrayList<EmployeesModel> employeesModels;
    private Context context;
    MainActivity.EmployeesInteractionListener employeesInteractionListener;

    public EmployeesAdapter(Context context,ArrayList<EmployeesModel> articleModels,MainActivity.EmployeesInteractionListener employeesInteractionListener){

        this.employeesModels = articleModels;
        this.context = context;
        this.employeesInteractionListener = employeesInteractionListener;
    }

    @NonNull
    @Override
    public EmployeesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.employees_adapter_layout,parent,false);
        return new EmployeesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeesAdapter.ViewHolder holder, int position) {

        final EmployeesModel employeesModel = employeesModels.get(position);
        holder.bind(employeesModel);
    }

    @Override
    public int getItemCount() {
        return employeesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private EmployeesAdapterLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind (EmployeesModel employeesModel){

            binding.setItemModel(employeesModel);
            binding.imageViewDeleteEmployees.setOnClickListener(v -> employeesInteractionListener.onEmployeesDeleted(employeesModel.getEmployeesId()));

        }
    }
}
