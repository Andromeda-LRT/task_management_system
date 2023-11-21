package com.company.oop.taskmanagementsystem.core;

import com.company.oop.taskmanagementsystem.commands.*;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.commands.enums.CommandType;
import com.company.oop.taskmanagementsystem.core.contracts.CommandFactory;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {
    @Override
    public Command createCommandFromCommandName(String commandTypeAsString,
                                                TaskManagementSystemRepository taskManagementSystemRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
        switch (commandType) {
            case CREATEMEMBER:
                return new CreateNewMember(taskManagementSystemRepository);
            case SHOWALLMEMBERS:
                return new ShowAllMembers(taskManagementSystemRepository);
            case SHOWMEMBERACTIVITY:
                return new ShowMemberActivity(taskManagementSystemRepository);
            case CREATENEWTEAM:
                return new CreateNewTeam(taskManagementSystemRepository);
            case SHOWALLTEAMS:
                return new ShowAllTeams(taskManagementSystemRepository);
            case SHOWTEAMACTIVITY:
                return new ShowTeamActivity(taskManagementSystemRepository);
            case ADDPERSONTOTEAM:
                return new AddMemberToTeam(taskManagementSystemRepository);
            case SHOWALLTEAMMEMBERS:
                return new ShowAllTeamMembers(taskManagementSystemRepository);
            case CREATENEWBOARDINTEAM:
                return new CreateNewBoardInTeam(taskManagementSystemRepository);
            case SHOWALLTEAMBOARDS:
                return new ShowAllTeamBoards(taskManagementSystemRepository);
            case SHOWBOARDACTIVITY:
                return new ShowBoardActivity(taskManagementSystemRepository);
            case CREATENEWBUG:
                return new CreateNewBugInBoard(taskManagementSystemRepository);
            case CREATENEWSTORY:
                return new CreateNewStoryInBoard(taskManagementSystemRepository);
            case CREATEFEEDBACK:
                return new CreateNewFeedbackInBoard(taskManagementSystemRepository);
            case CHANGEPRIORITYOFBUG:
                return new ChangePriorityOfBug(taskManagementSystemRepository);
            case CHANGESTATUSOFBUG:
                return new ChangeStatusOfBug(taskManagementSystemRepository);
            case CHANGESEVERITYOFBUG:
                return new ChangeSeverityOfBug(taskManagementSystemRepository);
            case CHANGEPRIORITYOFSTORY:
                return new ChangePriorityOfStory(taskManagementSystemRepository);
            case CHANGESIZEOFSTORY:
                return new ChangeSizeOfStory(taskManagementSystemRepository);
            case CHANGESTATUSOFSTORY:
                return new ChangeStatusOfStory(taskManagementSystemRepository);
            case CHANGERATINGOFFEEDBACK:
                return new ChangeRatingOfFeedback(taskManagementSystemRepository);
            case CHANGESTATUSOFFEEDBACK:
                return new ChangeStatusOfFeedback(taskManagementSystemRepository);
            case ASSIGNTASKTOMEMBER:
                return new AssignTaskToMember(taskManagementSystemRepository);
            case UNASSIGNTASKTOMEMBER:
                return new UnassignTaskToMember(taskManagementSystemRepository);
            case ADDCOMMENTTOTASK:
                return new AddCommentToTask(taskManagementSystemRepository);
            case LISTALLTASKS:
                return new ListAllTasks(taskManagementSystemRepository);
            case FILTERTASKSBYTITLE:
                return new FilterTasksByTitle(taskManagementSystemRepository);
            case SORTTASKSBYTITLE:
                return new SortTasksByTitle(taskManagementSystemRepository);
            case LISTBUGS:
                return new ListAllBugs(taskManagementSystemRepository);
            case LISTSTORIES:
                return new ListAllStories(taskManagementSystemRepository);
            case LISTFEEDBACK:
                return new ListAllFeedback(taskManagementSystemRepository);
            case FILTERTASKBYSTATUSORANDASSIGNEE:
                return new FilterTaskByStatusOrAndAssignee(taskManagementSystemRepository);
            case FILTERTASKWITHASSIGNEEBYSTATUSANDORASSIGNEE:
                return new FilterTaskWithAssigneeByStatusAndOrAssignee(taskManagementSystemRepository);
            case LISTTASKSWITHASSIGNEE:
                return new ListTasksWithAssignee(taskManagementSystemRepository);
            case LISTTASKWITHASSIGNEESORTEDBYTITLE:
                return new ListTasksWithAssigneeSortedByTitle(taskManagementSystemRepository);
            case SORTBUGBY:
                return new SortBugByTitlePrioritySeverity(taskManagementSystemRepository);
            case SORTSTORYBY:
                return new SortStoryByTitlePrioritySize(taskManagementSystemRepository);
            case SORTFEEDBACKBY:
                return new SortFeedbackByTitleRating(taskManagementSystemRepository);
            default:
                throw new IllegalArgumentException();
        }
    }
}
