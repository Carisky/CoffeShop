package org.example.utils.input;

import org.example.models.MenuItem.MenuItem;
import org.example.utils.output.ColorConsole;

import java.math.BigDecimal;
import java.util.Scanner;

public class MenuItemInput {
    public static MenuItem create(){
        Scanner scanner = new Scanner(System.in);
        MenuItem item = new MenuItem();

        ColorConsole.purple("Enter Type");
        item.setType(scanner.nextLine());

        ColorConsole.purple("Enter Price");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            ColorConsole.red("Invalid price format. Please enter a valid number.");
            return null;
        }

        item.setPrice(BigDecimal.valueOf(price));

        ColorConsole.purple("Enter Name");
        item.setName(scanner.nextLine());

        return item;
    }
}
