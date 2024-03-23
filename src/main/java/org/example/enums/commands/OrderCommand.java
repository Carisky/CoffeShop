package org.example.enums.commands;
import org.apache.commons.lang3.tuple.Pair;
import org.example.utils.input.OrderInput;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public enum OrderCommand {

    CREATE("update order", OrderInput::update);

    private final String commandName;
    private final Runnable executionFunction;

    OrderCommand(String commandName, Runnable executionFunction) {
        this.commandName = commandName;
        this.executionFunction = executionFunction;
    }

    void execute() {
        executionFunction.run();
    }

    public static Pair<Map<String, Function<Void, Void>>, String> OrderCommandsList() {
        Map<String, Function<Void, Void>> commands = new HashMap<>();

        for (OrderCommand command : OrderCommand.values()) {
            commands.put(command.commandName, __ -> {
                command.execute();
                return null;
            });
        }

        return Pair.of(commands, "order");
    }
}
