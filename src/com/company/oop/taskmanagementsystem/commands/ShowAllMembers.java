package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ShowAllMembers implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowAllMembers(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return print();
    }

    private String print() {
        if (taskManagementSystemRepository.getMembers().isEmpty()) {
            return "There are no added members.";
        }
        StringBuilder output = new StringBuilder();
        output.append("---Members---");
        for (Member member : taskManagementSystemRepository.getMembers()) {
            output.append(member.getName()).append(System.lineSeparator());
        }
        output.append(Constants.LINE_DIVISOR);
        return output.toString();
    }
}
