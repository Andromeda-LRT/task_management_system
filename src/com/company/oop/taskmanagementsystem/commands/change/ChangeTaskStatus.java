package com.company.oop.taskmanagementsystem.commands.change;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ChangeTaskStatus extends CommandImpl {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    public ChangeTaskStatus(TaskManagementSystemRepository taskManagementSystemRepository){
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        tryConcatinateEnum(parameters);
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        ValidationHelpers.validateIntIsNotNegative(Integer.parseInt(parameters.get(1)),
                ValidationHelpers.NEGATIVE_NUMBER_ERR_MSG);
        String taskType = parameters.get(0);
        int taskId = Integer.parseInt(parameters.get(1));
        Status statusToChangeTo = ParsingHelpers.tryParseEnum(parameters.get(2), Status.class);

        return executeChangeTaskStatus(taskType, taskId, statusToChangeTo);
    }

    public String executeChangeTaskStatus(String taskType, int taskId,Status statusToChangeTo ){

        Status latestStatus = getTaskManagementSystemRepository().findTaskById(taskId)
                        .getStatus();
        getTaskManagementSystemRepository().findTaskById(taskId).changeStatus(statusToChangeTo);

        return String.format(Constants.STATUS_CHANGED_MSG, taskType, taskId, latestStatus, statusToChangeTo);
    }

    public static List<String> tryConcatinateEnum(List<String> parameters){
        StringBuilder sb = new StringBuilder();
        if (parameters.size() == 4){
            sb.append(parameters.get(2)).append(" ").append(parameters.get(3));
            parameters.remove(3);
            parameters.remove(2);
            parameters.add(sb.toString());
            return parameters;
        }

        return parameters;
    }
}
