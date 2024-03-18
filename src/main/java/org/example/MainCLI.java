package org.example;

import org.example.enums.commands.MainCommand;
import org.example.interfaces.ICommandSearch;


public class MainCLI {
    public static void main(String[] args) {
        ICommandSearch.search(MainCommand.MainCommandsList());
    }
}
