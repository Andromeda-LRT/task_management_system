package com.company.oop.taskmanagementsystem.core;

import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.*;
import com.company.oop.taskmanagementsystem.models.contracts.*;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagementsystem.models.enums.Size;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    private int nextId;
    private final List members = new ArrayList<>();

    public TaskManagementSystemRepositoryImpl() {
        nextId = 0;
    }

    public Feedback createFeedback(String title, String description, int rating ){
        return new FeedbackImpl(++nextId, title, description, rating);
    }

    public Bug createBug(String title, String description,
                         ArrayList<String> stepsToReproduce, Priority priority, Severity severity){
        return new BugImpl(++nextId, title, description, stepsToReproduce, priority, severity);
    }

    public Story createStory(String title, String description, Priority priority, Size size){
        return new StoryImpl(++nextId, title, description, priority, size);
    }

    public Member createMember(String name){
        return new MemberImpl(name);
    }

    public Board createBoard(String name){
        return new BoardImpl(name);
    }

}