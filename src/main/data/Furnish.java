package data;

/**
 * Enumeration with flat category constants.
 */
public enum Furnish {
    DESIGNER,
    BAD,
    LITTLE;
    /**
     * Generates a beautiful list of enum string values.
     * @return String with all enum values splitted by comma.
     */
    public static String nameList() {
        String nameList = "";
        for (Furnish category : values()) {
            nameList += category.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
