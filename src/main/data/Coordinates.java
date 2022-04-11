package data;

/**
 * X-Y coordinates.
 */
public class Coordinates {
    private Long x;
    private double y;

    public Coordinates(Long x, Float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return X-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * @return Y-coordinate.
     */
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "X:" + x + " Y:" + y;
    }
    
    @Override
    public int hashCode() {
        return (int) y + x.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinates) {
            Coordinates coordinatesObj = (Coordinates) obj;
            return (x == coordinatesObj.getX()) && y == (coordinatesObj.getY());
        }
        return false;
    }
}
