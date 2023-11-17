package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.models.MemberImpl;
import com.company.oop.taskmanagementsystem.models.enums.Severity;

import java.util.List;

public interface Bug extends Identifiable, Printable, Prioritizeable, Task {
   List<String> getStepsToReproduce();

   Severity getSeverity();

   void increaseSeverity();

   void lowerSeverity();

   Member getAssignee();

   void setAssignee(Member assignee);
}
