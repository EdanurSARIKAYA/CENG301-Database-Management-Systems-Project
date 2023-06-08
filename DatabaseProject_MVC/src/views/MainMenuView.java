package views;

import java.util.HashMap;
import models.*;

public class MainMenuView implements ViewInterface {

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

        Integer choice;
        do {
            System.out.println("1. Controller Table");
            System.out.println("2. Manager Table");
            System.out.println("3. Assistant ManagerTable");
            System.out.println("4. Resident Table");
            System.out.println("5. Employee Table");
            System.out.println("6. Income Table");
            System.out.println("7. Expense Table");
            System.out.println("8. BankAccount Table");
            System.out.println("9. Dues Table");
            System.out.println("10. Exit");

            choice = getInteger("Enter your choice : ", false);
        } while (choice == null || choice < 1 || choice > 10);

        HashMap<String, Object> viewParameters = new HashMap<>();
        int selection;
        switch (choice) {

            case 1:
                System.out.println("\n1. Show all Controller");
                System.out.println("2. Update Controller");
                System.out.println("3. Quit");
                selection = getInteger("Enter your choice : ", false);
                functionName = "Controller";
                if (selection == 1) {
                    operationName = "select";
                }
                if (selection == 2) {
                    operationName = "update.gui";
                }
                if (selection == 3) {
                    return new ViewData(null, null);
                }
                break;
            case 2:
                System.out.println("\n1. Show all Manager");
                System.out.println("2. Update Manager");
                System.out.println("3. Quit");
                selection = getInteger("Enter your choice : ", false);
                functionName = "Manager";
                if (selection == 1) {
                    operationName = "select";
                }
                if (selection == 2) {
                    operationName = "update.gui";
                }
                if (selection == 3) {
                    return new ViewData(null, null);
                }
                break;
            case 3:
                System.out.println("\n1. Show all AssistantManager");
                System.out.println("2. Update AssistantManager");
                System.out.println("3. Quit");
                selection = getInteger("Enter your choice : ", false);
                functionName = "AssistantManager";
                if (selection == 1) {
                    operationName = "select";
                }
                if (selection == 2) {
                    operationName = "update.gui";
                }
                if (selection == 3) {
                    return new ViewData(null, null);
                }
                break;
            case 4:
                System.out.println("\n1. Show all Resident");
                System.out.println("2. Show Resident");
                System.out.println("3. Insert Resident");
                System.out.println("4. Update Resident");
                System.out.println("5. Delete Resident");
                System.out.println("6. Quit");
                selection = getInteger("Enter your choice : ", false);
                functionName = "Resident";
                if (selection == 1) {
                    operationName = "select";
                }
                if (selection == 2) {
                    operationName = "select.gui";
                }
                if (selection == 3) {
                    operationName = "insert.gui";
                }
                if (selection == 4) {
                    operationName = "update.gui";
                }
                if (selection == 5) {
                    operationName = "delete.gui";
                }
                if (selection == 6) {
                    return new ViewData(null, null);
                }
                break;
            case 5:
                System.out.println("\n1. Show all Employee");
                System.out.println("2. Show Employee");
                System.out.println("3. Insert Employee");
                System.out.println("4. Update Employee");
                System.out.println("5. Delete Employee");
                System.out.println("6. Quit");
                selection = getInteger("Enter your choice : ", false);
                functionName = "Employee";
                if (selection == 1) {
                    operationName = "select";
                }
                if (selection == 2) {
                    operationName = "select.gui";
                }
                if (selection == 3) {
                    operationName = "insert.gui";
                }
                if (selection == 4) {
                    operationName = "update.gui";
                }
                if (selection == 5) {
                    operationName = "delete.gui";
                }
                if (selection == 6) {
                    return new ViewData(null, null);
                }
                break;
            case 6:
                System.out.println("\n1. Show all Income");
                System.out.println("2. Show Income");
                System.out.println("3. Insert Income");
                System.out.println("4. Quit");
                selection = getInteger("Enter your choice : ", false);
                functionName = "Income";
                if (selection == 1) {
                    operationName = "select";
                }
                if (selection == 2) {
                    operationName = "select.gui";
                }
                if (selection == 3) {
                    operationName = "insert.gui";
                }
                if (selection == 4) {
                    return new ViewData(null, null);
                }
                break;
            case 7:
                System.out.println("\n1. Show all Expense");
                System.out.println("2. Show Expense");
                System.out.println("3. Insert Expense");
                System.out.println("4. Quit");
                selection = getInteger("Enter your choice : ", false);
                functionName = "Expense";
                if (selection == 1) {
                    operationName = "select";
                }
                if (selection == 2) {
                    operationName = "select.gui";
                }
                if (selection == 3) {
                    operationName = "insert.gui";
                }
                if (selection == 4) {
                    return new ViewData(null, null);
                }
                break;
            case 8:
                System.out.println("\n1. Show all BankAccount");
                System.out.println("2. Show BankAccount");
                System.out.println("3. Insert BankAccount");
                System.out.println("4. Quit");
                selection = getInteger("Enter your choice : ", false);
                functionName = "BankAccount";
                if (selection == 1) {
                    operationName = "select";
                }
                if (selection == 2) {
                    operationName = "select.gui";
                }
                if (selection == 3) {
                    operationName = "insert.gui";
                }
                if (selection == 4) {
                    return new ViewData(null, null);
                }
                break;
            case 9:
                System.out.println("\n1. Show all Dues");
                System.out.println("2. Show Dues");
                System.out.println("3. Insert Dues");
                System.out.println("4. Quit");
                selection = getInteger("Enter your choice : ", false);
                functionName = "Dues";
                if (selection == 1) {
                    operationName = "select";
                }
                if (selection == 2) {
                    operationName = "select.gui";
                }
                if (selection == 3) {
                    operationName = "insert.gui";
                }
                if (selection == 4) {
                    return new ViewData(null, null);
                }
                break;
            case 10:
                return new ViewData(null, null, null);

            default:
                return new ViewData(null, null, null);
        }

        return new ViewData(functionName, operationName, viewParameters);
    }

    @Override
    public String toString() {
        return "Enter Menu View";
    }
}
