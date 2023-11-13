package userManagement;

import authManagement.LoginService;
import authManagement.RegistrationService;
import dataPersistenceManagement.*;
import inputService.InputService;
import inputService.ScannerInputService;
import roleManagement.RoleService;

import java.util.Scanner;

public class StudentManagementSystem {
    private static StudentManagementSystem instance;
    private final UserManagement userManagement;
    private final RegistrationService registrationService;
    private final LoginService loginService;
    private final RoleService roleService;
    private final ExportData exportData;
    private final InputService inputService;
    private boolean isRegistration = true;
    private boolean isExit = false;
    private boolean isLoggedIn = false;
    private boolean isUserMenuOpen = true;

    private StudentManagementSystem() {
        this.inputService = ScannerInputService.getInstance();
        FetchData fetchData = new FetchDataService();
        this.roleService = new RoleService(fetchData.getLoginToRole());
        this.userManagement = new UserManagement(roleService, fetchData.getUserList(), fetchData.getLoginToUser());
        this.loginService = new LoginService(userManagement, fetchData.getLoginToPassword());
        this.registrationService = new RegistrationService(userManagement, loginService);
        this.exportData = new ExportDataService();
    }

    public void setRegistration(boolean registration) {
        isRegistration = registration;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isUserMenuOpen() {
        return isUserMenuOpen;
    }

    public void setUserMenuOpen(boolean userMenuOpen) {
        isUserMenuOpen = userMenuOpen;
    }

    public static StudentManagementSystem getInstance() {
        if (instance == null) {
            instance = new StudentManagementSystem();
        }
        return instance;
    }

    public void startSystem() {
        System.out.println("Welcome to the Student Management System!");

        while (isRegistration && !isLoggedIn) {
            System.out.println("Enter 'login' or 'register' to enter system");
            System.out.println("Enter 'exit' to exit system");
            String command = inputService.getUserInput("Cammand: ");
            switch (command.toLowerCase()) {
                case "login" -> loginService.login();
                case "register" -> registrationService.registerUser();
                case "exit" -> exit();
                default -> System.out.println("Invalid input");
            }
        }
    }

    public void writeAllDataIntoFiles() {
        exportData.exportUserList(userManagement.getUserList());
        exportData.exportLoginToUser(userManagement.getUserLoginToUserMap());
        exportData.exportLoginToPassword(loginService.getUserLoginToPassword());
        exportData.exportLoginToRole(roleService.getUserLoginToRoleMap());
    }

    public void exit() {
        instance.setRegistration(false);
        instance.setExit(true);
        instance.setUserMenuOpen(false);
        writeAllDataIntoFiles();
        System.out.println("Goodbye!");
        inputService.close();
    }

    public <T> void printObject(T object) {
        System.out.println(object.getClass().getName() + " = " + object);
    }
}
