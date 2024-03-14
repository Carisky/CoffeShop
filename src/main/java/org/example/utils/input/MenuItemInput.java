package org.example.utils.input;

import org.example.models.MenuItem.MenuItem;

import java.math.BigDecimal;
import java.util.Scanner;

public class MenuItemInput {
    public static MenuItem create(){
        Scanner scanner = new Scanner(System.in);
        MenuItem item = new MenuItem();

        System.out.println("Enter Type");
        item.setType(scanner.nextLine());

        System.out.println("Enter Price");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price format. Please enter a valid number.");
            return null; // Return null to indicate an error
        }

        item.setPrice(BigDecimal.valueOf(price));

        System.out.println("Enter Name");
        item.setName(scanner.nextLine());

        return item;
    }
}
