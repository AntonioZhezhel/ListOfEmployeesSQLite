package com.example.listofemployeessqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.listofemployeessqlite.data.Employees;

import java.util.ArrayList;

//created a helper class that extends SQLiteOpenHelper

public class SqliteDatabase extends SQLiteOpenHelper {

    private	static final int DATABASE_VERSION =	1;
    private	static final String	DATABASE_NAME = "bws_coworker";
    private	static final String TABLE_EMPLOYEES= "bws_coworkers";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "employeesname";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_PHONE = "phone";

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String	CREATE_EMPLOYEES_TABLE = "CREATE	TABLE " + TABLE_EMPLOYEES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT,"+ COLUMN_AGE + " INTEGER,"+ COLUMN_GENDER + " TEXT," + COLUMN_PHONE + " TEXT" + ")";
        db.execSQL(CREATE_EMPLOYEES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        onCreate(db);
    }

    public ArrayList<Employees> listEmployees(){
        String sql = "select * from " + TABLE_EMPLOYEES;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Employees> storeEmployees = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                Integer age = cursor.getInt(2);
                String gender = cursor.getString(3);
                String phone = cursor.getString(4);

                storeEmployees.add(new Employees(id, name,age,gender,phone));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeEmployees;
    }
    //methods for performing CRUD operations, add, update, delete

    public void addEmployees(Employees employees){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, employees.getName());
        values.put(COLUMN_AGE, employees.getAge());
        values.put(COLUMN_GENDER, employees.getGender());
        values.put(COLUMN_PHONE, employees.getPhone());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_EMPLOYEES, null, values);
    }

    public void updateEmployees(Employees employees){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, employees.getName());
        values.put(COLUMN_AGE, employees.getAge());
        values.put(COLUMN_GENDER, employees.getGender());
        values.put(COLUMN_PHONE, employees.getPhone());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_EMPLOYEES, values, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(employees.getId())});
    }

    public void deleteEmployees(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYEES, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(id)});
    }
}
