package org.example.utils.input;

import org.example.models.StaffMember.StaffMember;
import org.example.models.StaffMember.StaffMemberDAO;
import org.example.utils.output.ColorConsole;

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

    public static void updateConfectioner(StaffMember member){
        Scanner scanner = new Scanner(System.in);

        ColorConsole.purple("Enter Contact Phone");
        String phone = scanner.nextLine();
        member.setContactPhone(phone);

        ColorConsole.purple("Enter Contact Email");
        String email = scanner.nextLine();
        member.setContactEmail(email);
    }

    public static void updateBarista(StaffMember member){
        Scanner scanner = new Scanner(System.in);

        ColorConsole.purple("Enter Contact Phone");
        String phone = scanner.nextLine();
        member.setContactPhone(phone);
    }

    public static StaffMember searchByFullNameAndPosition(String position){
        Scanner scanner = new Scanner(System.in);
        StaffMemberDAO DAO = new StaffMemberDAO();

        ColorConsole.purple("Enter Full Name");
        return DAO.searchByFullNameAndPosition(scanner.nextLine(),position);
    }
    public static StaffMember searchBaristaByFullName(){return searchByFullNameAndPosition("Barista"); }
    public static StaffMember searchConfectionerByFullName(){return searchByFullNameAndPosition("Confectioner"); }

}
