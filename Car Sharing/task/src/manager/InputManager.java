package manager;

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
            if (input.equals("1")) {
                while (secondMenuExit) {
                    secondMenu();
                    String inputTwo = scanner.next();

                    switch (inputTwo) {
                        case ("1"):
                            carsharingSystem.getAllCompanies();
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
}

