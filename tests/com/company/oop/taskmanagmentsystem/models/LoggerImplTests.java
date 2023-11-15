package com.company.oop.taskmanagmentsystem.models;

import com.company.oop.taskmanagementsystem.models.LoggerImpl;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoggerImplTests {
    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            new LoggerImpl(TestsConstants.EMPTY_DESCRIPTION));
    }

    @Test
    public void constructor_Should_CreateLoggerWithValidDescription() {
        LoggerImpl logger = new LoggerImpl(TestsConstants.VALID_DESCRIPTION);

        Assertions.assertEquals(TestsConstants.VALID_DESCRIPTION, logger.getDescription());
    }

    @Test
    public void getDescription_Should_ReturnDescription() {
        LoggerImpl logger = new LoggerImpl(TestsConstants.VALID_DESCRIPTION);

        Assertions.assertEquals(TestsConstants.VALID_DESCRIPTION, logger.getDescription());
    }

    @Test
    public void print_Should_ReturnDescription() {
        LoggerImpl logger = new LoggerImpl(TestsConstants.VALID_DESCRIPTION);

        Assertions.assertEquals(TestsConstants.VALID_DESCRIPTION, logger.print());
    }
}
