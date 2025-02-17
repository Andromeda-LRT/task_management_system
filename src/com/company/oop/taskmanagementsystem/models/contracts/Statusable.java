package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.models.enums.Status;

public interface Statusable {
    Status getStatus();
    void changeStatus(Status statusToChangeTo);
    void advanceStatus();
    void revertStatus();
}
