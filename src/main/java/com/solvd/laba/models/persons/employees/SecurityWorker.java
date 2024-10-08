package com.solvd.laba.models.persons.employees;

import com.solvd.laba.enums.ContractType;
import com.solvd.laba.exception.NegativeValueException;
import com.solvd.laba.exception.BlankValueException;
import com.solvd.laba.exception.ValidationException;
import com.solvd.laba.interfaces.lambdas.IStringRefactor;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;

//Add fields
public class SecurityWorker extends CenterEmployee {

    private static String securityCompany;

    private int rate;
    private ContractType contractType;

    static {
        securityCompany = "SecCom";
    }

    public SecurityWorker(String name, String surname, IStringRefactor<String> iStringRefactor) {
        super(name, surname, iStringRefactor);
        this.rate = 25;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        if(rate > this.rate) {
            this.rate = rate;
        } else {
            throw new ValidationException("Rate must be greater than or equal to " + this.rate);
        }
    }

    public ContractType getContractType() {
        return contractType;
    }


    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public static String getSecurityCompany() {
        return securityCompany;
    }

    public static void setSecurityCompany(String securityCompany) {
        if(!securityCompany.isBlank()) {
            SecurityWorker.securityCompany = securityCompany;
        } else {
            throw new BlankValueException("SecurityCompany cannot be blank");
        }
    }

    public void raiseRate(BiFunction<SecurityWorker, Integer, Boolean> raise, int rate) {
        raise.apply(this, rate);
    }

    @Override
    public int calculateSalary() {
        return  rate * contractType.getHours();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SecurityWorker that = (SecurityWorker) o;
        return rate == that.rate && contractType == that.contractType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rate, contractType);
    }

    @Override
    public String toString() {
        return "SecurityWorker{" +
                "rate=" + rate +
                ", contractType=" + contractType +
                ", dateOfEmployment=" + dateOfEmployment +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}
