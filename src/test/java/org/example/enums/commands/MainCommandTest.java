package org.example.enums.commands;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import java.util.function.Function;

public class MainCommandTest {

    @Test
    void command_menu_item_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = MainCommand.MainCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> menuItemCommandFunction = commands.get("menu item");
        assertNotNull(menuItemCommandFunction);

    }

    @Test
    void command_customer_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = MainCommand.MainCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> staffMemberCommandFunction = commands.get("customer");
        assertNotNull(staffMemberCommandFunction);

    }

    @Test
    void command_staff_member_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = MainCommand.MainCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> staffMemberCommandFunction = commands.get("staff member");
        assertNotNull(staffMemberCommandFunction);

    }

}
