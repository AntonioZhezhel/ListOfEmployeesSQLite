package com.example.listofemployeessqlite.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.listofemployeessqlite.R;
import com.example.listofemployeessqlite.persistence.EmployeesModel;
import com.example.listofemployeessqlite.view.MainActivityVM;

import javax.inject.Inject;

public class EmployeesLayoutActivity extends AppCompatActivity {

    Intent data = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emloyees_layout);
        addNew();

        RadioGroup radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGenderq);
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonMale:
                        data.putExtra("gender","Male");

                        break;
                    case R.id.radioButtonFemale:
                        data.putExtra("gender","Female");
                        break;

                    case R.id.radioButtonOthers:
                        data.putExtra("gender","Other");
                        break;
                }
                setResult(RESULT_OK, data);

            }
        });

    }

    private void addNew() {

        Button addEmployees=findViewById(R.id.buttonAddq);
        EditText editTextEnterName=findViewById(R.id.editTextEnterNameq);
        EditText editTextEnterAge=findViewById(R.id.editTextEnterAgeq);
        //EditText editTextEnterGender=findViewById(R.id.editTextEnterGenderq);
        EditText editTextEnterPhone=findViewById(R.id.editTextEnterPhoneq);

        addEmployees.setOnClickListener(v -> {




            if (!TextUtils.isEmpty(editTextEnterName.getText()) && !TextUtils.isEmpty(editTextEnterAge.getText())
                    && !TextUtils.isEmpty(editTextEnterPhone.getText())) {

                String name = editTextEnterName.getText().toString();
                int age = Integer.parseInt(editTextEnterAge.getText().toString());
                long phone = Long.parseLong(editTextEnterPhone.getText().toString());

                data.putExtra("name",name);
                data.putExtra("age",age);
                data.putExtra("phone",phone);

                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}