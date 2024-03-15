package org.example.Menu;

import org.example.models.Customer.CustomerDAO;
import org.example.utils.input.CustomerInput;

import java.util.Objects;
import java.util.Scanner;

public class CustomerCLI {
    public static void RUN() {
            Scanner scanner = new Scanner(System.in);
            CustomerDAO DAO = new CustomerDAO();

            while (true) {
                System.out.println("Enter your command: ");
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
