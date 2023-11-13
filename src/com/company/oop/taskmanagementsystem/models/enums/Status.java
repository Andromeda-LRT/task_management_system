package com.company.oop.taskmanagementsystem.models.enums;

public enum Status {
    NEW,
    UNSCHEDULED,
    SCHEDULED,
    ACTIVE,
    NOT_DONE,
    IN_PROGRESS,
    DONE;

    @Override
    public String toString() {
        switch (this) {
            case NEW:
                return "New";
            case UNSCHEDULED:
                return "Unscheduled";
            case SCHEDULED:
                return "Scheduled";
            case ACTIVE:
                return "Active";
            case NOT_DONE:
                return "Not done";
            case IN_PROGRESS:
                return "In progress";
            case DONE:
                return "Done";
            default:
                throw new UnsupportedOperationException("There is no such status.");
        }
    }
}
