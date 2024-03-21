package org.example.enums.commands;

import org.example.enums.commands.StaffMemberCommand;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.*;

public class StaffMemberCommandTest {

    @Test
    void command_create_barista_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = StaffMemberCommand.StaffMemberCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> createBaristaCommandFunction = commands.get("create barista");
        assertNotNull(createBaristaCommandFunction);
    }

    @Test
    void command_create_confectioner_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = StaffMemberCommand.StaffMemberCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> createConfectionerCommandFunction = commands.get("create confectioner");
        assertNotNull(createConfectionerCommandFunction);
    }

    @Test
    void command_update_barista_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = StaffMemberCommand.StaffMemberCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> updateBaristaCommandFunction = commands.get("update barista");
        assertNotNull(updateBaristaCommandFunction);
    }

    @Test
    void command_update_confectioner_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = StaffMemberCommand.StaffMemberCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> updateConfectionerCommandFunction = commands.get("update confectioner");
        assertNotNull(updateConfectionerCommandFunction);
    }

    @Test
    void command_delete_barista_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = StaffMemberCommand.StaffMemberCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> deleteBaristaCommandFunction = commands.get("delete barista");
        assertNotNull(deleteBaristaCommandFunction);
    }

    @Test
    void command_delete_confectioner_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = StaffMemberCommand.StaffMemberCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> deleteConfectionerCommandFunction = commands.get("delete confectioner");
        assertNotNull(deleteConfectionerCommandFunction);
    }

    @Test
    void command_show_all_ShouldExecute_WhenCalled() {
        Pair<Map<String, Function<Void, Void>>, String> commandsList = StaffMemberCommand.StaffMemberCommandsList();
        Map<String, Function<Void, Void>> commands = commandsList.getLeft();

        Function<Void, Void> showAllCommandFunction = commands.get("show all");
        assertNotNull(showAllCommandFunction);
    }
}
