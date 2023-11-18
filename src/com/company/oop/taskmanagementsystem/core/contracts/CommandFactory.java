package com.company.oop.taskmanagementsystem.core.contracts;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;


public interface CommandFactory {
    Command createCommandFromCommandName(String commandTypeAsString,
                                         TaskManagementSystemRepository taskManagementSystemRepository);
}
