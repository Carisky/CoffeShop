package org.example.utils.input;

import org.example.DAO.ScheduleDAO;
import org.example.DAO.StaffMemberDAO;
import org.example.models.Schedule.Schedule;
import org.example.models.StaffMember.StaffMember;
import org.example.utils.output.ColorConsole;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class ScheduleInput {
    private static final StaffMemberDAO staffMemberDAO = new StaffMemberDAO();
    private static final Scanner scanner = new Scanner(System.in);

    private static String getDay() {
        String day;
        ColorConsole.purple("Enter day");

        ColorConsole.yellow("1 - Monday");
        ColorConsole.yellow("2 - Tuesday");
        ColorConsole.yellow("3 - Wednesday");
        ColorConsole.yellow("4 - Thursday");
        ColorConsole.yellow("5 - Friday");

        int dayNumber = scanner.nextInt();
        switch (dayNumber) {
            case 1:
                day = "Monday";
                break;
            case 2:
                day = "Tuesday";
                break;
            case 3:
                day = "Wednesday";
                break;
            case 4:
                day = "Thursday";
                break;
            case 5:
                day = "Friday";
                break;
            default:
                day = "Invalid day";
                break;
        }

        return day;
    }


    public static Schedule create() {
        Schedule schedule = new Schedule();

        ColorConsole.purple("Enter Name of Staff Member");
        String fullName = scanner.nextLine();

        List<StaffMember> members = staffMemberDAO.searchByFullName(fullName);

        if (members != null && !members.isEmpty()) {
            ColorConsole.purple("Select a Staff Member:");

            for (int i = 0; i < members.size(); i++) {
                StaffMember member = members.get(i);
                ColorConsole.yellow((i + 1) + ". " + member.getFullName());
            }

            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= members.size()) {
                StaffMember selectedMember = members.get(choice - 1);
                schedule.setStaffId(selectedMember.getId());

                String dayOfWeek = getDay();
                schedule.setDayOfWeek(dayOfWeek);

                ColorConsole.purple("Enter Start Time");
                String startString = scanner.next();
                Time start = Time.valueOf(startString);
                schedule.setStartTime(start);

                ColorConsole.purple("Enter End Time");
                String endString = scanner.next();
                Time end = Time.valueOf(endString);
                schedule.setEndTime(end);
            } else {
                ColorConsole.red("Invalid choice. Please select a valid number.");
            }

        } else {
            ColorConsole.red("Member " + fullName + " Doesn't Found");
        }

        return schedule;
    }

    public static Schedule update(){


        ColorConsole.purple("Enter Name of Staff Member");
        String fullName = scanner.nextLine();

        List<StaffMember> members = staffMemberDAO.searchByFullName(fullName);

        if (members != null && !members.isEmpty()) {
            ColorConsole.purple("Select a Staff Member:");

            for (int i = 0; i < members.size(); i++) {
                StaffMember member = members.get(i);
                ColorConsole.yellow((i + 1) + ". " + member.getFullName());
            }

            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= members.size()) {
                StaffMember selectedMember = members.get(choice - 1);
                List<Schedule> schedules = new ScheduleDAO().readByStaffId(selectedMember.getId());


                for (int i = 0; i < schedules.size(); i++) {
                    Schedule schedule = schedules.get(i);
                    ColorConsole.yellow((i + 1) + ". " + schedule.getDayOfWeek()+" "+schedule.getStartTime()+" "+schedule.getEndTime());
                }

                int schedulesChoice = scanner.nextInt();
                if (schedulesChoice >= 1 && schedulesChoice <= schedules.size()) {
                    Schedule schedule = schedules.get(schedulesChoice - 1);

                    schedule.setStaffId(selectedMember.getId());

                    String dayOfWeek = getDay();
                    schedule.setDayOfWeek(dayOfWeek);

                    ColorConsole.purple("Enter Start Time");
                    String startString = scanner.next();
                    Time start = Time.valueOf(startString);
                    schedule.setStartTime(start);

                    ColorConsole.purple("Enter End Time");
                    String endString = scanner.next();
                    Time end = Time.valueOf(endString);
                    schedule.setEndTime(end);
                    return schedule;

                } else {
                    ColorConsole.red("Invalid choice. Please select a valid number.");
                }
            }
        } else {
            ColorConsole.red("Member " + fullName + " Doesn't Found");
        }

        return null;

    }

    public static void deleteSchedule() {
        ColorConsole.purple("Enter Name of Staff Member");
        String fullName = scanner.nextLine();

        List<StaffMember> members = staffMemberDAO.searchByFullName(fullName);

        if (members != null && !members.isEmpty()) {
            ColorConsole.purple("Select a Staff Member:");

            for (int i = 0; i < members.size(); i++) {
                StaffMember member = members.get(i);
                ColorConsole.yellow((i + 1) + ". " + member.getFullName());
            }

            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= members.size()) {
                StaffMember selectedMember = members.get(choice - 1);
                String dayOfWeek = getDay();
                new ScheduleDAO().deleteScheduleByStaffIdAndDay(selectedMember.getId(), dayOfWeek);
                ColorConsole.green("Schedule for " + selectedMember.getFullName() + " on " + dayOfWeek + " has been deleted.");
            } else {
                ColorConsole.red("Invalid choice. Please select a valid number.");
            }
        } else {
            ColorConsole.red("Member " + fullName + " Doesn't Found");
        }
    }

    public static void deleteScheduleBetweenDates() {
        ColorConsole.purple("Enter Name of Staff Member");
        String fullName = scanner.nextLine();

        List<StaffMember> members = staffMemberDAO.searchByFullName(fullName);

        if (members != null && !members.isEmpty()) {
            ColorConsole.purple("Select a Staff Member:");

            for (int i = 0; i < members.size(); i++) {
                StaffMember member = members.get(i);
                ColorConsole.yellow((i + 1) + ". " + member.getFullName());
            }

            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= members.size()) {
                StaffMember selectedMember = members.get(choice - 1);

                ColorConsole.purple("Enter Start Date (YYYY-MM-DD)");
                String startDateString = scanner.next();
                Date startDate = Date.valueOf(startDateString);

                ColorConsole.purple("Enter End Date (YYYY-MM-DD)");
                String endDateString = scanner.next();
                Date endDate = Date.valueOf(endDateString);

                new ScheduleDAO().deleteScheduleBetweenDates(selectedMember.getId(), startDate, endDate);
                ColorConsole.green("Schedule for " + selectedMember.getFullName() + " between " + startDate + " and " + endDate + " has been deleted.");
            } else {
                ColorConsole.red("Invalid choice. Please select a valid number.");
            }
        } else {
            ColorConsole.red("Member " + fullName + " Doesn't Found");
        }
    }

    public static void showScheduleByStaffIdAndDay() {
        ColorConsole.purple("Enter Name of Staff Member");
        String fullName = scanner.nextLine();

        List<StaffMember> members = staffMemberDAO.searchByFullName(fullName);

        if (members != null && !members.isEmpty()) {
            ColorConsole.purple("Select a Staff Member:");

            for (int i = 0; i < members.size(); i++) {
                StaffMember member = members.get(i);
                ColorConsole.yellow((i + 1) + ". " + member.getFullName());
            }

            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= members.size()) {
                StaffMember selectedMember = members.get(choice - 1);

                String dayOfWeek = getDay();
                List<Schedule> schedules = new ScheduleDAO().readByStaffIdAndDayOfWeek(selectedMember.getId(), dayOfWeek);

                if (!schedules.isEmpty()) {
                    ColorConsole.purple("Schedule for " + selectedMember.getFullName() + " on " + dayOfWeek + ":");

                    for (Schedule schedule : schedules) {
                        ColorConsole.yellow("Start Time: " + schedule.getStartTime() + ", End Time: " + schedule.getEndTime());
                    }
                } else {
                    ColorConsole.yellow("No schedule found for " + selectedMember.getFullName() + " on " + dayOfWeek);
                }
            } else {
                ColorConsole.red("Invalid choice. Please select a valid number.");
            }
        } else {
            ColorConsole.red("Member " + fullName + " Doesn't Found");
        }
    }

}
