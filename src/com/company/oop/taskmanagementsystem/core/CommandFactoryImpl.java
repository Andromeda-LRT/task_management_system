package com.company.oop.taskmanagementsystem.core;

import com.company.oop.taskmanagementsystem.commands.add.AddCommentToTask;
import com.company.oop.taskmanagementsystem.commands.add.AddMemberToTeam;
import com.company.oop.taskmanagementsystem.commands.assignAndUnassign.AssignTaskToMember;
import com.company.oop.taskmanagementsystem.commands.assignAndUnassign.UnassignTaskToMember;
import com.company.oop.taskmanagementsystem.commands.change.*;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.commands.create.*;
import com.company.oop.taskmanagementsystem.commands.enums.CommandType;
import com.company.oop.taskmanagementsystem.commands.filter.*;
import com.company.oop.taskmanagementsystem.commands.list.*;
import com.company.oop.taskmanagementsystem.commands.show.*;
import com.company.oop.taskmanagementsystem.commands.sort.SortBugByFields;
import com.company.oop.taskmanagementsystem.commands.sort.SortFeedbackByFields;
import com.company.oop.taskmanagementsystem.commands.sort.SortStoryByFields;
import com.company.oop.taskmanagementsystem.commands.sort.SortTasksByTitle;
import com.company.oop.taskmanagementsystem.core.contracts.CommandFactory;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {
    @Override
    public Command createCommandFromCommandName(String commandTypeAsString,
                                                TaskManagementSystemRepository taskManagementSystemRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
        switch (commandType) {
            case CREATENEWMEMBER:
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
            case ADDMEMBERTOTEAM:
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
                return new CreateNewBug(taskManagementSystemRepository);
            case CREATENEWSTORY:
                return new CreateNewStory(taskManagementSystemRepository);
            case CREATEFEEDBACK:
                return new CreateNewFeedback(taskManagementSystemRepository);
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
            case FILTERTASKSBYASSIGNEE:
                return new FilterTasksByAssignee(taskManagementSystemRepository);
            case FILTERTASKSBYSTATUS:
                return new FilterTasksByStatus(taskManagementSystemRepository);
            case FILTERTASKSBYSTATUSANDASSIGNEE:
                return new FilterTasksByStatusAndAssignee(taskManagementSystemRepository);
            case FILTERTASKSWITHASSIGNEEBYSTATUS:
                return new FilterTasksWithAssigneeByStatus(taskManagementSystemRepository);
            case SORTTASKSBYTITLE:
                return new SortTasksByTitle(taskManagementSystemRepository);
            case LISTALLBUGS:
                return new ListAllBugs(taskManagementSystemRepository);
            case LISTALLSTORIES:
                return new ListAllStories(taskManagementSystemRepository);
            case LISTALLFEEDBACK:
                return new ListAllFeedback(taskManagementSystemRepository);
            case LISTTASKSWITHASSIGNEE:
                return new ListTasksWithAssignee(taskManagementSystemRepository);
            case LISTTASKWITHASSIGNEESORTEDBYTITLE:
                return new ListTasksWithAssigneeSortedByTitle(taskManagementSystemRepository);
            case SORTBUGBY:
                return new SortBugByFields(taskManagementSystemRepository);
            case SORTSTORYBY:
                return new SortStoryByFields(taskManagementSystemRepository);
            case SORTFEEDBACKBY:
                return new SortFeedbackByFields(taskManagementSystemRepository);
            default:
                throw new IllegalArgumentException();
        }
    }
}
