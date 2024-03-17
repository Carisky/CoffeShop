package org.example.Menu;

import org.example.models.Customer.CustomerDAO;
import org.example.utils.input.CustomerInput;
import org.example.utils.output.ColorConsole;

import java.util.Objects;
import java.util.Scanner;


public class CustomerCLI {
    public static void RUN() {
            Scanner scanner = new Scanner(System.in);
            CustomerDAO DAO = new CustomerDAO();

            while (true) {
                ColorConsole.cyan("Enter your command: ");
                String line = scanner.nextLine();

                if ("exit".equalsIgnoreCase(line.trim())) {
                    break;
                }
                if ("create".equalsIgnoreCase(line.trim())) {
                    DAO.create(Objects.requireNonNull(CustomerInput.create()));

                }
        }
    }
}
