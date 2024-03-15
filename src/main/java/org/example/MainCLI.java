package org.example;

import org.example.Menu.CustomerCLI;
import org.example.Menu.MenuItemCLI;
import org.example.Menu.StaffMemberCLI;


import java.util.Scanner;

public class MainCLI {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Enter your command: ");
                String line = scanner.nextLine();

                if ("exit".equalsIgnoreCase(line.trim())) {
                    break;
                }
                if ("menu item".equalsIgnoreCase(line.trim())) {
                    MenuItemCLI.RUN();
                }
                if ("staff member".equalsIgnoreCase(line.trim())) {
                    StaffMemberCLI.RUN();
                }
                if ("customer".equalsIgnoreCase(line.trim())) {
                    CustomerCLI.RUN();
                }
            }
    }
}
