package com.company.oop.taskmanagementsystem.core;

import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemEngine;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManagementSystemEngineImpl implements TaskManagementSystemEngine {
    private static final String OPERATION_CANNOT_BE_EMPTY_ERROR = "Operation cannot be empty";
    private static final String SHUT_DOWN_COMMAND = "Exit";
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public TaskManagementSystemEngineImpl(){
        this.taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
    }

    /**
     * Opens a scanner that will take incoming input lines until the shut down command "Exit" is given
     * in any other case it will continue working.
     * In the meantime the whole process is wrapped in a try catch block in case of any exception that may be thrown
     * from any of the methods within the program and the catch block will take it and print out the message to the user.
     */
    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.isBlank()) {
                    print(OPERATION_CANNOT_BE_EMPTY_ERROR);
                    continue;
                }
                if (input.equalsIgnoreCase(SHUT_DOWN_COMMAND)) {
                    break;
                }
                processOperation(input);
            } catch (Exception exception){
                // if there is an exception and its message is NOT empty AND NOT null
                // it will be outputted to the console
                if (exception.getMessage() != null && !exception.getMessage().isEmpty()){
                    print(exception.getMessage());
                } else {
                    // otherwise we print an exception using toString method
                    print(exception.toString());
                }
            }
        }
    }

    private void processOperation(String input){
        String operationName = extractOperationName(input);
        List<String> parameters = extractOperationParameters(input);
        // todo to implement using commandFactory and execute method
        //todo to print the final result
    }
    /**
     * From the received line in input only the first word is extracted, which is expected to be the operation
     * E.g. if we have an input "CreateFeedback Test TestNow 3", the method will return "CreateFeedback".
     * @param input A complete input line
     * @return The name of the operation to be executed
     */

    private String extractOperationName(String input){
        return input.split(" ")[0];
    }

    /**
     * From the received line in input extracts the parameters that are required for an operation to execute.
     * E.g. if the input is "CreateFeedback Test TestNow 3"
     * the method will return a list of ["Test, TestNow, 3"].
     *
     * @param input A complete input line
     * @return A list of the parameters needed to execute the operation
     */
    private List<String> extractOperationParameters(String input){
        //todo to consider a specific logic when input is a comment

        String[] operationParts = input.split(" ");
        List<String> parameters = Arrays.asList(operationParts);
        parameters.remove(0);
        return parameters;
    }

    private void print(String input){
        System.out.println(input);
    }
}
