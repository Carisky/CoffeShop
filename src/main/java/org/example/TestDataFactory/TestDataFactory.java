package org.example.TestDataFactory;

import org.example.models.Customer.Customer;
import org.example.models.Customer.CustomerDAO;
import org.example.models.MenuItem.MenuItem;
import org.example.models.MenuItem.MenuItemDAO;
import org.example.models.Order.Order;
import org.example.models.Order.OrderDAO;
import org.example.models.Schedule.Schedule;
import org.example.models.Schedule.ScheduleDAO;
import org.example.models.StaffMember.StaffMember;
import org.example.models.StaffMember.StaffMemberDAO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Random;

public class TestDataFactory {

    private static final String[] FIRST_NAMES = {"John", "Jane", "Alice", "Bob", "Charlie", "Emma", "David", "Eva"};
    private static final String[] LAST_NAMES = {"Smith", "Doe", "Johnson", "Brown", "Miller", "Jones", "Wilson", "Moore"};

    private static final int NUMBER_OF_STAFF_MEMBERS = 5;
    private static final int NUMBER_OF_CUSTOMERS = 10;
    private static final int NUMBER_OF_MENU_ITEMS = 10;
    private static final int NUMBER_OF_ORDERS = 20;
    private static final int NUMBER_OF_SCHEDULE_ENTRIES = 15;

    private static final Random random = new Random();

    public static void generateTestData() {
        generateCustomers();
        generateMenuItems();
        generateStaffMembers();
        generateScheduleEntries();
        generateOrders();
    }

    private static void generateStaffMembers() {
        for (int i = 0; i < NUMBER_OF_STAFF_MEMBERS; i++) {
            StaffMember staffMember = new StaffMember();
            staffMember.setFullName(generateRandomName());
            staffMember.setContactPhone(generateRandomPhoneNumber());
            staffMember.setContactEmail(generateRandomEmail());
            staffMember.setPosition(generateRandomPosition());

            new StaffMemberDAO().create(staffMember);
        }
    }

    private static void generateCustomers() {
        for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
            Customer customer = new Customer();
            customer.setFullName(generateRandomName());
            customer.setBirthDate(generateRandomBirthDate());
            customer.setContactPhone(generateRandomPhoneNumber());
            customer.setContactEmail(generateRandomEmail());
            customer.setDiscount(generateRandomDiscount());

            new CustomerDAO().create(customer);
        }
    }

    private static void generateMenuItems() {
        for (int i = 0; i < NUMBER_OF_MENU_ITEMS; i++) {
            MenuItem menuItem = new MenuItem();
            menuItem.setType("Beverage " + (i + 1));
            menuItem.setName("Dessert " + (i + 1));
            menuItem.setPrice(generateRandomPrice());

            new MenuItemDAO().create(menuItem);
        }
    }

    private static void generateOrders() {
        for (int i = 0; i < NUMBER_OF_ORDERS; i++) {
            Order order = new Order();
            order.setCustomerId(generateRandomId(NUMBER_OF_CUSTOMERS));
            order.setStaffId(generateRandomId(NUMBER_OF_STAFF_MEMBERS));
            order.setMenuId(generateRandomId(NUMBER_OF_MENU_ITEMS));
            order.setOrderDate(generateRandomOrderDate());

            new OrderDAO().create(order);
        }
    }

    private static Timestamp generateRandomOrderDate() {
        long millisInDay = 24 * 60 * 60 * 1000;
        long randomMillisOffset = (long) (random.nextDouble() * millisInDay * 365); // Random date within the last year
        long currentTimeMillis = System.currentTimeMillis();
        LocalDate randomLocalDate = LocalDate.ofEpochDay((currentTimeMillis - randomMillisOffset) / millisInDay);

        ZonedDateTime zonedDateTime = randomLocalDate.atStartOfDay(ZoneId.systemDefault());
        return new Timestamp(zonedDateTime.toEpochSecond() * 1000);
    }



    private static void generateScheduleEntries() {
        for (int i = 0; i < NUMBER_OF_SCHEDULE_ENTRIES; i++) {
            Schedule schedule = new Schedule();
            schedule.setStaffId(generateRandomId(NUMBER_OF_STAFF_MEMBERS));
            schedule.setDayOfWeek(generateRandomDayOfWeek());
            schedule.setStartTime(convertLocalTimeToTime(generateRandomTime()));
            schedule.setEndTime(convertLocalTimeToTime(generateRandomTime()));

            new ScheduleDAO().create(schedule);
        }
    }

    private static Time convertLocalTimeToTime(LocalTime localTime) {
        return Time.valueOf(localTime);
    }

    private static String generateRandomName() {
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return firstName + " " + lastName;
    }

    private static String generateRandomPhoneNumber() {
        return String.format("+1-%03d-%03d-%04d",
                random.nextInt(1000),
                random.nextInt(1000),
                random.nextInt(10000));
    }

    private static String generateRandomEmail() {
        return generateRandomName().toLowerCase().replace(" ", ".") + "@example.com";
    }

    private static String generateRandomPosition() {
        String[] positions = {"Barista", "Waiter", "Confectioner"};
        return positions[random.nextInt(positions.length)];
    }

    private static java.sql.Date generateRandomBirthDate() {
        long millisInYear = 365L * 24 * 60 * 60 * 1000;
        long randomMillisOffset = (long) (random.nextDouble() * millisInYear * 30); // Random date within the last 30 years
        long currentTimeMillis = System.currentTimeMillis();
        return new java.sql.Date(currentTimeMillis - randomMillisOffset);
    }

    private static BigDecimal generateRandomDiscount() {
        double randomDiscount = random.nextDouble() * 10.000; // Random double between 0.000 and 9.999
        return BigDecimal.valueOf(randomDiscount).setScale(3, RoundingMode.HALF_UP);
    }


    private static BigDecimal generateRandomPrice() {
        double randomDiscount = random.nextDouble() * 10.0 + 5; // Random double between 5.00 and 15.00
        return BigDecimal.valueOf(randomDiscount).setScale(3, RoundingMode.HALF_UP);
    }

    private static int generateRandomId(int maxId) {
        return random.nextInt(maxId) + 1; // Assuming IDs start from 1
    }

    private static String generateRandomDayOfWeek() {
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        return daysOfWeek[random.nextInt(daysOfWeek.length)];
    }

    private static LocalTime generateRandomTime() {
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        return LocalTime.of(hour, minute);
    }
}
