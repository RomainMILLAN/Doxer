package fr.skytorstd.doxer.manager;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.states.ConsoleState;
import fr.skytorstd.doxer.states.messages.application.ConsoleMessages;

import java.util.Scanner;

public class ConsoleCommand {

    public static void consoleCommand() throws InterruptedException {
        while(true){
            ConsoleManager.getInstance().toConsole(ConsoleMessages.CC_INFO_STOP.getMessage(), ConsoleState.CONSOLE);

            try (Scanner scanner = new Scanner(System.in)) {
                while(scanner.hasNextLine()){
                    String consoleCommand = scanner.nextLine();

                    if(consoleCommand.equals("!stop")){
                        System.exit(0);
                    }else {
                        ConsoleManager.getInstance().toConsole(ConsoleMessages.CC_ERROR_COMMAND.getMessage(), ConsoleState.ERROR);
                    }
                }
            }

        }
    }

}
