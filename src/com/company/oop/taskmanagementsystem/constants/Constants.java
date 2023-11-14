package com.company.oop.taskmanagementsystem.constants;

public class Constants {
// ----------------------------------------------------------------------------------------------------
//                               FEEDBACK CONSTANTS
// ----------------------------------------------------------------------------------------------------
    public static final String FEEDBACK = "Feedback";
    public static final String NEGATIVE_RATING_ERROR = "The rating should be a positive number.";
    public static final String STATUS_IS_ALREADY_SET_TO_NEW =
            "Cannot revert status, because %s status is already set to New";
// ----------------------------------------------------------------------------------------------------
//                               BUG CONSTANTS
// ----------------------------------------------------------------------------------------------------
    public static final String EMPTY_STEPS_TO_REPRODUCE = "There are no steps to reproduce!";
    public static final String BUG = "Bug";
// ----------------------------------------------------------------------------------------------------
//                               STORY CONSTANTS
// ----------------------------------------------------------------------------------------------------
    public static final String STORY = "Story";
    public static final String STATUS_IS_ALREADY_SET_TO_NOT_DONE =
            "Cannot revert status, because %s status is already set to Not Done";
// ----------------------------------------------------------------------------------------------------
//                               COMMON CONSTANTS
// ----------------------------------------------------------------------------------------------------
    public static final String TASK_CREATED = "%s '%s' was created: ";
    public static final String NEW_ASSIGNEE = "%s was assigned to: %s";
    public static final String CHANGED_ASSIGNEE = "%s assignee was changed to: %s";
    public static final String STATUS_IS_ALREADY_SET_TO_DONE =
            "Cannot advance status, because %s status is already set to Done";
    public static final String STATUS_CHANGED = "%s status changed from %s to %s.";
    public static final String CANNOT_ADVANCE_STATUS =
            "Cannot advance status, because %s status is already set to %s.";
    public static final String CANNOT_REVERT_STATUS =
            "Cannot revert status, because %s status is already set to %s.";

//  PRIORITY
    public static final String PRIORITY_LOWERED = "%s priority lowered from %s to %s.";
    public static final String PRIORITY_INCREASED = "%s priority increased from %s to %s.";
    public static final String PRIORITY_IS_ALREADY_SET_TO_HIGH =
            "Cannot increase, because %s priority is already set to High";
    public static final String PRIORITY_IS_ALREADY_SET_TO_LOW =
            "Cannot lower, because %s priority is already set to Low";

//  SEVERITY

    public static final String SEVERITY_INCREASED =
            ("Bug severity increased from %s to %s.");
    public static final String SEVERITY_DECREASED =
            ("Bug severity decreased from %s to %s.");
    public static final String SEVERITY_ALREADY_AT_CRITICAL =
            ("Cannot increase, because bug severity is already set to Critical");
    public static final String SEVERITY_ALREADY_AT_MINOR =
            ("Cannot decrease, because bug severity is already set to Minor");

//  SIZE

    public static final String SIZE_INCREASED =
            ("Story size increased from %s to %s.");
    public static final String SIZE_DECREASED =
            ("Story size decreased from %s to %s.");
    public static final String SIZE_ALREADY_SET_TO_LARGE =
            "Cannot increase, because story size is already set to Large";
    public static final String SIZE_ALREADY_SET_TO_SMALL =
            "Cannot decrease, because story size is already set to Small";
}
