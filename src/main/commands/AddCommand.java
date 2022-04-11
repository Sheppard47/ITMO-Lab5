package commands;

import java.time.LocalDateTime;

import data.Flat;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;
import utility.FlatAsker;

/**
 * Command 'add'. Adds a new element to collection.
 */
public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private FlatAsker flatAsker;

    public AddCommand(CollectionManager collectionManager, FlatAsker flatAsker) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.flatAsker = flatAsker;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.addToCollection(new Flat(
                collectionManager.generateNextIndex(),
                collectionManager.generateNextId(),
                flatAsker.askName(),
                flatAsker.askCoordinates(),
                LocalDateTime.now(),
                flatAsker.askNumberOfRooms(),
                flatAsker.askCategory(),
                flatAsker.askViewType(),
                flatAsker.askTransport(),
                flatAsker.askHouse()
            ));
            Console.println("Участок успешно добавлен!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
