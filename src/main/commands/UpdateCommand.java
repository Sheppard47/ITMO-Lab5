package commands;

import java.time.LocalDateTime;

import data.Furnish;
import data.House;
import data.Coordinates;
import data.Transport;
import data.Flat;
import data.View;
import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputInScriptException;
import exceptions.FlatNotFoundException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;
import utility.FlatAsker;

/**
 * Command 'update'. Updates the information about selected flat.
 */
public class UpdateCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private FlatAsker flatAsker;

    public UpdateCommand(CollectionManager collectionManager, FlatAsker flatAsker) {
        super("update <Id> {element}", "обновить значение элемента коллекции по Id");
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
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long index = Long.parseLong(argument);
            Long id = Long.parseLong(argument);
            Flat oldFlat = collectionManager.getById(id);
            if (oldFlat == null) throw new FlatNotFoundException();

            String name = oldFlat.getName();
            Coordinates coordinates = oldFlat.getCoordinates();
            LocalDateTime creationDate = oldFlat.getCreationDate();
            int numberOfRooms = oldFlat.getNumberOfRooms();
            Furnish category = oldFlat.getCategory();
            View viewType = oldFlat.getViewType();
            Transport transport = oldFlat.getTransport();
            House house = oldFlat.getHouse();

            collectionManager.removeFromCollection(oldFlat);

            if (flatAsker.askQuestion("Хотите изменить имя участка?")) name = flatAsker.askName();
            if (flatAsker.askQuestion("Хотите изменить координаты участка?")) coordinates = flatAsker.askCoordinates();
            if (flatAsker.askQuestion("Хотите изменить номер комнаты участка?")) numberOfRooms = flatAsker.askNumberOfRooms();
            if (flatAsker.askQuestion("Хотите изменить категорию участка?")) category = flatAsker.askCategory();
            if (flatAsker.askQuestion("Хотите изменить вид из окна участка?")) viewType = flatAsker.askViewType();
            if (flatAsker.askQuestion("Хотите изменить транспорт участка?")) transport = flatAsker.askTransport();
            if (flatAsker.askQuestion("Хотите изменить дом участка?")) house = flatAsker.askHouse();

            collectionManager.addToCollection(new Flat(
                index,
                id,
                name,
                coordinates,
                creationDate,
                numberOfRooms,
                category,
                viewType,
                transport,
                house
            ));
            Console.println("Участок успешно изменен!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("Id должен быть представлен числом!");
        } catch (FlatNotFoundException exception) {
            Console.printerror("Участка с таким Id в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
