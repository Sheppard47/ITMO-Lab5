package commands;

import data.Flat;
import exceptions.FlatNotFoundException;
import exceptions.WrongAmountOfElementsException;
import exceptions.CollectionIsEmptyException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'remove_by_id'. Removes the element by its Id.
 */
public class RemoveByIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id <Id>", "удалить элемент из коллекции по Id");
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
            Long id = Long.parseLong(argument);
            Flat flatToRemove = collectionManager.getById(id);
            if (flatToRemove == null) throw new FlatNotFoundException();
            collectionManager.removeFromCollection(flatToRemove);
            Console.println("Участок успешно удален!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("Id должен быть представлен числом!");
        } catch (FlatNotFoundException exception) {
            Console.printerror("Участка с таким Id в коллекции нет!");
        }
        return false;
    }
}
