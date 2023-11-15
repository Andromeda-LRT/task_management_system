package com.company.oop.taskmanagmentsystem.core;

import com.company.oop.taskmanagementsystem.core.TaskManagementSystemEngineImpl;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;
// todo to discuss implementation of EngineImpl tests
public class TaskManagementSystemEngineImplTests {
    private static final String OPERATION_CANNOT_BE_EMPTY_ERROR = "Operation cannot be empty";
    private static final String SHUT_DOWN_COMMAND = "Exit";
    private TaskManagementSystemEngineImpl taskManagementSystemEngine;
    private String input = " ";

    @BeforeEach
    public void initEngine(){
        taskManagementSystemEngine = new TaskManagementSystemEngineImpl();

    }

    @Test
    public void Start_Should_PrintOperationCannotBeEmpty_When_InputIsBlank(){
        Assertions.assertEquals(OPERATION_CANNOT_BE_EMPTY_ERROR, taskManagementSystemEngine);
    }
}
