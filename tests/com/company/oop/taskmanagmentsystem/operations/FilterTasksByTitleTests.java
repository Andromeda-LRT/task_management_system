package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.FilterTasksByTitle;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FilterTasksByTitleTests {

    private final TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();
    private final Command command = new FilterTasksByTitle(repository);
    private List<String> parameters;

    @Test
    public void filterByTask_Should_FilterByTask_WhenArgumentsValid(){
        parameters = new ArrayList<>();
        parameters.add("test");
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(TestsConstants.FILTERED_TITLES, parameters.get(0))).append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());
        repository.createFeedback("testtesttest", TestsConstants.VALID_DESCRIPTION,
                TestsConstants.VALID_RATING);
        repository.createFeedback("testtesttest1", TestsConstants.VALID_DESCRIPTION,
                TestsConstants.VALID_RATING);
        repository.createFeedback("ttetstttetst", TestsConstants.VALID_DESCRIPTION,
                TestsConstants.VALID_RATING);
        repository.createFeedback("teeeestteeeest", TestsConstants.VALID_DESCRIPTION,
                TestsConstants.VALID_RATING);
        for (Task task : repository.filterTaskByTitle(parameters.get(0))) {
            sb.append(task.printMainInformation()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        Assertions.assertEquals(sb.toString(), command.execute(parameters));
    }

    @Test
    public void filterByTask_Should_ThrowAnException_When_MissingParameters(){
        parameters = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }

    @Test
    public void filterByTask_Should_ThrowAnException_When_FilteredTaskListIsEmpty(){
        parameters = new ArrayList<>();
        parameters.add("test");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
}
