package fr.skytorstd.doxer.manager;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.states.ConsoleState;
import fr.skytorstd.doxer.states.messages.application.ConsoleMessages;

import java.util.Scanner;

public class ConsoleCommand {

    public static void consoleCommand() throws InterruptedException {
        while(true){
            ConsoleManager.getInstance().toConsole(ConsoleMessages.CC_INFO_STOP.getMessage(), ConsoleState.CONSOLE);
            ConsoleManager.getInstance().toConsole(ConsoleMessages.CC_INFO_RELOAD.getMessage(), ConsoleState.CONSOLE);

            try (Scanner scanner = new Scanner(System.in)) {
                String consoleCommand = scanner.nextLine();

                if(consoleCommand.equals("!stop")){
                    ConsoleManager.getInstance().toConsole(ConsoleMessages.CC_STOP.getMessage(), ConsoleState.CONSOLE);

                    App.getJda().shutdown();
                    System.exit(1);
                }else if(consoleCommand.equals("!reload")){
                    ConsoleManager.getInstance().toConsole(ConsoleMessages.CC_RELOAD.getMessage(), ConsoleState.CONSOLE);

                    App.getJda().shutdown();
                    App.runJdaBot();
                }else {
                    ConsoleManager.getInstance().toConsole(ConsoleMessages.CC_ERROR_COMMAND.getMessage(), ConsoleState.ERROR);
                }
            }

        }
    }

}
