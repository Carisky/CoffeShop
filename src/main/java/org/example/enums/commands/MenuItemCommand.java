package org.example.enums.commands;

import org.apache.commons.lang3.tuple.Pair;
import org.example.models.MenuItem.MenuItem;
import org.example.models.MenuItem.MenuItemDAO;
import org.example.utils.input.MenuItemInput;
import org.example.utils.output.ColorConsole;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public enum MenuItemCommand {
    CREATE("create", ()-> {
        new MenuItemDAO().create(Objects.requireNonNull(MenuItemInput.create()));
    }),
    SHOW_ALL("show all",()-> ColorConsole.cyan(new MenuItemDAO().getAll())),
    UPDATE_PRICE("update price",()->{
        MenuItemDAO DAO = new MenuItemDAO();

        List<MenuItem> items = MenuItemInput.updatePrice();

        if(items==null){
            ColorConsole.red("MenuItem with that type not found");
        }
        assert items != null;
        for (MenuItem menuItem : items) {
            DAO.update(menuItem);
        }
    }),
    DELETE("delete",()->{
        MenuItemDAO DAO = new MenuItemDAO();
        MenuItem item = MenuItemInput.searchByName();

        if(item!=null){
            DAO.delete(item.getId());
        }else ColorConsole.red("MenuItem with that name not found");

    });

    private final String commandName;
    private final Runnable executionFunction;

    MenuItemCommand(String commandName, Runnable executionFunction) {
        this.commandName = commandName;
        this.executionFunction = executionFunction;
    }

    void execute() {
        executionFunction.run();
    }

    public static Pair<Map<String, Function<Void, Void>>, String> MenuItemCommandsList(){
        Map<String, Function<Void, Void>> commands = new HashMap<>();

        for (MenuItemCommand command : MenuItemCommand.values()) {
            commands.put(command.commandName, __ -> {
                command.execute();
                return null;
            });
        }

        return Pair.of(commands,"menu item") ;
    }
}
