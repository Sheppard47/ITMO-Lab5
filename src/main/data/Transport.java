package data;

/**
 * Enumeration with flat view constants.
 */
public enum Transport {
    NONE,
    LITTLE,
    ENOUGH;

    /**
     * Generates a beautiful list of enum string values.
     * @return String with all enum values splitted by comma.
     */
    public static String nameList() {
        String nameList = "";
        for (Transport transport : values()) {
            nameList += transport.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
