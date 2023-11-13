package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.models.contracts.Printable;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerImpl implements Printable {
    private static final String NOT_EMPTY_DESCRIPTION = "Description cannot be empty!";

    private final String description;

    public LoggerImpl(String description) {
        ValidationHelpers.validateEmptyString(description, NOT_EMPTY_DESCRIPTION);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String print() {
        return getDescription();
    }
}
