import manager.InputManager;
import system.CarsharingSystem;

import java.util.Scanner;

public class Main {
    InputManager inputManager;
    CarsharingSystem carsharingSystem;


    public Main(String fileName) {
        Scanner scanner = new Scanner(System.in);
        inputManager = new InputManager(scanner);
        carsharingSystem = new CarsharingSystem(fileName);
    }

    public static void main(String[] args) {
        String dbname = "carsharing";
        if (args.length > 1 && 0 == args[1].compareTo("-databaseFileName")) {
            dbname = args[2];
        }
        Main application = new Main(dbname);
        application.start();
    }

    public void start() {
        inputManager.startInput(carsharingSystem);
    }
}
