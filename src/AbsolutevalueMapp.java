import java.util.List;

public class AbsolutevalueMapp {
    public List<Integer> mapAbsoluteValues(List<Integer> numbers) {
        return Main.map(numbers, Math::abs);
    }

    @Override
    public String toString() {
        return "Преобразует числа в их абсолютные значения";
    }
}
