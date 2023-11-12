package userManagement;

import authManagement.LoginService;
import authManagement.RegistrationService;
import dataPersistenceManagement.DataPersistenceService;
import roleManagement.RoleService;

import java.util.Scanner;

public class StudentManagementSystem {
    private static StudentManagementSystem instance;
    private final UserManagement userManagement;
    private final RegistrationService registrationService;
    private final LoginService loginService;
    private final RoleService roleService;
    private DataPersistenceService dataPersistenceService;
    private final Scanner sc;
    private boolean isRegistration = true;
    private boolean isExit = false;
    private boolean isLoggedIn = false;
    private boolean isUserMenuOpen = true;

    private StudentManagementSystem() {
        this.sc = InputService.getScanner();
        this.dataPersistenceService = new DataPersistenceService();
        this.roleService = new RoleService(dataPersistenceService.readLoginToRoleFromFile());
        this.userManagement = new UserManagement(roleService, dataPersistenceService.readUserListFromFile(), dataPersistenceService.readLoginToUserFromFile());
        this.loginService = new LoginService(userManagement, dataPersistenceService.readLoginToPasswordFromFile());
        this.registrationService = new RegistrationService(userManagement, loginService);
        this.dataPersistenceService = new DataPersistenceService();
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
            String command = sc.nextLine();
            switch (command.toLowerCase()) {
                case "login" -> loginService.login();
                case "register" -> registrationService.registerUser();
                case "exit" -> exit();
                default -> System.out.println("Invalid input");
            }
        }
    }

    public void writeAllDataIntoFiles() {
        dataPersistenceService.writeUserListIntoFile(userManagement.getUserList());
        dataPersistenceService.writeLoginToUserIntoFile(userManagement.getUserLoginToUserMap());
        dataPersistenceService.writeLoginToPasswordIntoFile(loginService.getUserLoginToPassword());
        dataPersistenceService.writeLoginToRoleIntoFile(roleService.getUserLoginToRoleMap());
    }

    public void exit() {
        instance.setRegistration(false);
        instance.setExit(true);
        writeAllDataIntoFiles();
        System.out.println("Goodbye!");
        sc.close();
    }

    public <T> void printObject(T object) {
        System.out.println(object.getClass().getName() + " = " + object);
    }
}
