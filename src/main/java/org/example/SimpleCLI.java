package org.example;

import org.example.Menu.CustomerCLI;
import org.example.Menu.MenuItemCLI;
import org.example.Menu.StaffMemberCLI;
import org.jline.reader.*;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class SimpleCLI {
    public static void main(String[] args) {
        try {
            Terminal terminal = TerminalBuilder.builder().build();
            LineReader lineReader = LineReaderBuilder.builder().terminal(terminal).build();

            while (true) {
                String line = lineReader.readLine("Enter your command: ");

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

            terminal.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
