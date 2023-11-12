package com.company.oop.taskmanagementsystem.utils;

public class ValidationHelpers {
    public static void validateIntRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(message);
        }
    }
}
