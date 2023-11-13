package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.models.Member;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;

import java.util.List;

public interface Bug extends Identifiable, Printable, Prioritizeable {
   List<String> getStepsToReproduce();

   Severity getSeverity();
   void increaseSeverity();
   void lowerSeverity();

   Member getAssignee();
}
