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
    public static final String ID_ERROR_MESSAGE = "The id of the bug should be a number";
// ----------------------------------------------------------------------------------------------------
//                               STORY CONSTANTS
// ----------------------------------------------------------------------------------------------------
    public static final String STORY = "Story";
    public static final String STATUS_IS_ALREADY_SET_TO_NOT_DONE =
            "Cannot revert status, because %s status is already set to Not Done";
    public static final String STATUS_IS_ALREADY_SET_TO_NOT_DONE_WITH_ID =
            "Cannot revert status, because %s with ID %d's status is already set to Not Done";

    public static final String STORY_STATUS_CHANGED_MSG = "Story with ID %d had it status changed from %s to %s";
    public static final String STORY_PRIORITY_CHANGED_MSG = "Story with ID %d had it priority changed from %s to %s";
    public static final String STORY_SIZE_CHANGED_MSG = "Story with ID %d had it size changed from %s to %s";
    public static final String ID_DOES_NOT_BELONG_TO_STORY = "The provided id should be of a story.";

    // ----------------------------------------------------------------------------------------------------
//                               COMMON CONSTANTS
// ----------------------------------------------------------------------------------------------------
    public static final String TASK_CREATED = "%s '%s' was created: ";
    public static final String NEW_ASSIGNEE = "%s was assigned to: %s";
    public static final String CHANGED_ASSIGNEE = "%s assignee was changed to: %s";
    public static final String STATUS_IS_ALREADY_SET_TO_DONE =
            "Cannot advance status, because %s status is already set to Done.";
    public static final String STATUS_IS_ALREADY_SET_TO_DONE_WITH_ID =
            "Cannot advance status, because %s with ID %d's status is already set to Done.";
    public static final String STATUS_CHANGED = "%s status changed from %s to %s.";
    public static final String CANNOT_ADVANCE_STATUS =
            "Cannot advance status, because %s status is already set to %s.";
    public static final String CANNOT_REVERT_STATUS =
            "Cannot revert status, because %s status is already set to %s.";
    public static final String LINE_DIVISOR = "---------------------";
    public static final String COMMENT_ADDED = "A comment was added by ";
    public static final String MEMBERS = "Members:";
    public static final String BOARDS = "Boards:";
    public static final String TEAM_ADD_METHOD = "%s %s was added to Team %s successfully.";
    public static final String NOT_UNIQUE = "The name %s is not unique in the system.";
    public static final String ALL_TEAMS = "ALL TEAMS";

//  PRIORITY
    public static final String PRIORITY_LOWERED = "%s priority lowered from %s to %s.";
    public static final String PRIORITY_INCREASED = "%s priority increased from %s to %s.";
    public static final String PRIORITY_IS_ALREADY_SET_TO_HIGH =
            "Cannot increase, because %s priority is already set to High";
    public static final String PRIORITY_IS_ALREADY_SET_TO_HIGH_WITH_ID =
            "Cannot increase, because %s with ID %d's priority is already set to High";
    public static final String PRIORITY_IS_ALREADY_SET_TO_LOW =
            "Cannot lower, because %s priority is already set to Low";
    public static final String PRIORITY_IS_ALREADY_SET_TO_LOW_WITH_ID =
            "Cannot lower, because %s with ID %d's priority is already set to Low";
    public static final String NO_BOARD_ACTIVITY = "No activity in this board";
    public static final String BOARD_NOT_FOUND_ERR_MSG = "Board %s does not exist in Team %s";
    public static final String OPERATION_ADVANCE = "ADVANCE";
    public static final String OPERATION_REVERT = "REVERT";
    public static final String OPERATION_INCREASE = "INCREASE";
    public static final String OPERATION_LOWER = "LOWER";
    public static final String OPERATION_DECREASE = "DECREASE";
    public static final String NO_SUCH_CHANGE_STATUS_OPERATION_ERR_MSG = "No such change status operation! " +
            "Please input either advance or revert.";
    public static final String NO_SUCH_CHANGE_PRIORITY_OPERATION_ERR_MSG = "No such change status operation! " +
            "Please input either increase or lower.";


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
    public static final String SIZE_ALREADY_SET_TO_LARGE_WITH_ID =
            "Cannot increase, because story with ID %d's size is already set to Large";
    public static final String SIZE_ALREADY_SET_TO_SMALL =
            "Cannot decrease, because story size is already set to Small";
    public static final String SIZE_ALREADY_SET_TO_SMALL_WITH_ID =
            "Cannot decrease, because story with ID %d's size is already set to Small";
    //Activity
    public static final String ACTIVITY = "%s ACTIVITY";
    //Member
    public static final String MEMBER_WAS_CREATED = "A member with the name %s was created";
}
