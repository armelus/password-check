package fr.demo.test;

import fr.demo.test.service.AuthenticationChecker;
import fr.demo.test.service.AuthenticationCheckerImpl;
import fr.demo.test.utils.AuthResponse;
import fr.demo.test.utils.AuthStatus;

import java.util.Scanner;

public class Application {

    public static void main(String... args) {
        AuthenticationChecker authenticationChecker = new AuthenticationCheckerImpl();
        Scanner scanner = new Scanner(System.in);

        AuthStatus status;
        do {
            // Ask user login
            System.out.println("Enter your login");
            // read user login
            String login = scanner.nextLine();

            // Ask user password
            System.out.println("Enter your password");
            // read user password
            String password = scanner.nextLine();

            // Checking password
            AuthResponse response = authenticationChecker.checkAuth(login, password);
            status = response.status();

            // Printing message
            System.out.println("\n" + response.message() + "\n");
        } while (status == AuthStatus.FAILED);

    }
}
