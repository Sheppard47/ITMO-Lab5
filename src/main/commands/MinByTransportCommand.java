package commands;

import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;
import commands.AbstractCommand;

/**
 * Command 'min_by_transport'. Prints the element of the collection with minimum transport.
 */
public class MinByTransportCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public MinByTransportCommand(CollectionManager collectionManager) {
        super("min_by_transport", "вывести элемент, значение поля transport которого минимально");
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
            Console.println(collectionManager.minByTransport());
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        }
        return true;
    }
}
