package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.models.enums.Priority;

public interface Prioritizeable {
    Priority getPriority();
    void increasePriority();
    void lowerPriority();
    void changePriority(Priority priorityToChangeTo);
}
