package com.company.oop.taskmanagementsystem.commands.sort;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.TaskImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.Collections;
import java.util.List;

public class SortTasksByTitle extends CommandImpl {
    private static final String SORT_TASKS_BY_TITLE = "---SORT TASKS BY TITLE---";
    private static final String N0_SORT_TASKS_BY_TITLE = "There is no tasks added!";
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public SortTasksByTitle(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        return listTasksSortByTitle();
    }

    private String listTasksSortByTitle() {
        if(getTaskManagementSystemRepository().getTasks().isEmpty()){
            throw new IllegalArgumentException(N0_SORT_TASKS_BY_TITLE);
        }
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(SORT_TASKS_BY_TITLE).append(System.lineSeparator());
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        List<Task> tasks = getTaskManagementSystemRepository().getTasks();

        Collections.sort(tasks);

        for (Task task : tasks){
            stringBuilder.append(task.printMainInformation()).append(System.lineSeparator());
        }
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
