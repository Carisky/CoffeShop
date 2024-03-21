package org.example.enums.commands;

import org.example.models.Customer.Customer;
import org.example.DAO.CustomerDAO;
import org.example.utils.input.CustomerInput;
import org.apache.commons.lang3.tuple.Pair;
import org.example.utils.output.ColorConsole;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public enum CustomerCommand {

    CREATE("create", ()-> new CustomerDAO().create(Objects.requireNonNull(CustomerInput.create()))),
    SHOW_ALL("show all",()-> ColorConsole.cyan(new CustomerDAO().getAll())),

    UPDATE("update customer", ()->{
       CustomerDAO DAO = new CustomerDAO();

       Customer customer = CustomerInput.update();

        if (customer!=null){
            DAO.update(customer);
        }else ColorConsole.red("Customer Doesn't Found");

    }),
    DELETE("delete customer", ()->{
        CustomerDAO DAO = new CustomerDAO();

        Customer customer = CustomerInput.searchByFullName();

        if (customer!=null){
            DAO.delete(customer.getId());
        }else ColorConsole.red("Customer Doesn't Found");

    });

    private final String commandName;
    private final Runnable executionFunction;

    CustomerCommand(String commandName, Runnable executionFunction) {
        this.commandName = commandName;
        this.executionFunction = executionFunction;
    }

    void execute() {
        executionFunction.run();
    }

    public static Pair<Map<String, Function<Void, Void>>, String>  CustomerCommandsList(){
        Map<String, Function<Void, Void>> commands = new HashMap<>();

        for (CustomerCommand command : CustomerCommand.values()) {
            commands.put(command.commandName, __ -> {
                command.execute();
                return null;
            });
        }

        return Pair.of(commands, "customer");
    }
}
