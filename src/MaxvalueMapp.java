import java.util.List;
import java.util.Arrays;

public class MaxvalueMapp {
    public List<Integer> mapMaxValues(List<int[]> arrays) {
        return Main.map(arrays, this::getMax);
    }

    private Integer getMax(int[] array) {
        return Arrays.stream(array).max().orElseThrow();
    }

    @Override
    public String toString() {
        return "Находит максимальное значение в каждом массиве";
    }
}
