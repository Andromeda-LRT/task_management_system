package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.ListAllTasks;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListAllTasksTests {
    private final TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();

    @Test
    void execute_Should_ListAllTasks_When_NoParametersProvided() {
        ListAllTasks listAllTasksCommand = new ListAllTasks(repository);
        List<String> parameters = new ArrayList<>();

        List<String> steps = new ArrayList<>();
        steps.add("Step1");
        steps.add("Step2");

        repository.createStory(
                "xxxxxxxxxx",
                "Valid descritpion of story",
                Priority.LOW,
                Size.MEDIUM
        );
        repository.createBug(
                "ValidBUGtitle_2",
                "Some description",
                steps,
                Priority.LOW,
                Severity.MAJOR);


        String result = listAllTasksCommand.execute(parameters);

        Assertions.assertTrue(result.contains("---ALL TASKS---"));
        Assertions.assertTrue(result.contains("xxxxxxxxxx"));
        Assertions.assertTrue(result.contains("ValidBUGtitle_2"));
    }

    @Test
    void execute_Should_ReturnNoTasksMessage_When_NoTasksInRepository() {
        repository.createMember("IvanIvan");

        ListAllTasks listAllTasksCommand = new ListAllTasks(repository);
        List<String> parameters = new ArrayList<>();

        String result = listAllTasksCommand.execute(parameters);

        Assertions.assertTrue(result.contains("No tasks were found!"));
    }
}
