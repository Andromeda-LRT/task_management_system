package com.company.oop.taskmanagementsystem.commands.change;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ChangeTaskPriority extends CommandImpl {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    public ChangeTaskPriority(TaskManagementSystemRepository taskManagementSystemRepository){
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        ValidationHelpers.validateIntIsNotNegative(Integer.parseInt(parameters.get(1)),
                ValidationHelpers.NEGATIVE_NUMBER_ERR_MSG);
        String taskType = parameters.get(0);
        int taskId = Integer.parseInt(parameters.get(1));
        Priority priorityToChangeTo = ParsingHelpers.tryParseEnum(parameters.get(2), Priority.class);

        return executeChangeTaskPriority(taskType, taskId, priorityToChangeTo);
    }

    public String executeChangeTaskPriority(String taskType, int taskId, Priority priorityToChangeTo ){

        switch (taskType.toUpperCase()){
            case Constants.STORY_CASE:
               Priority latestStoryPriority = getTaskManagementSystemRepository().findStoryById(taskId)
                        .getPriority();
                getTaskManagementSystemRepository().findStoryById(taskId).changePriority(priorityToChangeTo);
                return String.format(Constants.PRIORITY_CHANGED, taskType, taskId, latestStoryPriority, priorityToChangeTo);
            case Constants.BUG_CASE:
                Priority latestBugPriority = getTaskManagementSystemRepository().findBugByID(taskId)
                        .getPriority();
                getTaskManagementSystemRepository().findBugByID(taskId).changePriority(priorityToChangeTo);
                return String.format(Constants.PRIORITY_CHANGED, taskType, taskId, latestBugPriority, priorityToChangeTo);
            default:
                throw new IllegalArgumentException(Constants.INVALID_TASK_TYPE_WHEN_REQUIRED_STORY_BUG);
        }
    }
}
