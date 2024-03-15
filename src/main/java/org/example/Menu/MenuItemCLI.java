package org.example.Menu;

import org.example.models.MenuItem.MenuItemDAO;
import org.example.utils.input.MenuItemInput;

import java.util.Scanner;

public class MenuItemCLI {
    public static void RUN() {

            Scanner scanner = new Scanner(System.in);
            MenuItemDAO DAO = new MenuItemDAO();

            while (true) {
                System.out.println("Enter your command: ");
                String line = scanner.nextLine();

                if ("exit".equalsIgnoreCase(line.trim())) {
                    break;
                }
                if("create".equalsIgnoreCase(line.trim())) {
                    DAO.create(MenuItemInput.create());
                }
            }
    }
}
