package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.models.MemberImpl;
import com.company.oop.taskmanagementsystem.models.enums.Severity;

import java.util.List;

public interface Bug extends Identifiable, Printable, Prioritizeable {
   List<String> getStepsToReproduce();

   Severity getSeverity();
   void increaseSeverity();
   void lowerSeverity();

   MemberImpl getAssignee();
}
