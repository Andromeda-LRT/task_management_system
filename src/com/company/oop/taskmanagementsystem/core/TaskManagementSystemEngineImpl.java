package com.company.oop.taskmanagementsystem.core;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.core.contracts.CommandFactory;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemEngine;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManagementSystemEngineImpl implements TaskManagementSystemEngine {
    private static final String OPERATION_CANNOT_BE_EMPTY_ERROR = "Operation cannot be empty";
    private static final String SHUT_DOWN_COMMAND = "Exit";
    private static final String MAIN_SPLIT_SYMBOL = " ";
    private static final String OPEN_COMMENT_SYMBOL = "{{";
    private static final String CLOSE_COMMENT_SYMBOL = "{{";
    //todo to discuss if the below separator is necessary and if a new would be better.
    private static final String REPORT_SEPARATOR = "####################";

    private final TaskManagementSystemRepository taskManagementSystemRepository;
    private final CommandFactory commandFactory;

    public TaskManagementSystemEngineImpl(){
        this.taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        this.commandFactory = new CommandFactoryImpl();
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
        Command command = commandFactory.createCommandFromCommandName(operationName, taskManagementSystemRepository);
        List<String> parameters = extractOperationParameters(input);
        String commandResult = command.execute(parameters);
        System.out.println(commandResult);
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
        if (input.contains(OPEN_COMMENT_SYMBOL)){
            return extractCommentParameters(input);
        }
        String[] commandParts = input.split(" ");
        List<String> parameters = Arrays.asList(commandParts);
        parameters.remove(0);
        // converting the array to a List and removing first index since it is extract in the above method
        // and will be used for command. To try it out as an alternative instead of using a for loop.
        // if it does not work to switch back to a for loop.
//        for (int i = 1; i < commandParts.length; i++) {
//            parameters.add(commandParts[i]);
//        }
        return parameters;
    }
    public List<String> extractCommentParameters(String fullOperation){
        int indexOfFirstSeparator = fullOperation.indexOf(MAIN_SPLIT_SYMBOL);
        int indexOfOpenComment = fullOperation.indexOf(OPEN_COMMENT_SYMBOL);
        int indexOfCloseComment = fullOperation.indexOf(CLOSE_COMMENT_SYMBOL);
        List<String> parameters = new ArrayList<>();
        if (indexOfOpenComment >= 0){
            parameters.add(fullOperation.substring
                    (indexOfOpenComment + OPEN_COMMENT_SYMBOL.length(), indexOfCloseComment));
            fullOperation = fullOperation.replaceAll("\\{\\{.+(?=}})}}", "");
        }
        List<String> result = new ArrayList<>(
                Arrays.asList(fullOperation.substring(indexOfFirstSeparator + 1).split(MAIN_SPLIT_SYMBOL)));
        result.removeAll(Arrays.asList(" ","", null));
        parameters.addAll(result);
        return parameters;
    }

    private void print(String input){
        System.out.println(input);
        System.out.println(REPORT_SEPARATOR);
    }
}
