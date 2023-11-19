package com.company.oop.taskmanagementsystem.commands.enums;

public enum CommandType {
    CREATEMEMBER,
    SHOWALLMEMBERS,
    SHOWMEMBERACTIVITY,

    CREATENEWTEAM,
    SHOWALLTEAMS,
    SHOWTEAMACTIVITY,
    ADDPERSONTOTEAM,
    SHOWALLTEAMMEMBERS,

    CREATENEWBOARDINTEAM,
    SHOWALLTEAMBOARDS,
    SHOWBOARDACTIVITY,

    CREATENEWBUG,
    CREATENEWSTORY,
    CREATEFEEDBACK,

    CHANGEPRIORITYOFBUG,
    CHANGESTATUSOFBUG,
    CHANGESEVERITYOFBUG,

    CHANGEPRIORITYOFSTORY,
    CHANGESIZEOFSTORY,
    CHANGESTATUSOFSTORY,

    CHANGERATINGOFFEEDBACK,
    CHANGESTATUSOFFEEDBACK,

    ASSIGNTASKTOMEMBER,
    UNASSIGNTASKTOMEMBER,

    ADDCOMMENTTOTASK,

    LISTALLTASKS,
    FILTERTASKSBYTITLE,
    SORTTASKSBYTITLE,

    LISTBUGS,
    LISTSTORIES,
    LISTFEEDBACK,
    SORTBUGBY,
    SORTSTORYBY,
    SORTFEEDBACKBY;

//    List bugs/stories/feedback only.
//
//            · Filter by status and/or assignee
//
//· Sort by title/priority/severity/size/rating (depending on the task type)
//
//    o List tasks with assignee.
//
//· Filter by status and/or assignee
//
//· Sort by title


}
