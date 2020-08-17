package com.example.listofemployeessqlite.persistence;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//create model class for Employees
//@Entity annotates the class and identifies it as a SQLite table.

@Entity(tableName = "bws_coworker")
public class EmployeesModel {
    @PrimaryKey(autoGenerate = true)
    private int employeesId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "age")
    private int age;
    @ColumnInfo(name = "gender")
    private String gender;
    @ColumnInfo(name = "phone")
    private long phone;

    public EmployeesModel(String name, int age,String gender,long phone){

        this.name = name;
        this.age = age;
        this.gender =gender;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }
    public void setPhone(long phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(int employeesId) {
        this.employeesId = employeesId;
    }
}
