package com.example.listofemployeessqlite.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.listofemployeessqlite.BR;
import com.example.listofemployeessqlite.BaseActivity;
import com.example.listofemployeessqlite.R;
import com.example.listofemployeessqlite.databinding.ActivityMainBinding;
import com.example.listofemployeessqlite.persistence.EmployeesModel;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;


public class MainActivity extends BaseActivity<ActivityMainBinding,MainActivityVM> {

    private ActivityMainBinding binding;
    private EmployeesAdapter employeesAdapter;
    private AlertDialog alertDialog;

    public static final int ADD_NOTE_REQUEST = 1;

    @Inject
    MainActivityVM mainActivityVM;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    //returned the viewmodel (MainActivityVM) and layout file (activity_main)
    // in BaseActivity so that we can do data binding and customization of the viewmodel inside BaseActivity.
    // This process will apply to all future activities that may be created in the application.

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainActivityVM getViewModel() {
        return mainActivityVM;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=getmViewDataBinding();
        binding.recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));

        mainActivityVM.mEmployeesRepository.getAllEmployees().observe(this,articleModels -> {
            if (articleModels!=null) {
                employeesAdapter=new EmployeesAdapter(this, new ArrayList<>(articleModels), note_id -> mainActivityVM.mEmployeesRepository.deleteNote(note_id));
                binding.recyclerViewEmployees.setAdapter(employeesAdapter);
            }
        });

        binding.buttonAddEmployees.setOnClickListener(v -> addNewEmployees());
        binding.buttonOpenEmployeesLayout.setOnClickListener(v -> {
            Intent intent = new Intent(this, EmployeesLayoutActivity.class);
            startActivityForResult(intent,ADD_NOTE_REQUEST);
        });

    }

    public interface EmployeesInteractionListener {
        public void onEmployeesDeleted(int note_id);
    }

    private void addNewEmployees() {
        androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_employees_layout, null);
        dialogBuilder.setView(dialogView);

        Button ok_button=dialogView.findViewById(R.id.buttonAdd);
        EditText editTextEnterName=dialogView.findViewById(R.id.editTextEnterName);
        EditText editTextEnterAge=dialogView.findViewById(R.id.editTextEnterAge);
        EditText editTextEnterGender=dialogView.findViewById(R.id.editTextEnterGender);
        EditText editTextEnterPhone=dialogView.findViewById(R.id.editTextEnterPhone);

        alertDialog = dialogBuilder.create();
        if (alertDialog.getWindow()!=null) {
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            alertDialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        }

        ok_button.setOnClickListener(v -> {

            if (!TextUtils.isEmpty(editTextEnterName.getText()) && !TextUtils.isEmpty(editTextEnterAge.getText())
                    && !TextUtils.isEmpty(editTextEnterGender.getText()) && !TextUtils.isEmpty(editTextEnterPhone.getText())) {

                EmployeesModel employeesModel=new EmployeesModel(editTextEnterName.getText().toString(),Integer.parseInt(editTextEnterAge.getText().toString()),
                        editTextEnterGender.getText().toString(),Long.parseLong((editTextEnterPhone.getText().toString())));
                mainActivityVM.mEmployeesRepository.insert(employeesModel);
                alertDialog.dismiss();
            }else {

                if (TextUtils.isEmpty(editTextEnterName.getText()))
                    editTextEnterName.setError("Field can't be empty");
                if (TextUtils.isEmpty(editTextEnterAge.getText()))
                    editTextEnterAge.setError("Field can't be empty");
                if (TextUtils.isEmpty(editTextEnterGender.getText()))
                    editTextEnterGender.setError("Field can't be empty");
                if (TextUtils.isEmpty(editTextEnterPhone.getText()))
                    editTextEnterPhone.setError("Field can't be empty");
            }
        });

        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            assert data != null;
            String name = data.getStringExtra("name");
            int age = data.getIntExtra("age",  0);
            String gender = data.getStringExtra("gender");
            long phone = data.getLongExtra("phone",0);

            EmployeesModel employeesModel=new EmployeesModel(name,age,gender,phone);
            mainActivityVM.mEmployeesRepository.insert(employeesModel);

        }

    }
}