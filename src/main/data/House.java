package data;

/**
 * House with flats.
 */
public class House {
    private String name;
    private long year;
    private  long numberOfFloors;

    public House(String name, long year, long numberOfFloors) {
        this.name = name;
        this.year = year;
        this.numberOfFloors = numberOfFloors;
    }

    /**
     * @return Name of the house.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Number of flats in the house.
     */
    public long getyear() {
        return year;
    }

    /**
     * @return Number of flats in the house.
     */
    public long getNumberOfFloors() {
        return numberOfFloors;
    }

    @Override
    public String toString() {
        return name + " (год дома " + year + ", кол-во этажей " + numberOfFloors +")";
    }

    @Override
    public int hashCode() {
        return name.hashCode() + (int) year + (int) numberOfFloors;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof House) {
            House houseObj = (House) obj;
            return name.equals(houseObj.getName()) && (year == houseObj.getyear()) && (numberOfFloors == houseObj.getNumberOfFloors());
        }
        return false;
    }
}
