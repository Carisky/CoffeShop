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

    public static void delete(){
        ColorConsole.purple("Enter Date Of Order");

        String dateString = scanner.nextLine();
        Date orderDate = Date.valueOf(dateString);

        List<Order> orders = DAO.getOrdersByDate(orderDate);

        ColorConsole.purple("Select a order:");
        int choice = scanner.nextInt();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            ColorConsole.yellow((i + 1) + ". Date " + order.getOrderDate()+" customer: "+order.getCustomerId());
        }
        if (choice >= 1 && choice <= orders.size()) {
            Order selectedOrder = orders.get(choice - 1);
            DAO.delete(selectedOrder.getId());
        }

    }

    public static void showOrdersByDessertName() {
        ColorConsole.purple("Enter Dessert Name");
        String dessertName = scanner.nextLine();

        List<Order> orders = new OrderDAO().getOrdersByDessertName(dessertName);

        if (!orders.isEmpty()) {
            ColorConsole.green("Orders for Dessert '" + dessertName + "':");
            for (Order order : orders) {
                ColorConsole.yellow("Order ID: " + order.getId() + ", Customer ID: " + order.getCustomerId() + ", Staff ID: " + order.getStaffId() + ", Order Date: " + order.getOrderDate());
            }
        } else {
            ColorConsole.red("No orders found for Dessert '" + dessertName + "'.");
        }
    }


    public static void deleteOrdersByDesertName(){
        ColorConsole.purple("Enter Desert Name");
        String desertName = scanner.nextLine();

        DAO.deleteOrdersByMenuName(desertName);
    }
}
