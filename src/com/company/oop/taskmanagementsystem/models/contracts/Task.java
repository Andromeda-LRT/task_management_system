package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.models.LoggerImpl;
import com.company.oop.taskmanagementsystem.models.enums.Status;

import java.util.List;

public interface Task extends Identifiable, Printable{
    String getTitle();

    String getDescription();

    Status getStatus();
    List<Comment> getComments();
    // TODO create list of Logger class

    List<LoggerImpl> getHistoryOfChanges();

    // TODO add method for list of comments
}
