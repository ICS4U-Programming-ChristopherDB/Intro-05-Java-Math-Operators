package com.example;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Command line calculator built with Java.
 *
 * @author Christopher Di Bert
 * @version 1.0
 * @since 2024-02-14
 */
public final class MathOperators {

    /** Private constructor to prevent instantiation. */
    private MathOperators() {
        throw new UnsupportedOperationException("Cannot instantiate");
    }

    /** The number of decimal places that the user will specify. */
    private static int userDecimalPlaces = 0;
    /** Initializing input values that will be overridden. */
    private static Double num1 = 0.0d;
    /** Initializing input values that will be overridden. */
    private static Double num2 = 0.0d;
    /** Initializing input values that will be overridden. */
    private static String userOperation;
    /** Array of operations that are available. Used to check for valid input. */
    private static final String[] OPERATIONS = {"+", "-", "*", "/", "^", "sqrt"};

    public static void main(final String[] args) {
        System.out.print("\n".repeat(20));
        System.out.println("Hello! Welcome to the calculator!");

        final String errorMessage = getUserValues();
        if ("No errors".equals(errorMessage)) {
            System.out.println("Answer: " + arithmetic(num1, num2, userDecimalPlaces, userOperation));
        } else {
            System.out.println(errorMessage);
        }
    }

    private static boolean isValidOperation(final String userOperation) {
        for (final String operation : OPERATIONS) {
            if (userOperation.equals(operation)) {
                return true;
            }
        }
        return false;
    }

    private static String getUserValues() {
        final Scanner sc = new Scanner(System.in);
        System.out.print("Enter how many decimal places you want in your answers: ");
        try {
            userDecimalPlaces = sc.nextInt();
        } catch (final Exception e) {
            return "You must only enter a whole number!";
        }

        sc.nextLine();

        System.out.print("Enter the operation you wish to perform\n");
        System.out.print("(+, -, *, /, ^, sqrt)\n>> ");
        userOperation = sc.nextLine();
        if (!isValidOperation(userOperation)) {
            return "You must enter a listed operator!";
        }

        if (userOperation.charAt(0) != 's') {
            System.out.print("Enter num 1: ");
            try {
                num1 = sc.nextDouble();
                System.out.print("Enter num 2: ");
                num2 = sc.nextDouble();
            } catch (final Exception e) {
                return "You must only enter numbers!";
            }
        } else {
            System.out.print("Enter a number to square root: ");
            try {
                num1 = sc.nextDouble();
            } catch (final Exception e) {
                return "You must enter a valid number!";
            }
        }
        return errorCheck();
    }

    private static String errorCheck() {
        if (num2.intValue() == 0 && userOperation.equals("/")) {
            return "You cannot divide by zero!";
        } else if (num1 < 0 && userOperation.equals("sqrt")) {
            return "You can't take the square root of a negative number!";
        }
        return "No errors";
    }

    private static String arithmetic(final Double num1, final Double num2, final int decimalPlaces, final String operation) {
        final DecimalFormat decimalFormatter = new DecimalFormat("0." + "0".repeat(decimalPlaces));
        final Double result;
        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            case "^":
                result = Math.pow(num1, num2);
                break;
            default:
                result = Math.sqrt(num1);
                break;
        }
        return decimalFormatter.format(result);
    }
}
