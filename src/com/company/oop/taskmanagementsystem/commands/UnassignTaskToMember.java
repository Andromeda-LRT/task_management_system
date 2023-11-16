package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class UnassignTaskToMember extends CommandImpl{
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String ID_ERROR = "The first parameter should be a number.";
    private static final String TASK_UNASSIGNED = "Task with id %d was unassigned from %s";
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public UnassignTaskToMember(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), ID_ERROR);
        String name = parameters.get(1);
        unAssignTaskToMember(id, name);
        return String.format(TASK_UNASSIGNED, id, name);
    }

    private void unAssignTaskToMember(int id, String name){
        Member member = taskManagementSystemRepository.findMemberByName(name);
        member.unAssignTask(taskManagementSystemRepository.findTaskById(id));
    }
}
