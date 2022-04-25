package data;

import java.time.LocalDateTime;
import data.Coordinates;
import data.Furnish;
import data.View;
import data.Transport;
import data.House;

/**
 * Main character. Is stored in the collection.
 */
public class Flat implements Comparable<Flat> {
    private Long index;
    private Long id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private int numberOfRooms;
    private Furnish category;
    private View viewType;
    private Transport transport;
    private House house;

    public Flat(Long index, Long id, String name, Coordinates coordinates, LocalDateTime creationDate, int numberOfRooms,
                       Furnish category, View viewType, Transport transport, House house) {
        this.index = index;
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.numberOfRooms = numberOfRooms;
        this.category = category;
        this.viewType = viewType;
        this.transport = transport;
        this.house = house;
    }

    /**
     * @return Id of the flat.
     */
    public Long getId() {
        return id;
    }

    /**
     * @return Id of the flat.
     */
    public Long getIndex() {
        return index;
    }

    /**
     * @return Name of the flat.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Coordinates of the flat.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return Creation date of the flat.
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return NumberOfRooms of the flat.
     */
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * @return Category of the flat.
     */
    public Furnish getCategory() {
        return category;
    }

    /**
     * @return View type of the flat.
     */
    public data.View getViewType() {
        return viewType;
    }

    /**
     * @return View of the flat.
     */
    public Transport getTransport() {
        return transport;
    }

    /**
     * @return House of the flat.
     */
    public House getHouse() {
        return house;
    }

    @Override
    public int compareTo(Flat flatObj) {
        return id.compareTo(flatObj.getId());
    }

    @Override
    public String toString() {
        String info = "";
        info += "Участок №" + id;
        info += " (добавлен " + creationDate.toLocalDate() + " " + creationDate.toLocalTime() + "; индекс " + index + ")";
        info += "\n Имя: " + name;
        info += "\n Местоположение: " + coordinates;
        info += "\n Номер Комнаты: " + numberOfRooms;
        info += "\n Категория: " + category;
        info += "\n Вид из окна: " + viewType;
        info += "\n Размер Транспорта: " + transport;
        info += "\n Дом: " + house;
        return info;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + coordinates.hashCode() + (int) numberOfRooms + category.hashCode() + viewType.hashCode() +
               transport.hashCode() + house.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Flat) {
            Flat flatObj = (Flat) obj;
            return name.equals(flatObj.getName()) && coordinates.equals(flatObj.getCoordinates()) &&
                   (numberOfRooms == flatObj.getNumberOfRooms()) && (category == flatObj.getCategory()) &&
                   (viewType == flatObj.getViewType()) && (transport == flatObj.getTransport()) &&
                   house.equals(flatObj.getHouse());
        }
        return false;
    }
}
