package org.example.utils.input;

import org.example.models.MenuItem.MenuItem;
import org.example.models.StaffMember.StaffMember;
import org.example.utils.output.ColorConsole;

import java.math.BigDecimal;
import java.util.Scanner;

public class StaffMemberInput {

    private static StaffMember create(String position){
        Scanner scanner = new Scanner(System.in);
        StaffMember member = new StaffMember();

        ColorConsole.purple("Enter Name");
        member.setFullName(scanner.nextLine());

        member.setPosition(position);

        ColorConsole.purple("Enter Phone");
        member.setContactPhone(scanner.nextLine());

        ColorConsole.purple("Enter Email");
        member.setContactEmail(scanner.nextLine());

        return member;
    }
    public static StaffMember createBarista(){
        return create("Barista");
    }

    public static StaffMember createConfectioner() {
        return create("Confectioner");
    }
}
