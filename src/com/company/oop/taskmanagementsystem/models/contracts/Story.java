package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.models.enums.Size;

public interface Story extends Prioritizeable {
     Size getSize();
     void increaseSize();
     void decreaseSize();
}
