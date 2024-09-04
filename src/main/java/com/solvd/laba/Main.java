package com.solvd.laba;

import com.solvd.laba.models.Address;
import com.solvd.laba.models.MallRegion;
import com.solvd.laba.models.ShopCenter;
import com.solvd.laba.models.parkings.Parking;
import com.solvd.laba.models.persons.employees.CenterEmployee;
import com.solvd.laba.models.persons.employees.Janitor;
import com.solvd.laba.models.persons.employees.Manager;
import com.solvd.laba.models.persons.employees.SecurityWorker;
import com.solvd.laba.models.premises.Premise;
import com.solvd.laba.models.premises.Shop;
import com.solvd.laba.utils.CenterEmployeeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {

        Shop shop1, shop2;
        Premise premise1, premise2;
        ShopCenter shopCenter;
        Parking parking;
        Address address;
        MallRegion mallRegion1, mallRegion2;
        SecurityWorker securityWorker1, securityWorker2, securityWorker3, securityWorker4;
        Janitor janitor1, janitor2, janitor3, janitor4;
        Manager manager1, manager2;


        logger.info("Creating example data");
        shop1 = new Shop("Decatlon", LocalDate.of(2024,8,10));
        shop2 = new Shop("Carrefour", LocalDate.of(2024,8,1));

        premise1 = new Premise(shop1, new Dimension(20,40));
        premise2 = new Premise(shop2, new Dimension(30,50));


        address = new Address("Lorne St","Sackville","Nova Scotia");

        parking = new Parking(4);

        securityWorker1 = new SecurityWorker("John","Doe");
        securityWorker2 = new SecurityWorker("Oliver","Los");
        securityWorker3 = new SecurityWorker("George","Kos");
        securityWorker4 = new SecurityWorker("Noah","Tos");

        janitor1 = new Janitor("Arthur","Eos");
        janitor2 = new Janitor("Leo","Ross");
        janitor3 = new Janitor("Jack","Sos");
        janitor4 = new Janitor("Harry","Pot");

        manager1 = new Manager("Olivia","Doe");
        manager2 = new Manager("Markus","Wulfhart");

        Set<Janitor> temp = new HashSet();
        temp.add(janitor1);
        temp.add(janitor2);
        Set<Janitor> temp2 = new HashSet();
        temp2.add(janitor3);
        temp2.add(janitor4);
        Set<SecurityWorker> temp3 = new HashSet();
        temp3.add(securityWorker1);
        temp3.add(securityWorker2);
        Set<SecurityWorker> temp4 = new HashSet();
        temp4.add(securityWorker3);
        temp4.add(securityWorker4);


        mallRegion1 = new MallRegion("North", manager1, temp, temp3);
        mallRegion2 = new MallRegion("South", manager2, temp2,temp4);

        shopCenter = new ShopCenter("Sunnyville", parking, address);


        logger.info("Testing methods");
        //Showing whole hierarchy
        System.out.println("Whole hierarchy:\n"+shopCenter);

        //Testing overload methods in Premise class
        //For m2
        System.out.println();
        System.out.println("Properties/method tests:\n");
        System.out.println("Overload (m2): "+Premise.getDimensionCost(40));
        //For width, length
        System.out.println("Overload (width, length): "+Premise.getDimensionCost(20,20));

        //Testing static property and method
        System.out.println("Property: "+Premise.getMonthlyCost());

        //LocalDate
        System.out.println("LocalDate: "+premise1.getRentalDate());
        System.out.println("LocalDate: "+premise2.getRentalDate());
        System.out.println("LocalDate: "+shop1.getPaymentDate());
        System.out.println("LocalDate: "+shop2.getPaymentDate());

        //Methods test
        System.out.println("All normal workers (excluding security and manager):"+ mallRegion1.getAllWorkersSalary());
        System.out.println("Avg salary of section workers(including security, workers, manager):"+ mallRegion1.getAllWorkersSectionAvgSalary());

        janitor1.setSalary(3000);
        manager1.setSalary(5000);
        securityWorker1.setRate(35);
        securityWorker1.setHoursWorked(168);

        System.out.println(CenterEmployeeUtil.getFullName(janitor1));
        System.out.println(CenterEmployeeUtil.getFullName(securityWorker1));
        System.out.println(CenterEmployeeUtil.getFullName(manager1));

        System.out.println(CenterEmployeeUtil.parseDateOfEmployement(janitor1));
        System.out.println(CenterEmployeeUtil.parseDateOfEmployement(securityWorker1));
        System.out.println(CenterEmployeeUtil.parseDateOfEmployement(manager1));


        System.out.println(CenterEmployeeUtil.getSalaries(janitor1));
        System.out.println(CenterEmployeeUtil.getSalaries(manager1));
        System.out.println(CenterEmployeeUtil.getSalaries(securityWorker1));

        System.out.println("Welcome to shop center system.");


        Shop.save(shop1);
        Shop shop3 = Shop.load();

        System.out.println("\nIs shop saved correctly: "+shop1.equals(shop3)+"\n");

        logger.info("Starting logic part of application");
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose action: \n1) Display all employees\n2) Add new employee\n3) Exit program");
            switch(scanner.next()){
                case "1":
                    System.out.println(mallRegion1.getManager());
                    System.out.println((mallRegion1));
                    System.out.println(mallRegion1.getSecurityWorkers());
                    break;
                case "2":
                    System.out.println("Write employee name:");
                    String name  = scanner.next();
                    System.out.println("Write employee surname:");
                    String surname  = scanner.next();
                    if(name.isBlank() || surname.isBlank()){
                        System.out.println("Error: invalid name");
                        break;
                    }
                    System.out.println("Choose type of worker: \n 1) Manager \n 2) Janitor \n 3) Security \n 4) Exit");

                    switch(scanner.next()){
                        case "1":
                            try{
                                CenterEmployee centerEmployee1 = new Manager(name,surname);
                                System.out.println("Successfully added new manger named: "+centerEmployee1.getName() +" "+centerEmployee1.getSurname());
                            }catch (Exception e){
                                logger.error("Error in worker creation: "+e.getMessage());
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "2":
                            try{
                                CenterEmployee centerEmployee1 = new Janitor(name,surname);
                                System.out.println("Successfully added new manger named: "+centerEmployee1.getName() +" "+centerEmployee1.getSurname());
                            }catch (Exception e){
                                logger.error("Error in worker creation: "+e.getMessage());
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "3":
                            try{
                                CenterEmployee centerEmployee1 = new SecurityWorker(name,surname);
                                System.out.println("Successfully added new manger named: "+centerEmployee1.getName() +" "+centerEmployee1.getSurname());
                            }catch (Exception e){
                                logger.error("Error in worker creation: "+e.getMessage());
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "4":
                            break;
                        default:
                            System.out.println("You choose invalid option. Try again");
                    }
                    break;
                case "3":
                    logger.info("Exiting program");
                    System.out.println("Exiting program");
                    System.exit(0);
                default:
                    System.out.println("Invalid option choice. Try again.");
            }
        }

    }

}