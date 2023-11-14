package com.company.oop.taskmanagementsystem.models.enums;

public enum Priority {
    LOW,
    MEDIUM,
    HIGH;

    @Override
    public String toString(){
        switch (this){
            case LOW:
                return "Low";
            case MEDIUM:
                return "Medium";
            case HIGH:
                return "High";
            default:
                throw new UnsupportedOperationException("There is no such priority.");
        }
    }
}
