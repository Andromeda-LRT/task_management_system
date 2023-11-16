package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class ChangeStatusOfFeedback extends CommandImpl {

    public ChangeStatusOfFeedback(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        return null;
    }
}
