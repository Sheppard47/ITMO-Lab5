package utility;

import java.time.LocalDateTime;
import java.util.Stack;
import data.Flat;
import data.View;
import exceptions.CollectionIsEmptyException;
import utility.FileManager;



/**
 * Operates the collection itself.
 */
public class CollectionManager {
    private Stack<Flat> flatsCollection =  new Stack<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;

    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;

        loadCollection();
    }

    /**
     * @return The collecton itself.
     */
    public Stack<Flat> getCollection() {
        return flatsCollection;
    }

    /**
     * @return Last initialization time or null if there wasn't initialization.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Last save time or null if there wasn't saving.
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * @return Name of the collection's type.
     */
    public String collectionType() {
        return flatsCollection.getClass().getName();
    }

    /**
     * @return Size of the collection.
     */
    public int collectionSize() {
        return flatsCollection.size();
    }

    /**
     * @return The first element of the collection or null if collection is empty.
     */
    public Flat getFirst() {
        if (flatsCollection.isEmpty()) return null;
        return flatsCollection.firstElement();
    }

    /**
     * @return The last element of the collection or null if collection is empty.
     */
    public Flat getLast() {
        if (flatsCollection.isEmpty()) return null;
        return flatsCollection.lastElement();
    }

    /**
     * @param id Id of the flat.
     * @return A flat by his Id or null if flat isn't found.
     */
    public Flat getById(Long id) {
        for (Flat flat : flatsCollection) {
            if (flat.getId().equals(id)) return flat;
        }
        return null;
    }

    /**
     * @param index Index of the flat.
     * @return A flat by his Index or null if flat isn't found.
     */
    public Flat getByIndex(Long index) {
        for (Flat flat : flatsCollection) {
            if (flat.getIndex().equals(index)) return flat;
        }
        return null;
    }

    /**
     * @param flatToFind A flat who's value will be found.
     * @return A flat by his value or null if flat isn't found.
     */
    public Flat getByValue(Flat flatToFind) {
        for (Flat flat : flatsCollection) {
            if (flat.equals(flatToFind)) return flat;
        }
        return null;
    }


    /**
     * @return Flat, who has min transport.
     * @throws CollectionIsEmptyException If collection is empty.
     */
    public String minByTransport() throws CollectionIsEmptyException {
        if (flatsCollection.isEmpty()) throw new CollectionIsEmptyException();

        Flat minFlat = getFirst();
        for (Flat flat : flatsCollection) {
            if (flat.getTransport().compareTo(minFlat.getTransport()) < 0) {
                minFlat = flat;
            }
        }
        return minFlat.toString();
    }

    /**
     * @param viewToFilter View to filter by.
     * @return Information about count of valid flats or empty string, if there's no such flats.
     */
    public String viewCountInfo(View viewToFilter) {
        int count = 0;
        for (Flat flat : flatsCollection) {
            if(viewToFilter.compareTo(flat.getViewType()) < 0) {
                count +=1;
            }
        }
        return String.valueOf(count);
    }

    /**
     * Adds a new flat to collection.
     * @param flat A flat to add.
     */
    public void addToCollection(Flat flat) {
        flatsCollection.add(flat);
    }

    /**
     * Removes a new flat to collection.
     * @param flat A flat to remove.
     */
    public void removeFromCollection(Flat flat) {
        flatsCollection.remove(flat);
    }

    /**
     * Clears the collection.
     */
    public void clearCollection() {
        flatsCollection.clear();
    }

    /**
     * Generates next Id. It will be (the bigger one + 1).
     * @return Next Id.
     */
    public Long generateNextId() {
        if (flatsCollection.isEmpty()) return 1L;
        return flatsCollection.lastElement().getId() + 1;
    }

    /**
     * Generates next Index. It will be (the bigger one + 1).
     * @return Next Index.
     */
    public Long generateNextIndex() {
        if (flatsCollection.isEmpty()) return 0L;
        return flatsCollection.lastElement().getIndex() + 1;
    }

    /**
     * Saves the collection to file.
     */
    public void saveCollection() {
            fileManager.writeCollection(flatsCollection);
            lastSaveTime = LocalDateTime.now();
    }

    /**
     * Loads the collection from file.
     */
    private void loadCollection() {
        flatsCollection = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        if (flatsCollection.isEmpty()) return "Коллекция пуста!";

        String info = "";
        for (Flat flat : flatsCollection) {
            info += flat;
            if (flat != flatsCollection.lastElement()) info += "\n\n";
        }
        return info;
    }
}
