package com.example.listofemployeessqlite.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.listofemployeessqlite.R;
import com.example.listofemployeessqlite.persistence.EmployeesModel;
import com.example.listofemployeessqlite.view.MainActivityVM;

import javax.inject.Inject;

public class EmployeesLayoutActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emloyees_layout);
        addNew();
    }

    private void addNew() {

        Button addEmployees=findViewById(R.id.buttonAddq);
        EditText editTextEnterName=findViewById(R.id.editTextEnterNameq);
        EditText editTextEnterAge=findViewById(R.id.editTextEnterAgeq);
        EditText editTextEnterGender=findViewById(R.id.editTextEnterGenderq);
        EditText editTextEnterPhone=findViewById(R.id.editTextEnterPhoneq);

        addEmployees.setOnClickListener(v -> {

            if (!TextUtils.isEmpty(editTextEnterName.getText()) && !TextUtils.isEmpty(editTextEnterAge.getText())
                    && !TextUtils.isEmpty(editTextEnterGender.getText()) && !TextUtils.isEmpty(editTextEnterPhone.getText())) {

                String name = editTextEnterName.getText().toString();
                int age = Integer.parseInt(editTextEnterAge.getText().toString());
                String gender = editTextEnterGender.getText().toString();
                long phone = Long.parseLong(editTextEnterPhone.getText().toString());

                Intent data = new Intent();
                data.putExtra("name",name);
                data.putExtra("age",age);
                data.putExtra("gender",gender);
                data.putExtra("phone",phone);

                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}