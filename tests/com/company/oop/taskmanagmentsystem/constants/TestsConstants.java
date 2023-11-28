package com.company.oop.taskmanagmentsystem.constants;

import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagementsystem.models.enums.Size;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestsConstants {
    public static final int VALID_ID = 1;
    public static final int INVALID_ID = -1;
    public static final String VALID_TITLE = "Example of a valid title";
    public static final String VALID_DESCRIPTION = "Example of a valid description";
    public static final String EMPTY_DESCRIPTION = "";
    public static final int VALID_RATING = 10;
    public static final String INVALID_TITLE = "Invalid";
    public static final String INVALID_DESCRIPTION = "Invalid";
    public static final String INVALID_BOARD_NAME = "xxx";
    public static final String VALID_BOARD_NAME = "xxxxxx";
    public static final List<String> STEPS_TO_REPRODUCE = Arrays.asList("Step 1", "Step 2");
    public static final List<String> EMPTY_STEPS_TO_REPRODUCE = new ArrayList<>();
    public static final List<String> NULL_STEPS_TO_REPRODUCE = null;
    public static final Priority VALID_PRIORITY = Priority.LOW;
    public static final Severity VALID_SEVERITY = Severity.CRITICAL;
    public static final Priority TEST_PRIORITY = Priority.LOW;
    public static final Size TEST_SIZE = Size.MEDIUM;
    public static final String VALID_MEMBER_NAME = "Ivancho";
    public static final String INVALID_MEMBER_NAME ="Ivo";
    public static final String VALID_TEAM_NAME = "testTeam";
    public static final String FILTERED_TITLES = "---Titles filtered by %s ---";
    public static final String BUG_SORTED_BY_GIVEN_PARAMETER = "---BUG SORTED BY %S---";
    public static final String FEEDBACK_SORTED_BY_GIVEN_PARAMETER = "---FEEDBACK SORTED BY %S---";
    public static final String STORY_SORTED_BY_GIVEN_PARAMETER = "---STORY SORTED BY %S---";
    public static final String VALID_TEAM_NAME_2 = "TestTeam2";
    public static final String TEST_NAME_FOR_OBJECT_CREATION = "xxxxxxxxxx";
    public static final int VALID_ID_2 = 2;
    public static final int VALID_ID_3 = 3;
    public static final int VALID_ID_4 = 4;
}
