package ru.abibik.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс для выполнения сортировки строк.
 */
public class FileWriter {
    /**
     * Записывает отсортированные строки в файл, добавляя количество повторений.
     *
     * @param filePath    путь к выходному файлу.
     * @param sortedLines отсортированный список строк.
     * @param lineCounts  Map с количеством повторений строк.
     * @throws IOException если произошла ошибка при записи в файл.
     */
    public static void writeLines(String filePath, List<String> sortedLines, Map<String, Long> lineCounts) throws IOException {
        Path path = Paths.get(filePath);
        // Проверяем, существует ли родительская директория, и если нет, пытаемся создать
        if (path.getParent() != null && !Files.exists(path.getParent())) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                throw new IOException("Не удалось создать директорию для выходного файла: " + e.getMessage());
            }
        }
        // Проверяем, есть ли права на запись в файл
        if (Files.exists(path) && !Files.isWritable(path)) {
            throw new IOException("Нет прав на запись в выходной файл: " + filePath);
        }

        List<String> outputLines = sortedLines.stream()
                .map(line -> line + " " + lineCounts.getOrDefault(line, 0L))
                .collect(Collectors.toList());
        Files.write(path, outputLines);
    }
}
