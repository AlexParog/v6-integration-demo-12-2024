package ru.abibik.utils;

/**
 * Класс для парсинга аргументов командной строки.
 */
public class ArgumentParser {

    private String inputFilePath;
    private String outputFilePath;
    private String sortType;
    private int wordIndex = -1;

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public String getSortType() {
        return sortType;
    }

    public int getWordIndex() {
        return wordIndex;
    }

    public ArgumentParser(String inputFilePath, String outputFilePath, String sortType, int wordIndex) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.sortType = sortType;
        this.wordIndex = wordIndex;
    }

    public static Arguments parse(String[] args) {
        if (args.length < 3 || (args[2].equals("word") && args.length < 4)) {
            return null;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];
        String sortType = args[2];
        int wordIndex = -1;

        if (sortType.equals("word")) {
            try {
                wordIndex = Integer.parseInt(args[3]) - 1; // Преобразуем в 0-based index
                if (wordIndex < 0) {
                    throw new IllegalArgumentException("Индекс слова должен быть положительным числом.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Неверный формат индекса слова.");
            }
        }

        if (!sortType.equals("alphabetical") && !sortType.equals("length") && !sortType.equals("word")) {
            throw new IllegalArgumentException("Неверный тип сортировки.");
        }

        return new Arguments(inputFilePath, outputFilePath, sortType, wordIndex);
    }

    public static void printUsage() {
        System.out.println("Использование: java TextSorterApp <input_file_path> <output_file_path> <sort_type> [<word_index>]");
        System.out.println("Типы сортировки:");
        System.out.println("  alphabetical - по алфавиту");
        System.out.println("  length - по количеству символов");
        System.out.println("  word <номер_слова> - по слову с заданным номером (начиная с 1)");
        System.out.println("Пример: java TextSorterApp input.txt output.txt alphabetical");
        System.out.println("Пример: java TextSorterApp input.txt output.txt word 2");
    }
}
