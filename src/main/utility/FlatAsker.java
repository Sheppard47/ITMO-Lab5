package utility;

import java.util.NoSuchElementException;
import java.util.Scanner;

import data.Furnish;
import data.House;
import data.Coordinates;
import data.Transport;
import data.View;
import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import exceptions.NotInDeclaredLimitsException;
import run.App;
import utility.Console;

/**
 * Asks a user a flat's value.
 */
public class FlatAsker {
    private final int MAX_Y = 960;
    private final int MIN_NUMBER_OF_ROOMS = 0;
    private final long MIN_MARINES = 1;

    private Scanner userScanner;
    private boolean fileMode;
    
    public FlatAsker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    /**
     * Sets a scanner to scan user input.
     * @param userScanner Scanner to set.
     */
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * @return Scanner, which uses for user input.
     */
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * Sets flat asker mode to 'File Mode'.
     */
    public void setFileMode() {
        fileMode = true;
    }

    /**
     * Sets flat asker mode to 'User Mode'.
     */
    public void setUserMode() {
        fileMode = false;
    }

    /**
     * Asks a user the flat's name.
     * @return Flat's name.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Console.println("Введите название:");
                Console.print(App.PS2);
                name = userScanner.nextLine().trim();
                if (fileMode) Console.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Имя не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("Имя не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Asks a user the flat's X coordinate.
     * @return Flat's X coordinate.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Long askX() throws IncorrectInputInScriptException {
        String strX;
        Long x;
        while (true) {
            try {
                Console.println("Введите координату X:");
                Console.print(App.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println(strX);
                x = Long.parseLong(strX);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата X не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата X должна быть представлена целым числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;
    }

    /**
     * Asks a user the flat's Y coordinate.
     * @return Flat's Y coordinate.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Float askY() throws IncorrectInputInScriptException {
        String strY;
        Float y;
        while (true) {
            try {
                Console.println("Введите координату Y <= " + (MAX_Y) + ":");
                Console.print(App.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode) Console.println(strY);
                y = Float.parseFloat(strY);
                if (y > MAX_Y) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата Y не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Координата Y не может превышать " + MAX_Y + "!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }



    /**
     * Asks a user the flat's coordinates.
     * @return Flat's coordinates.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        Long x;
        Float y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }

    /**
     * Asks a user the flat's numberOfRooms.
     * @return Flat's numberOfRooms.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public int askNumberOfRooms() throws IncorrectInputInScriptException {
        String strNumberOfRooms;
        int numberOfRooms;
        while (true) {
            try {
                Console.println("Введите номер комнаты:");
                Console.print(App.PS2);
                strNumberOfRooms = userScanner.nextLine().trim();
                if (fileMode) Console.println(strNumberOfRooms);
                numberOfRooms = Integer.parseInt(strNumberOfRooms);
                if (numberOfRooms < MIN_NUMBER_OF_ROOMS) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Номер комнаты не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Номер комнаты не может быть отрицательным!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Номер комнаты должен быть представлен целым числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return numberOfRooms;
    }

    /**
     * Asks a user the flat's category.
     * @return Flat's category.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Furnish askCategory() throws IncorrectInputInScriptException {
        String strCategory;
        Furnish category;
        while (true) {
            try {
                Console.println("Список категорий - " + Furnish.nameList());
                Console.println("Введите категорию:");
                Console.print(App.PS2);
                strCategory = userScanner.nextLine().trim();
                if (fileMode) Console.println(strCategory);
                category = Furnish.valueOf(strCategory.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Категория не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("Категории нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return category;
    }

    /**
     * Asks a user the flat's view type.
     * @return Flat's view type.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public View askViewType() throws IncorrectInputInScriptException {
        String strViewType;
        View viewType;
        while (true) {
            try {
                Console.println("Список видов из окна - " + View.nameList());
                Console.println("Введите вид из окна:");
                Console.print(App.PS2);
                strViewType = userScanner.nextLine().trim();
                if (fileMode) Console.println(strViewType);
                viewType = View.valueOf(strViewType.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Вид из окна не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("Вида из окна нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return viewType;
    }

    /**
     * Asks a user the flat's transport.
     * @return Flat's transport.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Transport askTransport() throws IncorrectInputInScriptException {
        String strTransport;
        Transport transport;
        while (true) {
            try {
                Console.println("Список размеров транспорта - " + Transport.nameList());
                Console.println("Введите размер транспорта:");
                Console.print(App.PS2);
                strTransport = userScanner.nextLine().trim();
                if (fileMode) Console.println(strTransport);
                transport = Transport.valueOf(strTransport.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Транспорт не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("Транспорта нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return transport;
    }

    /**
     * Asks a user the flat's house.
     * @return Flat's house.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public House askHouse() throws IncorrectInputInScriptException {
        String name;
        long houseYearCount;
        long houseFloorsCount;
        name = askHouseName();
        houseYearCount = askHouseYearCount();
        houseFloorsCount = askHouseFloorsCount();
        return new House(name, houseYearCount, houseFloorsCount);
    }


    /**
     * Asks a user the flat house's name.
     * @return House's name.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public String askHouseName() throws IncorrectInputInScriptException {
        String houseName;
        while (true) {
            try {
                Console.println("Введите название дома:");
                Console.print(App.PS2);
                houseName = userScanner.nextLine().trim();
                if (fileMode) Console.println(houseName);
                if (houseName.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Название дома не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("Название дома не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return houseName;
    }

    /**
     * Asks a user the flat house's number of soldiers.
     * @return Number of soldiers.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public long askHouseYearCount() throws IncorrectInputInScriptException {
        String strHouseYearCount;
        long HouseYearCount;
        while (true) {
            try {
                Console.println("Введите год дома:");
                Console.print(App.PS2);
                strHouseYearCount = userScanner.nextLine().trim();
                if (fileMode) Console.println(strHouseYearCount);
                HouseYearCount = Long.parseLong(strHouseYearCount);
                if (HouseYearCount < MIN_MARINES) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Год дома не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Год дома должен быть натуральным числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Год дома должен быть представлен целым числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return HouseYearCount;
    }

    /**
     * Asks a user the flat house's HouseFloorsCount.
     * @return Number of soldiers.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public long askHouseFloorsCount() throws IncorrectInputInScriptException {
        String strHouseFloorsCount;
        long HouseFloorsCount;
        while (true) {
            try {
                Console.println("Введите кол-во этажей дома:");
                Console.print(App.PS2);
                strHouseFloorsCount = userScanner.nextLine().trim();
                if (fileMode) Console.println(strHouseFloorsCount);
                HouseFloorsCount = Long.parseLong(strHouseFloorsCount);
                if (HouseFloorsCount < MIN_MARINES) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Кол-во этажей не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Кол-во этажей должено быть натуральным числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Кол-во этажей должено быть представлен целым числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return HouseFloorsCount;
    }


    /**
     * Asks a user a question.
     * @return Answer (true/false).
     * @param question A question.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public boolean askQuestion(String question) throws IncorrectInputInScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                Console.print(App.PS2);
                answer = userScanner.nextLine().trim();
                if (fileMode) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Ответ не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Ответ должен быть представлен знаками '+' или '-'!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return (answer.equals("+")) ? true : false;
    }

    @Override
    public String toString() {
        return "FlatAsker (вспомогательный класс для запросов пользователю)";
    }
}
