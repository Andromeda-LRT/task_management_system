package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;

import java.util.List;

public abstract class CommandImpl implements Command {

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    protected CommandImpl(TaskManagementSystemRepository taskManagementSystemRepository){
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }
    protected TaskManagementSystemRepository getTaskManagementSystemRepository(){
        return this.taskManagementSystemRepository;
    }

//    @Override
//    public String execute(List<String> parameters) {
//        return null;
//    }
}
