package ru.abibik;


import ru.abibik.io.FileReader;
import ru.abibik.io.FileWriter;
import ru.abibik.utils.ArgumentParser;
import ru.abibik.utils.Arguments;
import ru.abibik.utils.LineCounter;
import ru.abibik.utils.Sorter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TextSorterApp {
    public static void main(String[] args) {
        try {
            Arguments arguments = ArgumentParser.parse(args);
            if (arguments == null) {
                ArgumentParser.printUsage();
                return;
            }

            List<String> lines = FileReader.readLines(arguments.inputFilePath());
            Map<String, Long> lineCounts = LineCounter.countOccurrences(lines);
            List<String> sortedLines = Sorter.sort(lines, arguments.sortType(), arguments.wordIndex());
            FileWriter.writeLines(arguments.outputFilePath(), sortedLines, lineCounts);

            System.out.println("Файл успешно отсортирован и сохранен в: " + arguments.outputFilePath());

        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка в аргументах: " + e.getMessage());
            ArgumentParser.printUsage();
        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }
}
