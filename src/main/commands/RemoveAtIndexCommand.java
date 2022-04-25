package commands;

import data.Flat;
import exceptions.FlatNotFoundException;
import exceptions.WrongAmountOfElementsException;
import exceptions.CollectionIsEmptyException;
import utility.CollectionManager;
import utility.Console;
import commands.AbstractCommand;

/**
 * Command 'remove_at_index'. Removes the element by Index.
 */
public class RemoveAtIndexCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveAtIndexCommand(CollectionManager collectionManager) {
        super("remove_at_index <Index>", "удалить элемент из коллекции по Index");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long index = Long.parseLong(argument);
            Flat flatToRemove = collectionManager.getByIndex(index);
            if (flatToRemove == null) throw new FlatNotFoundException();
            collectionManager.removeFromCollection(flatToRemove);
            Console.println("Участок успешно удален!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("Index должен быть представлен числом!");
        } catch (FlatNotFoundException exception) {
            Console.printerror("Участка с таким Index в коллекции нет!");
        }
        return false;
    }
}
