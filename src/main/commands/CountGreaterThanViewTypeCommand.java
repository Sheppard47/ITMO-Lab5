package commands;

import data.View;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'count_greater_than_view_type'. Filters the collection by view type.
 */
public class CountGreaterThanViewTypeCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public CountGreaterThanViewTypeCommand(CollectionManager collectionManager) {
        super("count_greater_than_view_type <view>", "вывести элементы, значение поля viewType которых равно заданному");
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
            View view = View.valueOf(argument.toUpperCase());
            String count = collectionManager.viewCountInfo(view);
            if (!count.isEmpty()) {
                Console.println("Количество элементов, значение поля view которых больше заданного " + count);
                return true;
            }
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (IllegalArgumentException exception) {
            Console.printerror("Вида из окна нет в списке!");
            Console.println("Список видов из окна - " + View.nameList());
        }
        return false;
    }
}
