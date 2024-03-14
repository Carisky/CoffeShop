package org.example.Menu;

import org.example.models.Customer.CustomerDAO;
import org.example.models.StaffMember.StaffMemberDAO;
import org.example.utils.input.CustomerInput;
import org.example.utils.input.StaffMemberInput;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.Objects;

public class CustomerCLI {
    public static void RUN() {
        try {
            Terminal terminal = TerminalBuilder.builder().build();
            LineReader lineReader = LineReaderBuilder.builder().terminal(terminal).build();
            CustomerDAO DAO = new CustomerDAO();

            while (true) {
                String line = lineReader.readLine("Enter your command for CustomerCLI: ");

                if ("exit".equalsIgnoreCase(line.trim())) {
                    break;
                }
                if("create".equalsIgnoreCase(line.trim())) {
                    DAO.create(Objects.requireNonNull(CustomerInput.create()));
                }
            }

            terminal.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
