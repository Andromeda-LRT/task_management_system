package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.ListAllBugs;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListAllBugsTests {

    private final TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();

    @Test
    void execute_Should_ListAllBugs_When_NoParametersProvided() {
        ListAllBugs listAllBugsCommand = new ListAllBugs(repository);
        List<String> parameters = new ArrayList<>();
        List<String> steps = new ArrayList<>();
        steps.add("Step1");
        steps.add("Step2");

        repository.createBug(
                "ValidBUGtitle",
                "Some description",
                steps,
                Priority.LOW,
                Severity.MAJOR);

        String result = listAllBugsCommand.execute(parameters);

        Assertions.assertTrue(result.contains("---ALL BUGS---"));
        Assertions.assertTrue(result.contains("ValidBUGtitle"));
    }

    @Test
    void execute_Should_ReturnNoBugsMessage_When_NoBugsInRepository() {
        ListAllBugs listAllBugsCommand = new ListAllBugs(repository);
        List<String> parameters = new ArrayList<>();

        String result = listAllBugsCommand.execute(parameters);

        Assertions.assertTrue(result.contains("No bugs were found!"));
    }

    @Test
    void execute_Should_ListAllBugs_When_BugsInRepository() {
        ListAllBugs listAllBugsCommand = new ListAllBugs(repository);
        List<String> parameters = new ArrayList<>();

        List<String> steps = new ArrayList<>();
        steps.add("Step1");
        steps.add("Step2");

        repository.createBug(
                "ValidBUGtitle_1",
                "Some description",
                steps,
                Priority.LOW,
                Severity.MAJOR);
        repository.createBug(
                "ValidBUGtitle_2",
                "Some description",
                steps,
                Priority.LOW,
                Severity.MAJOR);


        String result = listAllBugsCommand.execute(parameters);

        Assertions.assertTrue(result.contains("---ALL BUGS---"));
        Assertions.assertTrue(result.contains("ValidBUGtitle_1"));
        Assertions.assertTrue(result.contains("ValidBUGtitle_2"));
    }
}
