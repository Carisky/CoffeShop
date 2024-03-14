package org.example.utils.input;

import org.example.models.Customer.Customer;
import org.example.models.MenuItem.MenuItem;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthDate = dateFormat.parse(inputDateStr);
            customer.setBirthDate(birthDate);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter a date in the format yyyy-MM-dd.");
        }

        return customer;
    }
}
