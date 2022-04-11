package run;

import java.util.Scanner;
import commands.RemoveByIdCommand;
import commands.RemoveLastCommand;
import commands.RemoveAtIndexCommand;
import commands.AddCommand;
import commands.ClearCommand;
import commands.ExecuteScriptCommand;
import commands.ExitCommand;
import commands.CountGreaterThanViewTypeCommand;
import commands.HelpCommand;
import commands.HistoryCommand;
import commands.InfoCommand;
import commands.MinByTransportCommand;
import commands.SaveCommand;
import commands.ShowCommand;
import commands.PrintAscendingCommand;
import commands.UpdateCommand;
import utility.CollectionManager;
import utility.CommandManager;
import utility.Console;
import utility.FileManager;
import utility.FlatAsker;


/**
 * Main application class. Creates all instances and runs the program.
 * @author Shmelev Roman R3138
 */
public class App {
    public static final String PS1 = "$ ";
    public static final String PS2 = "> ";

    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)) {
            final String envVariable = "Collection.json";

            FlatAsker flatAsker = new FlatAsker(userScanner);
            FileManager fileManager = new FileManager(envVariable);
            CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager(
                new HelpCommand(),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new PrintAscendingCommand(collectionManager),
                new AddCommand(collectionManager, flatAsker),
                new UpdateCommand(collectionManager, flatAsker),
                new RemoveByIdCommand(collectionManager),
                new RemoveLastCommand(collectionManager),
                new RemoveAtIndexCommand(collectionManager),
                new ClearCommand(collectionManager),
                new SaveCommand(collectionManager),
                new ExitCommand(),
                new ExecuteScriptCommand(),
                new HistoryCommand(),
                new MinByTransportCommand(collectionManager),
                new CountGreaterThanViewTypeCommand(collectionManager));
            Console console = new Console(commandManager, userScanner, flatAsker);

            console.interactiveMode();
        }
    }
}
