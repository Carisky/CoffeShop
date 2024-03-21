package org.example.utils.input;

import org.example.models.MenuItem.MenuItem;
import org.example.DAO.MenuItemDAO;
import org.example.utils.output.ColorConsole;

import java.math.BigDecimal;
import java.util.List;
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

    public static List<MenuItem> updatePrice(){
        Scanner scanner = new Scanner(System.in);

        ColorConsole.purple("Enter Type");
        String type = scanner.nextLine();
        List<MenuItem> items = new MenuItemDAO().searchByType(type);

        if(items!=null){
            ColorConsole.purple("Enter Price For "+type);
            double price;
            try {
                price = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                ColorConsole.red("Invalid price format. Please enter a valid number.");
                return null;
            }

            for (MenuItem menuItem : items) {
                menuItem.setPrice(BigDecimal.valueOf(price));
            }
            return items;
        }
        return null;
    }

    public static MenuItem searchByName(){
        Scanner scanner = new Scanner(System.in);

        ColorConsole.purple("Enter Name");
        String name = scanner.nextLine();

        return new MenuItemDAO().searchByName(name);
    }
}
