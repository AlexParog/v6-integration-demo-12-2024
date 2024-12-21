package ru.abibik.utils;

/**
 * Класс-обертка для хранения аргументов командной строки.
 */
public record Arguments(String inputFilePath, String outputFilePath, String sortType, int wordIndex) {
}
