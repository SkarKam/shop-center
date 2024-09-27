package com.solvd.laba;

import com.solvd.laba.enums.ContractType;
import com.solvd.laba.enums.ParkingSpaceType;
import com.solvd.laba.enums.Rating;
import com.solvd.laba.enums.ShopType;
import com.solvd.laba.interfaces.lambdas.IMyConsumer;
import com.solvd.laba.interfaces.lambdas.IMyPredict;
import com.solvd.laba.interfaces.lambdas.IStringRefactor;
import com.solvd.laba.models.Address;
import com.solvd.laba.models.MallRegion;
import com.solvd.laba.models.ShopCenter;
import com.solvd.laba.models.parkings.Parking;
import com.solvd.laba.models.parkings.ParkingSpace;
import com.solvd.laba.models.persons.employees.CenterEmployee;
import com.solvd.laba.models.persons.employees.Janitor;
import com.solvd.laba.models.persons.employees.Manager;
import com.solvd.laba.models.persons.employees.SecurityWorker;
import com.solvd.laba.models.premises.Premise;
import com.solvd.laba.models.premises.Shop;
import com.solvd.laba.threads.ConnectionPool;
import com.solvd.laba.threads.MockConnection;
import com.solvd.laba.threads.MyRunnableThread;
import com.solvd.laba.threads.MyThread;
import com.solvd.laba.utils.CenterEmployeeUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.*;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    static File file = new File("target/revenue.txt");


    public static void main(String[] args) throws InterruptedException {


        Function<Integer, Integer> getCost = size -> {
            return size * Premise.getMonthlyCost();
        };

        BiFunction<SecurityWorker, Integer, Boolean> raise = (securityWorker, rate) -> {
            if(securityWorker.getContractType().getHours()>168){
                securityWorker.setRate(rate);
                return true;
            }
            return false;
        };

        Predicate<Integer> isEnough = avgRating -> avgRating >= Rating.GOOD.getMinRate();

        Consumer<MallRegion> employees = region -> {
            System.out.println("Manager: "+region.getManager().getName() +" "+region.getManager().getSurname());
            region.getJanitors().forEach(janitor -> {
                System.out.println("Janitor: " + janitor.getName() + " " + janitor.getSurname());
            });
            region.getSecurityWorkers().forEach(securityWorker -> {
                System.out.println("SecurityWorker: " + securityWorker.getName() + " " + securityWorker.getSurname());
            });
        };

        Supplier<Integer> parkingSpaceIDGeneration = () -> {
            return ParkingSpace.getParkingSpaces().size();
        };

        IStringRefactor<String> nameRefactor = (name) -> {
            StringUtils.lowerCase(name);
            StringUtils.capitalize(name);
            return name;
        };

        IMyPredict<Shop,Integer> premisePrediction = (shop, premiseCost) -> {
            return shop.getShopType().getEstimitedIncome() > premiseCost;
        };

        IMyConsumer<Collection<Janitor>,Integer> raiseSalary = (janitors, extra) -> {
          janitors.forEach(janitor -> janitor.setSalary(janitor.getSalary() + extra));
        };

        Shop shop1, shop2;
        Premise premise1, premise2;
        ShopCenter shopCenter;
        Parking parking;
        Address address;
        MallRegion mallRegion1, mallRegion2;
        SecurityWorker securityWorker1, securityWorker2, securityWorker3, securityWorker4;
        Janitor janitor1, janitor2, janitor3, janitor4;
        Manager manager1, manager2;
        ParkingSpace parkingSpace1, parkingSpace2, parkingSpace3, parkingSpace4;

        logger.info("Creating example data");
        shop1 = new Shop("Decatlon", ShopType.ClothingStore, LocalDate.of(2024,8,10));
        shop2 = new Shop("Carrefour", ShopType.Mixed, LocalDate.of(2024,8,1));

        parkingSpace1 = new ParkingSpace(parkingSpaceIDGeneration, ParkingSpaceType.ForInvalids);
        parkingSpace2 = new ParkingSpace(parkingSpaceIDGeneration,ParkingSpaceType.ForOthersCars);
        parkingSpace3 = new ParkingSpace(parkingSpaceIDGeneration,ParkingSpaceType.ForOthersCars);
        parkingSpace4 = new ParkingSpace(parkingSpaceIDGeneration,ParkingSpaceType.ForShops);

        parkingSpace1.setOccupied(true);
        parkingSpace2.setOccupied(true);
        parkingSpace3.setOccupied(true);
        parkingSpace4.setOccupied(false);

        parkingSpace1.setPaid(true);
        parkingSpace2.setPaid(false);
        parkingSpace3.setPaid(true);
        parkingSpace4.setPaid(false);

        premise1 = new Premise(shop1, new Dimension(20,40));
        premise2 = new Premise(shop2, new Dimension(30,50));


        address = new Address("Lorne St","Sackville","Nova Scotia");

        parking = new Parking(4);

        securityWorker1 = new SecurityWorker("John","Doe",nameRefactor);
        securityWorker2 = new SecurityWorker("Oliver","Los",nameRefactor);
        securityWorker3 = new SecurityWorker("George","Kos",nameRefactor);
        securityWorker4 = new SecurityWorker("Noah","Tos",nameRefactor);

        janitor1 = new Janitor("Arthur","Eos",nameRefactor);
        janitor2 = new Janitor("Leo","Ross",nameRefactor);
        janitor3 = new Janitor("Jack","Sos",nameRefactor);
        janitor4 = new Janitor("Harry","Pot",nameRefactor);

        manager1 = new Manager("Olivia","Doe",nameRefactor);
        manager2 = new Manager("Markus","Wulfhart",nameRefactor);

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

        Map<Integer,ParkingSpace> parkingSpaceMap = new HashMap<Integer,ParkingSpace>();
        parkingSpaceMap.put(1,parkingSpace1);
        parkingSpaceMap.put(2,parkingSpace2);
        parkingSpaceMap.put(3,parkingSpace3);
        parkingSpaceMap.put(4,parkingSpace4);

        parking.setParkingSpaces(parkingSpaceMap);

        shopCenter = new ShopCenter("Sunnyville", parking, address);

        List<Premise> list = new ArrayList<>();
        list.add(premise1);
        list.add(premise2);

        Set<MallRegion> temp5 = new HashSet();
        temp5.add(mallRegion1);
        shopCenter.setPremises(list);
        shopCenter.setCenterWorkersSections(temp5);
        shopCenter.setParking(parking);

        securityWorker1.setContractType(ContractType.FULLTIME);
        securityWorker2.setContractType(ContractType.FULLTIME);
        securityWorker3.setContractType(ContractType.QUARTERTIME);
        securityWorker4.setContractType(ContractType.PARTTIME);

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

        System.out.println("Avg salary of section workers(including security, workers, manager):"+ mallRegion1.getAllWorkersSectionAvgSalary());

        janitor1.setSalary(3000);
        manager1.setSalary(5000);
        securityWorker1.setRate(35);
        securityWorker1.setContractType(ContractType.FULLTIME);

        System.out.println(CenterEmployeeUtil.getFullName(janitor1));
        System.out.println(CenterEmployeeUtil.getFullName(securityWorker1));
        System.out.println(CenterEmployeeUtil.getFullName(manager1));

        System.out.println(CenterEmployeeUtil.parseDateOfEmployement(janitor1));
        System.out.println(CenterEmployeeUtil.parseDateOfEmployement(securityWorker1));
        System.out.println(CenterEmployeeUtil.parseDateOfEmployement(manager1));


        System.out.println(CenterEmployeeUtil.getSalaries(janitor1));
        System.out.println(CenterEmployeeUtil.getSalaries(manager1));
        System.out.println(CenterEmployeeUtil.getSalaries(securityWorker1));

        System.out.println(getCost.apply(20));




        System.out.println("Welcome to shop center system.");


        if(Shop.load().isEmpty()) {
            logger.info("Shop center system is empty. Temp will be saved");
            Shop.save();
        }

        logger.info("Starting logic part of application");
        shopCenter.generateRevenueInfo();
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose action: \n1) Display all employees\n2) Add new employee\n3) Read Shop Center revenue\n4) Display shops\n 5) Thread test\n6) Lambda test \n7) Exit program and activate relfection method");
            switch(scanner.next()){
                case "1":
                        mallRegion1.printAllEmployees(employees);
                    break;
                case "2":
                    System.out.println("Write name and surname of employee like this: name surname");
                    scanner.nextLine();
                    String data = scanner.nextLine();
                    String[] fullname = StringUtils.split(data," ");

                    if(fullname.length!=2){
                        System.out.println("Please enter two names: ");
                        logger.warn("Warn: invalid name");
                        break;
                    }
                    String name = StringUtils.lowerCase(fullname[0]);
                    String surname = StringUtils.lowerCase(fullname[1]);
                    if(StringUtils.isBlank(name) || StringUtils.isBlank(surname)){
                        System.out.println("Error: invalid name");
                        logger.warn("Error: invalid name");
                        break;
                    }
                    System.out.println("Choose type of worker: \n 1) Manager \n 2) Janitor \n 3) Security \n 4) Exit");
                    switch(scanner.next()){
                        case "1":
                            try{
                                CenterEmployee centerEmployee1 = new Manager(name ,surname,nameRefactor);
                                System.out.println("Successfully added new manger named: "+centerEmployee1.getName() +" "+centerEmployee1.getSurname());

                            }catch (Exception e){
                                logger.error("Error in worker creation: "+e.getMessage());
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "2":
                            try{
                                CenterEmployee centerEmployee1 = new Janitor(name,surname,nameRefactor);
                                System.out.println("Successfully added new manger named: "+centerEmployee1.getName() +" "+centerEmployee1.getSurname());
                                mallRegion1.addJanitor((Janitor) centerEmployee1);
                            }catch (Exception e){
                                logger.error("Error in worker creation: "+e.getMessage());
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "3":
                            try{
                                CenterEmployee centerEmployee1 = new SecurityWorker(name,surname,nameRefactor);
                                System.out.println("Successfully added new manger named: "+centerEmployee1.getName() +" "+centerEmployee1.getSurname());
                                mallRegion1.addSecurityWorker((SecurityWorker) centerEmployee1);
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
                    logger.info("Loading report");
                    try {
                        String revenue = FileUtils.readFileToString(file,"UTF-8");
                        System.out.println("Revenue: "+revenue);
                    }
                    catch (IOException e) {
                        System.out.println(FileUtils.getFile("target/revenue.txt"));
                        logger.error("File not found");
                        logger.info("Generating new file");
                        shopCenter.generateRevenueInfo();
                        System.out.println("Try again");
                    }
                    break;
                case "4":
                    logger.info("Displaying shops");
                    System.out.println(Shop.load());
                    break;
                case "5":
                    threadsTest();
                    break;
                case "6":
                    System.out.println("Choose lambda:\n1)Print all employees(Consumer)\n2)Raise security workers rate(BiFunction)\n" +
                            "3)Obligation achieved(Prediction)\n4)Setting shop predition(Custom Prediction)\n5)Function" +
                            "\n6)Raise janitors salary(Custom Consumer)" +
                            "\n7)Exit");
                    switch(scanner.next()){
                        case "1":
                            mallRegion1.printAllEmployees(employees);
                            break;
                        case "2":
                            System.out.println("Rate before change: "+securityWorker1.getRate());
                            securityWorker1.raiseRate(raise,30);
                            System.out.println("Rate after change: "+securityWorker1.getRate());
                            break;
                        case "3":
                            System.out.println("Manager obligation: "+manager2.isObligationFulfilled());
                            manager2.setObligationFulfilled(isEnough);
                            System.out.println("Manager obligation: "+manager2.isObligationFulfilled());
                            break;
                        case "4":
                            premise2.setShop(shop2,premisePrediction);
                            break;
                        case "5":

                            break;
                        case "6":
                            System.out.println("Janitor normal salary: "+janitor1.getSalary());
                            mallRegion1.raisedJanitorsSalary(raiseSalary,400);
                            System.out.println("Janitor raised salary: "+janitor1.getSalary());
                            break;
                        case "7":
                            break;
                        default:
                            break;
                    }
                    break;
                case "7":
                    reflectionMethod();
                    logger.info("Exiting program");
                    System.out.println("Exiting program");
                    System.exit(0);
                default:
                    System.out.println("Invalid option choice. Try again.");
            }
        }


    }

    public static void reflectionMethod(){

        try{
            Class<?> tmp = Class.forName("com.solvd.laba.models.premises.Shop");

            System.out.println("\nParams: ");

            for(Field field : tmp.getDeclaredFields()){
                System.out.println("Field name: "+field.getName()+"  type: "+field.getType());
            }

            System.out.println("\nConstructors: ");
            for(Constructor constructor : tmp.getDeclaredConstructors()){
                System.out.println("Constructor name: "+constructor.getName()+" param type: "+ Arrays.toString(constructor.getParameterTypes()) +" genericParamType: "+ Arrays.toString(constructor.getGenericParameterTypes()));
            }

            System.out.println("\nMethods: ");
            for(Method method : tmp.getDeclaredMethods()){
                System.out.println("Method name: "+method.getName()+" param type: "+ Arrays.toString(method.getParameterTypes()) +" return type: "+method.getReturnType());
            }

            System.out.println("\nCreating new object");

            Constructor<?> constructor = tmp.getConstructor(String.class,  ShopType.class ,LocalDate.class);
            Object shop = constructor.newInstance("Lidl",ShopType.ClothingStore,LocalDate.of(2024,12,1));

            Method getMethod = tmp.getDeclaredMethod("getShopName");
            System.out.println(getMethod.invoke(shop));

            Method setMethod = tmp.getDeclaredMethod("setPaymentDate", LocalDate.class);
            setMethod.invoke(shop,LocalDate.of(2025,02,12));
            getMethod = tmp.getDeclaredMethod("getPaymentDate");
            System.out.println(getMethod.invoke(shop));

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public static void threadsTest() throws InterruptedException {
        System.out.println("Threads test:");

        System.out.println("\nCustom threads test.");
        Thread thread1 = new MyThread();
        Thread thread2 = new Thread(new MyRunnableThread());

        thread1.start();
        thread2.start();


        while(thread1.isAlive() || thread2.isAlive()){
        }
        System.out.println("\nConnection pool test:\n");

        ConnectionPool connectionPool = new ConnectionPool(5);

        ExecutorService executorService = Executors.newFixedThreadPool(7);

        for(int i = 0; i<7; i++){
            executorService.execute(() -> {
                try{
                    MockConnection connection = connectionPool.getConnection();
                    connection.testMethod();
                    connectionPool.releaseConnection(connection);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        while(!executorService.isTerminated()){}
        System.out.println("\nFuture test test:\n");
        CompletableFuture<Void>  completableFuture = CompletableFuture.runAsync(() -> {
            try {
                MockConnection connection = connectionPool.getConnection();
                connection.testMethod();
                connectionPool.releaseConnection(connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).exceptionally(e -> {
            System.err.println(e.getMessage());
            return null;
        });


        while(!completableFuture.isDone()){}
        System.out.println();
        System.out.println("Threads test ended.");
    }
}