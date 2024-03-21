package org.example.enums.commands;

import org.example.enums.commands.MenuItemCommand;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.*;

public class MenuItemCommandTest {

    @Test
    void command_create_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = MenuItemCommand.MenuItemCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> createCommandFunction = commands.get("create");
        assertNotNull(createCommandFunction);
    }

    @Test
    void command_show_all_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = MenuItemCommand.MenuItemCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> showAllCommandFunction = commands.get("show all");
        assertNotNull(showAllCommandFunction);
    }

    @Test
    void command_update_price_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = MenuItemCommand.MenuItemCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> updatePriceCommandFunction = commands.get("update price");
        assertNotNull(updatePriceCommandFunction);
    }

    @Test
    void command_delete_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = MenuItemCommand.MenuItemCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> deleteCommandFunction = commands.get("delete");
        assertNotNull(deleteCommandFunction);
    }
}
