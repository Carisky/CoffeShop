package org.example.utils.input;

import org.example.models.Customer.Customer;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Scanner;

public class CustomerInput {
    public static Customer create(){
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();

        System.out.println("Enter name");
        customer.setFullName(scanner.nextLine());

        System.out.println("Enter email");
        customer.setContactEmail(scanner.nextLine());

        System.out.println("Enter phone");
        customer.setContactPhone(scanner.nextLine());

        System.out.println("Enter discount");
        double discount;
        try {
            discount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price format. Please enter a valid number.");
            return null;
        }
        customer.setDiscount(BigDecimal.valueOf(discount));

        System.out.println("Enter Birth Date (format: yyyy-MM-dd):");
        String inputDateStr = scanner.nextLine();
        Date birthDate = Date.valueOf(inputDateStr);
        customer.setBirthDate(birthDate);

        return customer;
    }
}
