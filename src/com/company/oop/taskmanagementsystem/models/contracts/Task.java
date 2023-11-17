package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.models.LoggerImpl;
import com.company.oop.taskmanagementsystem.models.enums.Status;

import java.util.List;

public interface Task extends Identifiable, Printable, Statusable{
    String getTitle();

    String getDescription();

    Status getStatus();

    List<Comment> getComments();

    void addComment(Comment comment);

    List<LoggerImpl> getHistoryOfChanges();
}
