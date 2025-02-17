package com.company.oop.taskmanagementsystem.utils;


import java.util.Arrays;
import java.util.List;

public class ParsingHelpers {
    public static final String NO_SUCH_ENUM = "There is no %s in %s.";

    public static <E extends Enum<E>> E tryParseEnum(String valueToParse, Class<E> type) {
        try {
           return Enum.valueOf(type, valueToParse.replace(" ", "_").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(NO_SUCH_ENUM, valueToParse, type.getSimpleName()));
        }
    }

    public static int tryParseInt(String valueToParse, String errorMessage) {
        try {
            return Integer.parseInt(valueToParse);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
