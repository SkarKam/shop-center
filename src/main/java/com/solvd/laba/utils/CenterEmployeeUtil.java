package com.solvd.laba.utils;

import com.solvd.laba.models.persons.employees.CenterEmployee;

import java.time.format.DateTimeFormatter;

public class CenterEmployeeUtil {
    private CenterEmployeeUtil() {}

    public static String getFullName(CenterEmployee centerEmployee){
        {
            return centerEmployee.getName() + ' ' + centerEmployee.getSurname();
        }
    }
    public static String parseDateOfEmployement(CenterEmployee centerEmployee){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return centerEmployee.getDateOfEmployment().format(formatter);
    }
    public static int getSalaries(CenterEmployee centerEmployee){
        return centerEmployee.calculateSalary();
    }
}
