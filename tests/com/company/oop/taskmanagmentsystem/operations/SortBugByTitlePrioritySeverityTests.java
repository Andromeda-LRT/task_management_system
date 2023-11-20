package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.SortBugByTitlePrioritySeverity;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortBugByTitlePrioritySeverityTests {

    private TaskManagementSystemRepository repository;
    private Command command;
    private List<String> parameters;
    private StringBuilder sb;
    private List<Bug> bugsForTest;

    @BeforeEach
    public void initSortBugByTitlePrioritySeverityCommand() {

        repository = new TaskManagementSystemRepositoryImpl();
        command = new SortBugByTitlePrioritySeverity(repository);
        parameters = new ArrayList<>();
        sb = new StringBuilder();

        repository.createBug("aaaaaaaaaa", TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE, Priority.HIGH, Severity.CRITICAL);
        repository.createBug("bbbbbbbbbb", TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE, Priority.LOW, Severity.MINOR);
        repository.createBug("cccccccccc", TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE, Priority.MEDIUM, Severity.CRITICAL);
        repository.createBug("dddddddddd", TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE, Priority.HIGH, Severity.MAJOR);
        repository.createBug("eeeeeeeeee", TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE, Priority.MEDIUM, Severity.MAJOR);
        repository.createBug("ffffffffff", TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE, Priority.HIGH, Severity.MAJOR);
        repository.createBug("gggggggggg", TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE, Priority.LOW, Severity.MAJOR);
        repository.createBug("hhhhhhhhhh", TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE, Priority.HIGH, Severity.MAJOR);

        bugsForTest = repository.findAllBugsInTasks();
    }

    @Test
    public void SortBugByTitlePrioritySeverity_Should_SortByTitle_When_ArgumentsValid() {
        parameters.add("Title");
        sb.append(String.format(TestsConstants.BUG_SORTED_BY_GIVEN_PARAMETER, parameters.get(0)))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        Collections.sort(bugsForTest);

        for (Bug bug : bugsForTest) {
            sb.append(bug.printMainInformation()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        Assertions.assertEquals(sb.toString(), command.execute(parameters));
    }

    @Test
    public void SortBugByTitlePrioritySeverity_Should_SortByPriority_When_ArgumentsValid() {
        parameters.add("Priority");
        sb.append(String.format(TestsConstants.BUG_SORTED_BY_GIVEN_PARAMETER, parameters.get(0)))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());
        Comparator<Bug> compareByPrio = new Comparator<>() {
            @Override
            public int compare(Bug o1, Bug o2) {
                return o1.getPriority().compareTo(o2.getPriority());
            }
        };

        bugsForTest.sort(compareByPrio.reversed());

        for (Bug bug : bugsForTest) {
            sb.append(bug.printMainInformation()).append(System.lineSeparator());
            sb.append(String.format("%s: ", parameters.get(0).toUpperCase()))
                    .append(bug.getPriority()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        Assertions.assertEquals(sb.toString(), command.execute(parameters));
    }

    @Test
    public void SortBugByTitlePrioritySeverity_ShouldBySeverity_Sort_When_ArgumentsValid() {
        parameters.add("Severity");
        sb.append(String.format(TestsConstants.BUG_SORTED_BY_GIVEN_PARAMETER, parameters.get(0)))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());
        Comparator<Bug> compareBySeverity = new Comparator<>() {
            @Override
            public int compare(Bug o1, Bug o2) {
                return o1.getSeverity().compareTo(o2.getSeverity());
            }
        };

        bugsForTest.sort(compareBySeverity);

        for (Bug bug : bugsForTest) {
            sb.append(bug.printMainInformation()).append(System.lineSeparator());
            sb.append(String.format("%s: ", parameters.get(0).toUpperCase()))
                    .append(bug.getSeverity()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        Assertions.assertEquals(sb.toString(), command.execute(parameters));
    }
    @Test
    public void SortBugByTitlePrioritySeverity_Should_Throw_AnException_When_MissingParameters(){

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }

    @Test
    public void SortBugByTitlePrioritySeverity_Should_Throw_AnException_When_ThereAreNoBugs(){
        parameters.add("Severity");
        repository = new TaskManagementSystemRepositoryImpl();
        command = new SortBugByTitlePrioritySeverity(repository);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
    @Test
    public void SortBugByTitlePrioritySeverity_Should_Throw_AnException_When_InvalidOperation(){
        parameters.add("Size");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
}
