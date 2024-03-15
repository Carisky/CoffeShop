package org.example.Menu;

import org.example.models.StaffMember.StaffMemberDAO;
import org.example.utils.input.StaffMemberInput;
import java.util.Scanner;
public class StaffMemberCLI {
    public static void RUN() {
            // Create a terminal
            Scanner scanner = new Scanner(System.in);
            StaffMemberDAO DAO = new StaffMemberDAO();

            // Main loop for reading input
            while (true) {
                System.out.println("Enter your command: ");
                String line = scanner.nextLine();

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

    }
}
