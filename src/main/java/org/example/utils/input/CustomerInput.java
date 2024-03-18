package org.example.utils.input;

import org.example.models.Customer.Customer;
import org.example.models.Customer.CustomerDAO;
import org.example.utils.output.ColorConsole;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Scanner;

public class CustomerInput {
    public static Customer create(){
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();

        ColorConsole.purple("Enter name");
        customer.setFullName(scanner.nextLine());

        ColorConsole.purple("Enter email");
        customer.setContactEmail(scanner.nextLine());

        ColorConsole.purple("Enter phone");
        customer.setContactPhone(scanner.nextLine());

        ColorConsole.purple("Enter discount");
        double discount;
        try {
            discount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            ColorConsole.red("Invalid price format. Please enter a valid number.");
            return null;
        }
        customer.setDiscount(BigDecimal.valueOf(discount));

        ColorConsole.purple("Enter Birth Date (format: yyyy-MM-dd):");
        String inputDateStr = scanner.nextLine();
        Date birthDate = Date.valueOf(inputDateStr);
        customer.setBirthDate(birthDate);

        return customer;
    }

    public static Customer update(){
        Scanner scanner = new Scanner(System.in);
        ColorConsole.purple("Enter Full Name");
        String fullName = scanner.nextLine();
        Customer customer = new CustomerDAO().searchByFullName(fullName);

        if(customer!=null){
            ColorConsole.purple("Enter discount");
            double discount;
            try {
                discount = Double.parseDouble(scanner.nextLine());
                customer.setDiscount(BigDecimal.valueOf(discount));
                return customer;
            } catch (NumberFormatException e) {
                ColorConsole.red("Invalid price format. Please enter a valid number.");
            }
        }
        return customer;
    }


    public static Customer searchByFullName(){
        Scanner scanner = new Scanner(System.in);
        ColorConsole.purple("Enter Full Name");
        String fullName = scanner.nextLine();
        return new CustomerDAO().searchByFullName(fullName);
    }
}
