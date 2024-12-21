package ru.abibik.utils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для выполнения сортировки строк.
 */
public class Sorter {
    /**
     * Сортирует список строк в соответствии с заданным типом сортировки.
     *
     * @param lines     список строк для сортировки.
     * @param sortType  тип сортировки (alphabetical, length, word).
     * @param wordIndex индекс слова для сортировки (для типа 'word').
     * @return отсортированный список строк.
     * @throws IllegalArgumentException если тип сортировки недействителен или индекс слова некорректен.
     */
    public static List<String> sort(List<String> lines, String sortType, int wordIndex) {
        // Создаем новый список, чтобы не изменять исходный
        List<String> distinctLines = lines.stream().distinct().toList();
        // Добавлена проверка на выход за границы массива слов
        return switch (sortType) {
            case "alphabetical" -> distinctLines.stream().sorted().collect(Collectors.toList());
            case "length" ->
                    distinctLines.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
            case "word" -> {
                if (wordIndex < 0) {
                    throw new IllegalArgumentException("Индекс слова должен быть указан для сортировки по слову.");
                }
                yield distinctLines.stream().sorted(Comparator.comparing(s -> {
                    String[] words = s.split("\\s+");
                    // Добавлена проверка на выход за границы массива слов
                    return wordIndex < words.length ? words[wordIndex] : "";
                })).collect(Collectors.toList());
            }
            default -> throw new IllegalArgumentException("Неверный тип сортировки: " + sortType);
        };
    }
}
