package org.example.enums.commands;

import org.example.enums.commands.CustomerCommand;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerCommandTest {

    @Test
    void command_create_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = CustomerCommand.CustomerCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> createCommandFunction = commands.get("create");
        assertNotNull(createCommandFunction);
    }

    @Test
    void command_show_all_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = CustomerCommand.CustomerCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> showAllCommandFunction = commands.get("show all");
        assertNotNull(showAllCommandFunction);
    }

    @Test
    void command_update_customer_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = CustomerCommand.CustomerCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> updateCustomerCommandFunction = commands.get("update customer");
        assertNotNull(updateCustomerCommandFunction);
    }

    @Test
    void command_delete_customer_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = CustomerCommand.CustomerCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> deleteCustomerCommandFunction = commands.get("delete customer");
        assertNotNull(deleteCustomerCommandFunction);
    }
}
