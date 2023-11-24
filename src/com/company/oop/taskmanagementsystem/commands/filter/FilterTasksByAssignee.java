package com.company.oop.taskmanagementsystem.commands.filter;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class FilterTasksByAssignee extends CommandImpl {

    protected FilterTasksByAssignee (TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        return null;
    }
}
