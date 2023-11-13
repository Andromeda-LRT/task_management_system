package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Size;

public class StoryImpl implements Story {

    private Priority priority;
    private Size size;

        // ready for push
    @Override
    public Size getSize() {
        return this.size;
    }
}
