package com.company.oop.taskmanagementsystem.core.contracts;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.commands.enums.CommandType;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;

public interface CommandFactory {
    Command createCommandFromCommandName(String commandTypeAsString,
                                         TaskManagementSystemRepository taskManagementSystemRepository);
}
