package com.example.listofemployeessqlite.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listofemployeessqlite.R;
import com.example.listofemployeessqlite.data.Employees;
import com.example.listofemployeessqlite.data.SqliteDatabase;

import java.util.ArrayList;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesViewHolder>implements Filterable {


    private Context context;
    private ArrayList<Employees> listEmployees;
    private ArrayList<Employees> mArrayList;

    private SqliteDatabase mDatabase;

    public EmployeesAdapter(Context context, ArrayList<Employees> listEmployees) {
        this.context = context;
        this.listEmployees= listEmployees;
        this.mArrayList=listEmployees;
        mDatabase = new SqliteDatabase(context);
    }

    //a filtering method used to perform search operations on the list of employees.
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    listEmployees = mArrayList;
                } else {

                    ArrayList<Employees> filteredList = new ArrayList<>();

                    for (Employees contacts : mArrayList) {

                        if (contacts.getName().toLowerCase().contains(charString)) {

                            filteredList.add(contacts);
                        }
                    }

                    listEmployees = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listEmployees;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listEmployees = (ArrayList<Employees>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public EmployeesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employees_adapter_layout, parent, false);
        return new EmployeesViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull EmployeesViewHolder holder, int position) {

        final Employees employees = listEmployees.get(position);

        holder.textViewName.setText(employees.getName()+"    ("+employees.getGender()+")");
        holder.textViewPhone.setText(employees.getPhone());
        holder.textViewAge.setText(employees.getAge()+"   years old");

        holder.imageViewEditEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(employees);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listEmployees.size();
    }

    //delete row from database
    public void removeItem(int position){
        final Employees employees = listEmployees.get(position);
        mDatabase.deleteEmployees(employees.getId());
        listEmployees.remove(position);
        notifyItemRemoved(position);
    }


    private void editTaskDialog(final Employees employees){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_employees_layout, null);

        final EditText nameField = (EditText)subView.findViewById(R.id.editTextEnterName);
        final EditText ageField = (EditText)subView.findViewById(R.id.editTextEnterAge);
        final EditText genderField = (EditText)subView.findViewById(R.id.editTextEnterGender);
        final EditText phoneField = (EditText)subView.findViewById(R.id.editTextEnterPhone);

        if(employees != null){
            nameField.setText(employees.getName());
            ageField.setText(Integer.toString(employees.getAge()));
            genderField.setText(employees.getGender());
            phoneField.setText(employees.getPhone());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit employees");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("EDIT EMPLOYEES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = nameField.getText().toString();
                final Integer age = Integer.valueOf(ageField.getText().toString());
                final String gender = genderField.getText().toString();
                final String phone = phoneField.getText().toString();



                if(TextUtils.isEmpty(name)){
                    Toast.makeText(context, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else{
                    mDatabase.updateEmployees(new Employees(employees.getId(), name, age,gender,phone));
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}
