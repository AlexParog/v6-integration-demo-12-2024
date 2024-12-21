package ru.abibik.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс для подсчета количества повторений строк.
 */
public class LineCounter {
    /**
     * Подсчитывает количество повторений каждой строки в списке.
     *
     * @param lines список строк.
     * @return Map, где ключ - строка, значение - количество повторений.
     */
    public static Map<String, Long> countOccurrences(List<String> lines) {
        return lines.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    }
}