package org.example.enums.commands;

import org.apache.commons.lang3.tuple.Pair;
import org.example.models.StaffMember.StaffMember;
import org.example.models.StaffMember.StaffMemberDAO;
import org.example.utils.input.StaffMemberInput;
import org.example.utils.output.ColorConsole;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public enum StaffMemberCommand {
    CREATE_BARISTA("create barista", ()-> {
        new StaffMemberDAO().create(StaffMemberInput.createBarista());
    }),
    CREATE_CONFECTIONER("create confectioner", ()-> {
        new StaffMemberDAO().create(StaffMemberInput.createConfectioner());
    }),
    UPDATE_CONFECTIONER("update confectioner", ()-> {
        StaffMember confectioner = StaffMemberInput.searchConfectionerByFullName();

        if(confectioner!=null){
            StaffMemberInput.updateConfectioner(confectioner);
            new StaffMemberDAO().update(confectioner);
        }
        else ColorConsole.red("Member Doesn't Found");
    }),

    UPDATE_BARISTA("update barista", ()-> {
        StaffMember barista = StaffMemberInput.searchBaristaByFullName();

        if(barista!=null){
            StaffMemberInput.updateBarista(barista);
            new StaffMemberDAO().update(barista);
        }
        else ColorConsole.red("Member Doesn't Found");
    }),
    DELETE_BARISTA("delete barista", ()-> {
        StaffMember barista = StaffMemberInput.searchBaristaByFullName();

        if(barista!=null){
            new StaffMemberDAO().delete(barista.getId());
        }else ColorConsole.red("Member Doesn't Found");
    }),
    DELETE_CONFECTIONER("delete confectioner", ()-> {
        StaffMember confectioner = StaffMemberInput.searchConfectionerByFullName();

        if(confectioner!=null){
            new StaffMemberDAO().delete(confectioner.getId());
        }
        else ColorConsole.red("Member Doesn't Found");
    }),
    SHOW_ALL("show all",()-> ColorConsole.cyan(new StaffMemberDAO().getAll()));

    private final String commandName;
    private final Runnable executionFunction;

    StaffMemberCommand(String commandName, Runnable executionFunction) {
        this.commandName = commandName;
        this.executionFunction = executionFunction;
    }

    void execute() {
        executionFunction.run();
    }

    public static Pair<Map<String, Function<Void, Void>>, String> StaffMemberCommandsList(){
        Map<String, Function<Void, Void>> commands = new HashMap<>();

        for (StaffMemberCommand command : StaffMemberCommand.values()) {
            commands.put(command.commandName, __ -> {
                command.execute();
                return null;
            });
        }

        return Pair.of(commands,"staff member");
    }
}
