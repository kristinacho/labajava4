public class CustomNumber implements MyComparable<CustomNumber> {
    private int value;

    public CustomNumber(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(CustomNumber otherNumber) {
        return Integer.compare(this.value, otherNumber.getValue());
    }

    @Override
    public String toString() {
        return "Number: " + value;
    }
}