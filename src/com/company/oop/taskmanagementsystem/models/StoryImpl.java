package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.contracts.Comment;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Size;

import java.util.ArrayList;
import java.util.List;

public class StoryImpl extends TaskImpl implements Story {
    public static final String STATUS_CHANGED_FROM_NOT_DONE_TO_IN_PROGRESS =
            "Story status changed from Not Done to In Progress";
    public static final String STATUS_CHANGED_FROM_IN_PROGRESS_TO_DONE =
            "Story status changed from In Progress to Done";
    public static final String STATUS_IS_ALREADY_SET_TO_DONE =
            "Cannot advance status, because Story status is already set to Done";
    public static final String STATUS_CHANGED_FROM_DONE_TO_IN_PROGRESS =
            "Story status changed from Done to In Progress";
    public static final String STATUS_CHANGED_FROM_IN_PROGRESS_TO_NOT_DONE =
            "Story status changed from In Progress to Not Done";
    public static final String STATUS_IS_ALREADY_SET_TO_NOT_DONE =
            "Cannot revert status, because Story status is already set to Not Done";
    public static final String STORY_FOR_CONSTANTS = "Story";

    private Priority priority;
    private Size size;
    public StoryImpl(int id, String title, String description, Status status, Priority priority, Size size){
        super(id, title, description, status);
        setPriority(priority);
        setSize(size);
    }
    @Override
    public Size getSize() {
        return this.size;
    }
}
