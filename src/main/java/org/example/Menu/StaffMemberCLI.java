package org.example.Menu;

import org.example.models.MenuItem.MenuItemDAO;
import org.example.models.StaffMember.StaffMemberDAO;
import org.example.utils.input.MenuItemInput;
import org.example.utils.input.StaffMemberInput;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class StaffMemberCLI {
    public static void RUN() {
        try {
            // Create a terminal
            Terminal terminal = TerminalBuilder.builder().build();
            LineReader lineReader = LineReaderBuilder.builder().terminal(terminal).build();
            StaffMemberDAO DAO = new StaffMemberDAO();

            // Main loop for reading input
            while (true) {
                String line = lineReader.readLine("Enter your command for StaffMemberCLI: ");

                // Check for exit command
                if ("exit".equalsIgnoreCase(line.trim())) {
                    break;
                }
                if("create barista".equalsIgnoreCase(line.trim())) {
                    DAO.create(StaffMemberInput.createBarista());
                }
                if("create confectioner".equalsIgnoreCase(line.trim())) {
                    DAO.create(StaffMemberInput.createConfectioner());
                }
            }

            // Close the terminal
            terminal.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
