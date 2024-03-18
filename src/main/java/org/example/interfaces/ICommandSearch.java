package org.example.interfaces;

import org.apache.commons.lang3.tuple.Pair;
import org.example.utils.output.ColorConsole;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public interface ICommandSearch {
    static void search(Pair<Map<String, Function<Void, Void>>, String> info){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            ColorConsole.green("Enter your command: "+info.getRight());
            String line = scanner.nextLine().trim();

            if ("help".equalsIgnoreCase(line)) {
                ColorConsole.yellow(info.getLeft().keySet().toString());
            }
            if (info.getLeft().containsKey(line)) {
                info.getLeft().get(line).apply(null);
            }
            if ("exit".equalsIgnoreCase(line)) {
                break;
            } else if (!info.getLeft().containsKey(line) && !line.equalsIgnoreCase("help")){
                ColorConsole.red("Invalid command!");
            }
        }
    }
}
