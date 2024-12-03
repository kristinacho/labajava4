import java.util.List;

public class StringLengthMapp{
    public List<Integer> mapLengths(List<String> strings) {
        return Main.map(strings, String::length);
    }

    @Override
    public String toString() {
        return "Преобразует строки в их длины";
    }
}
