package org.example.enums.commands;

import org.apache.commons.lang3.tuple.Pair;
import org.example.DAO.MenuItemDAO;
import org.example.DAO.ScheduleDAO;
import org.example.utils.input.MenuItemInput;
import org.example.utils.input.ScheduleInput;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public enum ScheduleCommand {

    CREATE("create", ()-> {
        new ScheduleDAO().create(ScheduleInput.create());
    }),
    DELETE("delete",ScheduleInput::deleteSchedule),
    DELETE_BETWEEN_DATES("delete between dates",ScheduleInput::deleteScheduleBetweenDates),
    UPDATE("update", () -> {
        new ScheduleDAO().update(Objects.requireNonNull(ScheduleInput.update()));
    });

    private final String commandName;
    private final Runnable executionFunction;

    ScheduleCommand(String commandName, Runnable executionFunction) {
        this.commandName = commandName;
        this.executionFunction = executionFunction;
    }

    void execute() {
        executionFunction.run();
    }

    public static Pair<Map<String, Function<Void, Void>>, String> ScheduleCommandsList(){
        Map<String, Function<Void, Void>> commands = new HashMap<>();

        for (ScheduleCommand command : ScheduleCommand.values()) {
            commands.put(command.commandName, __ -> {
                command.execute();
                return null;
            });
        }

        return Pair.of(commands,"schedule") ;
    }
}
