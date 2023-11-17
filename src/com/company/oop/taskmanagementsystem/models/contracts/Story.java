package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.models.MemberImpl;
import com.company.oop.taskmanagementsystem.models.enums.Size;

public interface Story extends Prioritizeable, Task {
     Size getSize();
     Member getAssignee();
     void increaseSize();
     void decreaseSize();
     void setAssignee(Member assignee);
}
