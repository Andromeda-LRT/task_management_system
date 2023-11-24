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
    private static final String OPEN_USER_STATEMENT_SYMBOL = "{{";
    private static final String CLOSE_USER_STATEMENT_SYMBOL = "}}";

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
        if (input.contains(OPEN_USER_STATEMENT_SYMBOL)){
            return configureUserStatementParameters(input);
        }

        String[] commandParts = input.split(" ");
        List<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }

    /**
     * The method first creates a List that will store all the userStatementParameters followed by an int variable to
     * pinpoint the index of mainSplitSymbol. An if statement is used to confirm if input contains both open and close
     * statement symbol in order to assign new value to userStatementParameters ArrayList using a new method, which solely
     * handles extraction of userStatementParameters. After the assignment is done, all the highlighted symbols are
     * replaced with an empty String "". After that another if statement is used to check if the current result contains
     * an empty String, and we return the outcome of the last method AddUserStatementParametersToOperationParameters, which
     * takes both result List (input w ) and the list containing the userStatementParameters.
     * @param input a complete input line
     * @return A list containing both userStatementParameters and the remaining operationParameters
     * as single elements inside
     */
    public List<String> configureUserStatementParameters(String input){
        List<String> userStatementParameters = new ArrayList<>();
        int indexOfFirstSeparator = input.indexOf(MAIN_SPLIT_SYMBOL);
        if (input.contains(OPEN_USER_STATEMENT_SYMBOL) && input.contains(CLOSE_USER_STATEMENT_SYMBOL)){
            userStatementParameters = extractUserStatementParameters(input, userStatementParameters);
        }

        input = input.replaceAll("\\{\\{.+(?=}})}}", "");
        List<String> result = new ArrayList<>(
                Arrays.asList(input.substring(indexOfFirstSeparator + 1)
                                .split(MAIN_SPLIT_SYMBOL)));
        if (result.contains("")){
            return AddUserStatementParametersToOperationParameters(result, userStatementParameters);
        }
        return result;
    }

    /**
     * A while loop is used to complete the parameter extraction process until fullOperation String does not have both
     * open and close statement symbol. On each iteration new open and close symbol indices are assigned to two local
     * int variables, which are used to make the extraction itself using substring method for the list
     * userStatementParameters. When that is done the fullOperation string in shortened from close user statement
     * symbol until its final index.
     * @param fullOperation a complete input line
     * @param userStatementParameters an empty List
     * @return A list containing userStatementParameters as single elements instead of multiple
     */
    public List<String> extractUserStatementParameters(String fullOperation, List<String> userStatementParameters) {

        while (fullOperation.contains(OPEN_USER_STATEMENT_SYMBOL) && fullOperation.contains(CLOSE_USER_STATEMENT_SYMBOL)) {

            int indexOfOpenComment = fullOperation.indexOf(OPEN_USER_STATEMENT_SYMBOL);
            int indexOfCloseComment = fullOperation.indexOf(CLOSE_USER_STATEMENT_SYMBOL);

            if (indexOfOpenComment >= 0) {
                userStatementParameters.add(fullOperation
                        .substring
                                (indexOfOpenComment + OPEN_USER_STATEMENT_SYMBOL.length(), indexOfCloseComment));
                fullOperation = fullOperation.substring
                        (indexOfCloseComment + CLOSE_USER_STATEMENT_SYMBOL.length(), fullOperation.length());
            }
        }
        return userStatementParameters;
    }

        /**
         * The method takes a List where it has a single element that is "" (empty) and another List that has several
         * elements, which could be a comment, title, or description for a given task. It iterates through each element
         * in the source List and once it reaches the index that is "" it adds the element from the target List.
         * The targetIndex increments and the enhanced for loop breaks in order to avoidConcurrentModificationException.
         * Also, even though the element is "" it still counts as such,
         * therefore the newly added element is added in its place,
         * and the "" element is moved to the following index along with the elements after it.
         * Before the loop ends all elements that are " ", "" or null are removed from the source List and the loop breaks.
         * E.g. if the source List is " (title) "" (description) would be null (rating) 2 "
         * target List is Index 1 - feedback 1111111111 is a test,
         * Index 2 this is a test description, and it should serve as such.
         * the method will return a list of ["feedback 1111111111 is a test,
         * this is a test description, and it should serve as such, 2"].
         *
         * @param source The list containing all the parameters where there used to be a comment, title and a
         * description for a task. Currently, that index is "" (empty).
         * @param target The list where the extracted comment or title and description is separated only by relevant
         * punctuation and taking a slot of a single element in a List.
         * @return A list of parameters that now has either a description and title or comment as a single element inside,
         * instead of several elements.
         */

    public List<String> AddUserStatementParametersToOperationParameters(List<String> source, List<String> target){
        int targetIndex = 0;
        while(targetIndex < target.size()) {
            for (String parameter : source) {
                if (parameter.isEmpty()) {
                    source.add(source.indexOf(parameter), target.get(targetIndex));
                    targetIndex++;
                    break;
                }
            }
        }
            source.removeAll(Arrays.asList(" ", "", null));
            return source;
    }

    private void print(String input){
        System.out.println(input);
    }
}
