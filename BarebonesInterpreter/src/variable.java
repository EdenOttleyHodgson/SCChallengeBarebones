public class variable {
    private String identifier;
    private int value;
    public variable(String theIdentifier) {
        identifier = theIdentifier;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int newValue) {
        value = newValue;
    }
    public String getIdentifier() {
        return identifier;
    }

}
