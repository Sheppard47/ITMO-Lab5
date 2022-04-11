package commands;

import data.Flat;
import exceptions.WrongAmountOfElementsException;
import exceptions.CollectionIsEmptyException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'remove_last'. Removes the last element.
 */
public class RemoveLastCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveLastCommand(CollectionManager collectionManager) {
        super("remove_last", "удалить последний элемент из коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Flat flatToRemove = collectionManager.getLast();
            collectionManager.removeFromCollection(flatToRemove);
            Console.println("Участок успешно удален!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        }
        return false;
    }
}
