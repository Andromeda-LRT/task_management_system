package com.company.oop.taskmanagementsystem.commands.contracts;

import java.util.List;

public interface Command {
    String execute(List<String> parameters);
}
