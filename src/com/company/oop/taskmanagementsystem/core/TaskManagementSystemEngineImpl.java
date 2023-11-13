package com.company.oop.taskmanagementsystem.core;

import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemEngine;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManagementSystemEngineImpl implements TaskManagementSystemEngine {
    private static final String OPERATION_CANNOT_BE_EMPTY_ERROR = "Operation cannot be empty";
    private static final String SHUT_DOWN_COMMAND = "Exit";

    public TaskManagementSystemEngineImpl(){

    }
    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();
            if (input.isBlank()){
                print(OPERATION_CANNOT_BE_EMPTY_ERROR);
                continue;
            }
            if (input.equalsIgnoreCase(SHUT_DOWN_COMMAND)){
                break;
            }
            processOperation(input);
        }


    }

    private void processOperation(String input){
        String operationName = extractOperationName(input);
        List<String> parameters = extractOperationParameters(input);
        // todo to implement using commandFactory and execute method
        //todo to print the final result
    }

    private String extractOperationName(String input){
        return input.split(" ")[0];
    }

    private List<String> extractOperationParameters(String input){


        String[] operationParts = input.split(" ");
        List<String> parameters = Arrays.asList(operationParts);
        parameters.remove(0);
        return parameters;
    }

    private void print(String input){
        System.out.println(input);
    }
}
