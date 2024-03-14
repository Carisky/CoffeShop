package org.example.Menu;

import org.example.models.MenuItem.MenuItemDAO;
import org.example.utils.input.MenuItemInput;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class MenuItemCLI {
    public static void RUN() {
        try {
            Terminal terminal = TerminalBuilder.builder().build();
            LineReader lineReader = LineReaderBuilder.builder().terminal(terminal).build();
            MenuItemDAO DAO = new MenuItemDAO();

            while (true) {
                String line = lineReader.readLine("Enter your command for MenuItemCLI: ");

                if ("exit".equalsIgnoreCase(line.trim())) {
                    break;
                }
                if("create".equalsIgnoreCase(line.trim())) {
                    DAO.create(MenuItemInput.create());
                }
            }

            terminal.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
