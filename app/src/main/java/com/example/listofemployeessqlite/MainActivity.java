package com.example.listofemployeessqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.listofemployeessqlite.data.Employees;
import com.example.listofemployeessqlite.data.SqliteDatabase;
import com.example.listofemployeessqlite.ui.EmployeesAdapter;
import com.example.listofemployeessqlite.ui.EmployeesViewHolder;
import com.example.listofemployeessqlite.ui.RecyclerItemTouchHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {


    private SqliteDatabase mDatabase;
    private ArrayList<Employees> allEmployees =new ArrayList<>();
    private EmployeesAdapter mAdapter;
    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = findViewById(R.id.activityMain);

        RecyclerView employeesView = (RecyclerView)findViewById(R.id.recyclerViewEmployees);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        employeesView.setLayoutManager(linearLayoutManager);
        employeesView.setHasFixedSize(true);
        mDatabase = new SqliteDatabase(this);
        allEmployees = mDatabase.listEmployees();


        if(allEmployees.size() > 0){
            employeesView.setVisibility(View.VISIBLE);
            mAdapter = new EmployeesAdapter(this, allEmployees);
            employeesView.setAdapter(mAdapter);

        }else {
            employeesView.setVisibility(View.GONE);
            Toast.makeText(this, "There is no employees in the database. Start adding now", Toast.LENGTH_LONG).show();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.buttonAddEmployees);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTaskDialog();
            }
        });



        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(employeesView);
    }


    private void addTaskDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_employees_layout, null);

        final EditText nameField = (EditText)subView.findViewById(R.id.editTextEnterName);
        final EditText ageField = (EditText)subView.findViewById(R.id.editTextEnterAge);
        final EditText genderField = (EditText)subView.findViewById(R.id.editTextEnterGender);
        final EditText phoneField = (EditText)subView.findViewById(R.id.editTextEnterPhone);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add new Employees");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("ADD Employees", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = nameField.getText().toString();
                final Integer age = Integer.valueOf(ageField.getText().toString());
                final String gender = genderField.getText().toString();
                final String phone = phoneField.getText().toString();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(MainActivity.this, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else{
                    Employees newEmployees = new Employees(name,age,gender,phone);
                    mDatabase.addEmployees(newEmployees);

                    finish();
                    startActivity(getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDatabase != null){
            mDatabase.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) search.getActionView();
        search(searchView);
        return true;
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (mAdapter!=null)
                    mAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        if (viewHolder instanceof EmployeesViewHolder) {

            // remove the item from recycler view
            mAdapter.removeItem(viewHolder.getBindingAdapterPosition());

        }
    }


}