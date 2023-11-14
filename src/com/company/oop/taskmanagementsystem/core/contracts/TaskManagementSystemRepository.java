package com.company.oop.taskmanagementsystem.core.contracts;

import com.company.oop.taskmanagementsystem.models.contracts.*;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagementsystem.models.enums.Size;

import java.util.ArrayList;

public interface TaskManagementSystemRepository {
    Member createMember(String name);

    Story createStory( String title, String description, Priority priority, Size size);

    Bug createBug(String title, String description,
                  ArrayList<String> stepsToReproduce, Priority priority, Severity severity);

    Feedback createFeedback(String title, String description, int rating );

    Board createBoard(String name);
}
