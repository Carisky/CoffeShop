package org.example.utils.input;

import org.example.DAO.OrderDAO;
import org.example.models.Order.Order;
import org.example.utils.output.ColorConsole;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class OrderInput {
    private static final OrderDAO DAO = new OrderDAO();
    private static final Scanner scanner = new Scanner(System.in);
    public static void update(){

        ColorConsole.purple("Enter Date Of Order");

        String dateString = scanner.nextLine();
        Date orderDate = Date.valueOf(dateString);

        List<Order> orders = DAO.getOrdersByDate(orderDate);

        ColorConsole.purple("Select a order:");
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            ColorConsole.yellow((i + 1) + ". Date " + order.getOrderDate()+" customer: "+order.getCustomerId());
        }
        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= orders.size()) {
            Order selectedOrder = orders.get(choice - 1);

            ColorConsole.purple("Enter New Order Date");

            String newTimestampString = scanner.next();
            Timestamp newOrderTimestamp = Timestamp.valueOf(newTimestampString);

            selectedOrder.setOrderDate(newOrderTimestamp);

            DAO.update(selectedOrder);
        }
    }
}
