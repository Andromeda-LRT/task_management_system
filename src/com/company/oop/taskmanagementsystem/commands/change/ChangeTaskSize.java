package com.company.oop.taskmanagementsystem.commands.change;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ChangeTaskSize extends CommandImpl {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    public ChangeTaskSize(TaskManagementSystemRepository taskManagementSystemRepository){
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        ValidationHelpers.validateIntIsNotNegative(Integer.parseInt(parameters.get(1)),
                ValidationHelpers.NEGATIVE_NUMBER_ERR_MSG);
        String taskType = parameters.get(0);
        int taskId = Integer.parseInt(parameters.get(1));
        Size sizeToChangeTo = ParsingHelpers.tryParseEnum(parameters.get(2), Size.class);

        return executeChangeTaskSize(taskType, taskId, sizeToChangeTo);
    }

    public String executeChangeTaskSize(String taskType, int taskId, Size sizeToChangeTo ){

        switch (taskType.toUpperCase()){
            case Constants.STORY_CASE:
                Size latestStorySize = getTaskManagementSystemRepository().findStoryById(taskId)
                        .getSize();
                getTaskManagementSystemRepository().findStoryById(taskId).changeSize(sizeToChangeTo);
                return String.format(Constants.SIZE_CHANGED, taskType, taskId, latestStorySize, sizeToChangeTo);
        }

        throw new IllegalArgumentException(Constants.INVALID_TASK_TYPE_FOR_SIZE);
    }
}
