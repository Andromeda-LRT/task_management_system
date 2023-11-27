package com.company.oop.taskmanagementsystem.commands.change;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ChangeTaskSeverity extends CommandImpl {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    public ChangeTaskSeverity(TaskManagementSystemRepository taskManagementSystemRepository){
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        ValidationHelpers.validateIntIsNotNegative(Integer.parseInt(parameters.get(1)),
                ValidationHelpers.NEGATIVE_NUMBER_ERR_MSG);
        String taskType = parameters.get(0);
        int taskId = Integer.parseInt(parameters.get(1));
        Severity severityToChangeTo = ParsingHelpers.tryParseEnum(parameters.get(2), Severity.class);

        return executeChangeTaskSeverity(taskType, taskId, severityToChangeTo);
    }

    public String executeChangeTaskSeverity(String taskType, int taskId, Severity severityToChangeTo ){

        switch (taskType.toUpperCase()){
            case Constants.BUG_CASE:
                Severity latestBugSeverity = getTaskManagementSystemRepository().findBugByID(taskId)
                        .getSeverity();
                getTaskManagementSystemRepository().findBugByID(taskId).changeSeverity(severityToChangeTo);
                return String.format(Constants.SEVERITY_CHANGED, taskType, taskId, latestBugSeverity, severityToChangeTo);
            default:
                throw new IllegalArgumentException(Constants.INVALID_TASK_TYPE_FOR_SEVERITY);
        }
    }
}
