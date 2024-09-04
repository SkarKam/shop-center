package com.solvd.laba.kaminski.utils;

import com.solvd.laba.kaminski.models.persons.employees.CenterEmployee;

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
