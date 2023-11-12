package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.models.enums.Status;

public interface Task extends Identifiable{
    String getTitle();

    String getDescription();

    Status getStatus();

    // TODO create list of Logger class
}
