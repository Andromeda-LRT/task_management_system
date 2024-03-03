# Tasks Management Console Application

## Project Description

The Tasks Management console application is designed to help a small team of developers track and manage tasks for a software product they are building. This application supports multiple teams, each with its unique setup of members and boards to organize tasks effectively.

### Features

- **Multiple Teams Support:** Each team has a unique name, members, and boards.
- **Team Members:** Members have a unique name, a list of tasks, and an activity history.
- **Boards:** Each board within a team has a unique name, a list of tasks, and an activity history.
- **Task Types:** Supports three types of tasks: Bug, Story, and Feedback, each with specific attributes.
- **Task Operations:** Create, assign/unassign tasks to members, change task attributes, add comments, and view change history.
- **Listing and Filtering:** List all tasks or specific types (Bug/Story/Feedback) with options to filter and sort.

### Functional Requirements

#### Teams
- Unique name (5-15 symbols).
- Can have multiple members and boards.

#### Members
- Unique name (5-15 symbols).
- List of tasks and activity history.

#### Boards
- Unique name within the team (5-10 symbols).
- List of tasks and activity history.

#### Tasks
- **Bug:** ID, title, description, steps to reproduce, priority, severity, status, assignee, comments, and history.
- **Story:** ID, title, description, priority, size, status, assignee, comments, and history.
- **Feedback:** ID, title, description, rating, status, comments, and history.
- Task IDs are unique across the application.

### Operations

1. **Person Management:** Create a new person, show all people, show person's activity.
2. **Team Management:** Create a new team, show all teams, show team's activity, add person to team, show all team members.
3. **Board Management:** Create a new board in a team, show all team boards, show board's activity.
4. **Task Management:** Create new tasks (Bug/Story/Feedback), change task attributes, assign/unassign tasks, add comments to tasks.
5. **Listing and Filtering:** List tasks with various filters and sorting options.

### Use Cases

**Use case #1:** Logging a new bug with steps to reproduce and assigning it to a team member for fixing.

**Use case #2:** Adding a new developer to the team and assigning critical bugs to them.

**Use case #3:** Marking a bug as fixed, adding a comment, and verifying the change history.
