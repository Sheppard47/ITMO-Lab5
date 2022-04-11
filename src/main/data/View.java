package data;

/**
 * Enumeration with flat view constants.
 */
public enum View {
    YARD,
    BAD,
    NORMAL,
    GOOD;

    /**
     * Generates a beautiful list of enum string values.
     * @return String with all enum values splitted by comma.
     */
    public static String nameList() {
        String nameList = "";
        for (View viewType : values()) {
            nameList += viewType.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
