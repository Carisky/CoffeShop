package org.example.enums.commands;

import org.apache.commons.lang3.tuple.Pair;
import org.example.interfaces.ICommandSearch;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public enum MainCommand {
    MENU_ITEM("menu item", ()->ICommandSearch.search(MenuItemCommand.MenuItemCommandsList())),
    STAFF_MEMBER("staff member", ()->ICommandSearch.search(StaffMemberCommand.StaffMemberCommandsList())),
    CUSTOMER("customer", ()->ICommandSearch.search(CustomerCommand.CustomerCommandsList())),
    ORDER("order", ()->ICommandSearch.search(OrderCommand.OrderCommandsList())),
    SCHEDULE("schedule", ()->ICommandSearch.search(ScheduleCommand.ScheduleCommandsList()));

    private final String commandName;
    private final Runnable executionFunction;

    MainCommand(String commandName, Runnable executionFunction) {
        this.commandName = commandName;
        this.executionFunction = executionFunction;
    }

    void execute() {
        executionFunction.run();
    }

    public static Pair<Map<String, Function<Void, Void>>, String> MainCommandsList(){
        Map<String, Function<Void, Void>> commands = new HashMap<>();

        for (MainCommand command : MainCommand.values()) {
            commands.put(command.commandName, __ -> {
                command.execute();
                return null;
            });
        }

        return Pair.of(commands, "main");
    }
}
