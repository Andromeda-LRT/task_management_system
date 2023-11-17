package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ShowAllMembers extends CommandImpl {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    public ShowAllMembers(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return print();
    }

    private String print() {
        if (getTaskManagementSystemRepository().getMembers().isEmpty()) {
            throw new IllegalArgumentException("There are no added members.");
        }
        StringBuilder output = new StringBuilder();
        output.append("---Members---").append(System.lineSeparator());
        for (Member member : getTaskManagementSystemRepository().getMembers()) {
            output.append(member.getName()).append(System.lineSeparator());
        }
        output.append(Constants.LINE_DIVISOR);
        return output.toString();
    }
}
