package com.solvd.laba.models;

import com.solvd.laba.exception.BlankValueException;

import com.solvd.laba.interfaces.ICost;
import com.solvd.laba.interfaces.lambdas.IMyConsumer;
import com.solvd.laba.models.persons.clients.ShopOwner;
import com.solvd.laba.models.persons.employees.Janitor;
import com.solvd.laba.models.persons.employees.Manager;
import com.solvd.laba.models.persons.employees.SecurityWorker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;


//rename
public class MallRegion implements ICost {

    private String sectionName;
    private Manager manager;
    private Set<Janitor> janitors;
    private Set<SecurityWorker> securityWorkers;
    private Set<ShopOwner> shopOwners;

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
            throw new NullPointerException("Manager cannot be null.");
        }
    }

    public Set<Janitor> getJanitors() {
        return janitors;
    }

    public void setJanitors(Set<Janitor> janitors) {
        this.janitors = janitors;
    }

    public void addJanitor(Janitor janitor){
        if(janitor !=null) {
            this.janitors.add(janitor);
        } else {
            throw new NullPointerException("Worker cannot be null.");
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
        } else {throw new NullPointerException("Security worker cannot be null.");}
    }

    public int getAllWorkersSectionAvgSalary(){
        int result = getJanitors()
                        .stream()
                        .mapToInt(Janitor::getSalary)
                        .sum();

        result += getSecurityWorkers()
                .stream()
                .mapToInt(SecurityWorker::calculateSalary)
                .sum();

        result += getManager().calculateSalary();


        return result/(janitors.size()+securityWorkers.size()+1);
    }
    public Set<ShopOwner> getShopOwners() {
        return shopOwners;
    }

    public void setShopOwners(Set<ShopOwner> shopOwners) {
        this.shopOwners = shopOwners;
    }


    public void printAllEmployees(Consumer<MallRegion> consumer){
        consumer.accept(this);
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

    @Override
    public int calculateCost() {
        int result = getJanitors()
                .stream()
                .mapToInt(Janitor::getSalary)
                .sum();

        result += getSecurityWorkers()
                .stream()
                .mapToInt(SecurityWorker::calculateSalary)
                .sum();

        result += getManager().calculateSalary();
        return result;
    }

    public void raisedJanitorsSalary(IMyConsumer<Collection<Janitor>,Integer> raiseSalary, int bonus){
        raiseSalary.accept(this.getJanitors(),bonus);
    }
}
