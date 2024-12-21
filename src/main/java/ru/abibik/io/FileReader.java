package ru.abibik.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Класс для чтения данных из файла.
 */
public class FileReader {
    /**
     * Читает все строки из файла.
     *
     * @param filePath путь к файлу.
     * @return список строк из файла.
     * @throws IOException если произошла ошибка при чтении файла.
     */
    public static List<String> readLines(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        // Проверяем, существует ли файл
        if (!Files.exists(path)) {
            throw new IOException("Входной файл не найден: " + filePath);
        }
        // Проверяем, является ли файл обычным файлом (не директорией и т.п.)
        if (!Files.isRegularFile(path)) {
            throw new IOException("Указанный путь не является обычным файлом: " + filePath);
        }
        // Проверяем, доступен ли файл для чтения
        if (!Files.isReadable(path)) {
            throw new IOException("Нет прав на чтение входного файла: " + filePath);
        }
        List<String> lines = Files.readAllLines(path);
        // Проверяем, не пустой ли файл
        if (lines.isEmpty()) {
            System.out.println("Внимание: Входной файл пуст.");
        }
        return lines;
    }
}
