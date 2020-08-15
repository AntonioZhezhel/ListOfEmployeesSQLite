package com.example.listofemployeessqlite.data;

//model class for object Employees

public class Employees {
    private	int	id;
    private	String name;
    private	String phone;
    private	Integer age;
    private	String gender;


    public Employees(String name, Integer age, String gender, String phone) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
    }

    public Employees(int id, String name, Integer age, String gender, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
