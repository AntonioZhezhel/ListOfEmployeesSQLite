package com.example.listofemployeessqlite;

import androidx.lifecycle.ViewModel;

import com.example.listofemployeessqlite.persistence.EmployeesRepository;

public abstract class BaseViewModel extends ViewModel {

    private final EmployeesRepository mEmployeesRepository;

    protected BaseViewModel(EmployeesRepository mEmployeesRepository) {
        this.mEmployeesRepository = mEmployeesRepository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public EmployeesRepository getEmployeesRepository(){
        return mEmployeesRepository;
    }
}

