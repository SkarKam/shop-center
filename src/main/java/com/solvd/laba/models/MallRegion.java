package com.solvd.laba.models;

import com.solvd.laba.exception.BlankValueException;
import com.solvd.laba.exception.NullValueException;
import com.solvd.laba.models.persons.employees.Janitor;
import com.solvd.laba.models.persons.employees.Manager;
import com.solvd.laba.models.persons.employees.SecurityWorker;

import java.util.Objects;
import java.util.Set;

//rename
public class MallRegion {
    private String sectionName;
    private Manager manager;
    private Set<Janitor> janitors;
    private Set<SecurityWorker> securityWorkers;

    public MallRegion(String sectionName, Manager manager, Set<Janitor> janitors,Set<SecurityWorker> securityWorkers) {
        this.sectionName = sectionName;
        this.manager = manager;
        this.janitors = janitors;
        this.securityWorkers = securityWorkers;
    }

    public MallRegion(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        if(!sectionName.isBlank()) {
            this.sectionName = sectionName;
        } else {
            throw new BlankValueException("Section name cannot be blank.");
        }
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        if(manager!=null) {
            this.manager = manager;
        } else {
            throw new NullValueException("Manager cannot be null.");
        }
    }

    public Set<Janitor> getWorkers() {
        return janitors;
    }

    public void setWorkers(Set<Janitor> janitors) {
        this.janitors = janitors;
    }

    public void addWorkers(Janitor janitor){
        if(janitor !=null) {
            this.janitors.add(janitor);
        } else {
            throw new NullValueException("Worker cannot be null.");
        }
    }

    public Set<SecurityWorker> getSecurityWorkers() {
        return securityWorkers;
    }

    public void setSecurityWorkers(Set<SecurityWorker> securityWorkers) {
        this.securityWorkers = securityWorkers;
    }
    public void addSecurityWorker(SecurityWorker securityWorkers) {
        if(securityWorkers!=null) {
            this.securityWorkers.add(securityWorkers);
        } else {throw new NullValueException("Security worker cannot be null.");}
    }

    public int getAllWorkersSalary(){
        int result = 0;
        for(Janitor janitor : janitors){
            result+= janitor.getSalary();
        }
        return result;
    }
    public int getAllWorkersSectionAvgSalary(){
        int result = 0;
        int counter = 0;
        for(Janitor janitor : janitors){
            result += janitor.getSalary();
            counter++;
        }
        for(SecurityWorker securityWorker : securityWorkers){
            result += securityWorker.getHoursWorked();
            counter++;
        }
        result+=manager.getSalary();
        counter++;
        return result/counter;
    }

    @Override
    public String toString() {
        return "\nCenterWorkersSection{" +
                "sectionName='" + sectionName + '\'' +
                "manager=" + manager +
                "workers=" + janitors +
                "securityWorkers=" + securityWorkers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MallRegion that = (MallRegion) o;
        return Objects.equals(sectionName, that.sectionName) && Objects.equals(manager, that.manager) && Objects.equals(janitors, that.janitors) && Objects.equals(securityWorkers, that.securityWorkers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionName, manager, janitors, securityWorkers);
    }
}
