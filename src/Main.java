import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Задание 1.1. Обобщенная коробка.
        System.out.println("№ 1.1. Обобщенная коробка.");
        Box<Integer> integerBox = new Box<>();
        try {
            integerBox.put(3);
            printBoxContent(integerBox);
        } catch (BoxNotEmptyException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Задание 1.3. Сравнимое.
        System.out.println("\n№ 1.3. Сравнимое.");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        int firstValue = getValidIntInput(scanner);

        System.out.print("Введите второе число: ");
        int secondValue = getValidIntInput(scanner);

        CustomNumber number1 = new CustomNumber(firstValue);
        CustomNumber number2 = new CustomNumber(secondValue);

        int result = number1.compareTo(number2);
        System.out.println("\nФункция сравнимое вернуло число: " + result);

        if (result > 0) {
            System.out.println("Первое число больше второго.");
        } else if (result < 0) {
            System.out.println("Второе число больше первого.");
        } else {
            System.out.println("Оба числа равны.");
        }

        //Задание 2.3. Поиск максимума.
        System.out.println("\n№ 2.3. Поиск максимума.");
        // Ввод количества коробок
        List<Box<? extends Number>> boxes = new ArrayList<>();

        System.out.print("Введите количество коробок: ");
        int numberOfBoxes = getValidIntInput(scanner);

        // Заполнение коробок значениями
        for (int i = 0; i < numberOfBoxes; i++) {
            try {
                System.out.print("Введите значение для коробки " + (i + 1) + ": ");
                double value = getValidDoubleInput(scanner);

                Box<Double> box = new Box<>();
                box.put(value);
                boxes.add(box); // Добавляем коробку в список
            } catch (BoxNotEmptyException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        // Нахождение максимального значения
        double maxValue = findMaxValue(boxes);
        System.out.println("Максимальное значение: " + maxValue);

        // Задание 3.1. Функция.
        System.out.println("\n№ 3.1. Функция.");
        // Длины строк
        List<String> strings = Arrays.asList("qwerty", "asdfg", "zx");
        StringLengthMapp stringLengthMapper = new StringLengthMapp();
        List<Integer> lengths = stringLengthMapper.mapLengths(strings);
        System.out.println("\nИсходные строки: " + strings);
        System.out.println("Длины строк: " + lengths);

        // Пример 2: Абсолютные значения
        List<Integer> numbers = Arrays.asList(1, -3, 7);
        AbsolutevalueMapp absoluteValueMapper = new AbsolutevalueMapp();
        List<Integer> absoluteValues = absoluteValueMapper.mapAbsoluteValues(numbers);
        System.out.println("\nИсходные числа: " + numbers);
        System.out.println("Все значения стали положительными: " + absoluteValues);

        // Пример 3: Максимальные значения из массивов
        List<int[]> arrays = Arrays.asList(
                new int[]{1, 2, 3},
                new int[]{-1, -2, -3},
                new int[]{4, 5, 6}
        );
        MaxvalueMapp maxValueMapper = new MaxvalueMapp();
        List<Integer> maxValues = maxValueMapper.mapMaxValues(arrays);
        System.out.println("\nИсходные массивы: " + Arrays.deepToString(arrays.toArray()));
        System.out.println("Максимальные значения: " + maxValues);

        //Здаание 3.2. ФИЛЬТР.
        System.out.println("\nЗадание 3.2. Фильтр");
        // Пример 1: Фильтрация строк длиннее 3 символов
        List<String> stringss = Arrays.asList("qwerty", "asdfg", "zx");
        List<String> filteredStrings = filter(stringss, s -> s.length() < 3);
        System.out.println("\nСтроки короче 3 символов: " + filteredStrings);

        // Пример 2: Фильтрация положительных чисел
        List<Integer> numberss = Arrays.asList(1, -3, 7);
        List<Integer> positiveNumbers = filter(numberss, n -> n > 0);
        System.out.println("Положительные числа: " + positiveNumbers);

        // Пример 3: Фильтрация массивов с суммой элементов больше 10
        List<int[]> arrayss = Arrays.asList(
                new int[]{-1, -2, -3},
                new int[]{4, -5, -6},
                new int[]{0, 0, 0},
                new int[]{-7, -8, -9}
        );

        // Фильтруем массивы, оставляя только те, которые не содержат положительных элементов
        List<int[]> filteredArrays = filter(arrayss, arr -> !containsPositive(arr));

        System.out.println("Массивы без положительных элементов:");
        for (int[] arr : filteredArrays) {
            System.out.println(Arrays.toString(arr));
        }

        //Задание3.3. Сокращениею
        System.out.println("\nЗадание3.3. Сокращение.");
        runReducerTests();
        //Задание3.4. Коллекционирование
        System.out.println("\nЗадание3.4. Коллекционирование.");
        runCollectorTests();
    }

    public static void printBoxContent(Box<Integer> box) {
        Integer content = box.get();
        if (content != null) {
            System.out.println("Содержимое коробки: " + content);
        } else {
            System.out.println("Коробка пуста");
        }
    }

    public static double findMaxValue(List<Box<? extends Number>> boxes) {
        double max = Double.NEGATIVE_INFINITY; // Начальное значение для поиска максимума
        for (Box<? extends Number> box : boxes) {
            if (!box.isEmpty()) {
                double value = box.get().doubleValue(); // Получаем значение как double
                if (value > max) {
                    max = value; // Обновляем максимум
                }
            }
        }
        return max == Double.NEGATIVE_INFINITY ? 0 : max; // Возвращаем 0, если не найдено ни одного значения
    }

    private static int getValidIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Ошибка! Введите целое число: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double getValidDoubleInput(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Ошибка! Введите число с плавающей запятой: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public static <T, P> List<P> map(List<T> list, Function<T, P> function) {
        List<P> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }

    public static <T> List<T> filter(List<T> list, Filterr<T> criteria) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (criteria.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    private static boolean containsPositive(int[] array) {
        for (int num : array) {
            if (num > 0) {
                return true; // Если найден положительный элемент
            }
        }
        return false; // Положительных элементов нет
    }

    private static void runReducerTests() {
        Reducer reducer = new Reducer();

        // Пример 1: Объединение строк
        List<String> stringList = List.of("qwerty", "asdfg", "zx");
        String combinedString = reducer.reduce(stringList, (s1, s2) -> s1 + s2, "");
        System.out.println("\nРезультат объединения строк: " + combinedString);

        // Пример 2: Сумма целых чисел
        List<Integer> integerList = List.of(1, -3, 7);
        Integer totalSum = reducer.reduce(integerList, Integer::sum, 0);
        System.out.println("Общая сумма: " + totalSum);

    }

    private static void runCollectorTests() {
        Collector collector = new Collector();

        // Пример 1: Разделение чисел на положительные и отрицательные
        List<Integer> integerList = List.of(1, -3, 7);
        Supplier<Map<Boolean, List<Integer>>> numberMapSupplier = () -> new HashMap<>() {{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
        BiConsumer<Map<Boolean, List<Integer>>, Integer> numberAccumulator = (map, number) -> {
            map.get(number > 0).add(number);
        };
        Map<Boolean, List<Integer>> separatedNumbers = collector.collect(integerList, numberMapSupplier, numberAccumulator);
        System.out.println("\nРезультат разделения чисел: " + separatedNumbers);

        // Пример 2: Группировка строк по длине
        List<String> stringList = List.of("qwerty", "asdfg", "zx", "qw");
        Supplier<Map<Integer, List<String>>> lengthMapSupplier = HashMap::new;
        BiConsumer<Map<Integer, List<String>>, String> stringAccumulator = (map, str) -> {
            map.computeIfAbsent(str.length(), k -> new ArrayList<>()).add(str);
        };
        Map<Integer, List<String>> groupedStrings = collector.collect(stringList, lengthMapSupplier, stringAccumulator);
        System.out.println("Результат группировки строк: " + groupedStrings);

        // Пример 3: Уникальные строки
        List<String> stringsWithDuplicates = List.of("qwerty", "asdfg", "qwerty", "qw");
        Supplier<Set<String>> uniqueSetSupplier = HashSet::new;
        BiConsumer<Set<String>, String> uniqueAccumulator = Set::add;
        Set<String> uniqueStringsResult = collector.collect(stringsWithDuplicates, uniqueSetSupplier, uniqueAccumulator);
        System.out.println("Уникальные строки: " + uniqueStringsResult);
    }
}