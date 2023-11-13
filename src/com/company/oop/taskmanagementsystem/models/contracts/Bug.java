package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.models.Member;
import com.company.oop.taskmanagementsystem.models.enums.Priority;

import javax.print.attribute.standard.Severity;
import java.util.List;

public interface Bug extends Printable {
   List<String> getStepsToReproduce();

   Priority getPriority();

   Severity getSeverity();

   Member getAssignee();
}
