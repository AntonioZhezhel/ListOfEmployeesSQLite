package com.example.listofemployeessqlite.view;

import com.example.listofemployeessqlite.BaseViewModel;
import com.example.listofemployeessqlite.persistence.EmployeesRepository;

import javax.inject.Inject;

//view model class for MainActivity

public class MainActivityVM extends BaseViewModel {

    final EmployeesRepository mEmployeesRepository;

    @Inject
    public MainActivityVM(EmployeesRepository mEmployeesRepository) {
        super(mEmployeesRepository);
        this.mEmployeesRepository=mEmployeesRepository;
    }
}
