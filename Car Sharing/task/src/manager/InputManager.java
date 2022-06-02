package manager;

import entity.Car;
import system.CarsharingSystem;
import entity.Company;
import java.util.Scanner;

public class InputManager {

    private final Scanner scanner;

    public InputManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startInput(final CarsharingSystem carsharingSystem) {
        boolean isExit = true;
        while (isExit) {
            mainMenu();
            String input = scanner.next();
            boolean secondMenuExit = true;
            boolean thirdMenuExit;
            if (input.equals("1")) {
                while (secondMenuExit) {
                    secondMenu();
                    String inputTwo = scanner.next();
                    switch (inputTwo) {
                        case ("1"):
                            if (carsharingSystem.getAllCompanies()) {
                                System.out.println("0. Back");
                                System.out.println();
                                int chooseCompany = scanner.nextInt();
                                Company choosenCompany = carsharingSystem.getCompany(chooseCompany);
                                if (choosenCompany != null && chooseCompany != 0) {
                                    System.out.println("'" + choosenCompany.getName() + "'" + " company");
                                    thirdMenuExit = true;
                                    while (thirdMenuExit) {
                                        carMenu();
                                        String inputThree = scanner.next();
                                        switch (inputThree) {
                                            case ("1"):
                                                carsharingSystem.getAllCars(chooseCompany);
                                                System.out.println();
                                                break;
                                            case ("2"):
                                                System.out.println("Enter the car name:");
                                                scanner.nextLine();
                                                String carName = scanner.nextLine();
                                                Car newCar = new Car(carsharingSystem.getCarId(), carName);
                                                carsharingSystem.addCarToDB(newCar, chooseCompany);
                                                System.out.println("The car was added!");
                                                break;
                                            case ("0"):
                                                thirdMenuExit = false;
                                                break;
                                        }
                                    }
                                }
                            }
                            break;
                        case ("2"):
                            System.out.println("Enter the company name:");
                            scanner.nextLine();
                            String name = scanner.nextLine();
                            Company company = new Company(carsharingSystem.getCompanyID(), name);
                            carsharingSystem.addCompanyToDB(company);
                            System.out.println("The company was created!");
                            System.out.println();
                            break;
                        case ("0"):
                            secondMenuExit = false;
                            break;
                    }
                }
            }
            if (input.equals("0")) {
                isExit = false;
                break;
            }
        }
    }

    private void secondMenu() {
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
    }

    private void mainMenu() {
        System.out.println("1. Log in as a manager");
        System.out.println("0. Exit");
    }

    private void carMenu() {
        System.out.println("1. Car list\n" +
                "2. Create a car\n" +
                "0. Back");
    }
}

