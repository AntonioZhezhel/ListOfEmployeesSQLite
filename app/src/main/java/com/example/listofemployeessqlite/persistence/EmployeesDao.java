package com.example.listofemployeessqlite.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

//the class basically acts as an interface that will query the sqlite database as requested.
// It contains various sql queries like insert, delete

@Dao
public interface EmployeesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(EmployeesModel word);

    @Query("DELETE FROM bws_coworker")
    void deleteAll();

    @Query("DELETE FROM bws_coworker WHERE employeesId = :id")
    void delete(int id);

    @Query("SELECT * from bws_coworker ORDER BY employeesId DESC")
    LiveData<List<EmployeesModel>> getAlphabetizedWords();

}
