import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * This is a command line calculator written in
 *
 * <p>Java
 *
 * @author Christopher Di Bert
 * @version 1.0
 * @since 2024-2-12
 */
public class MathOperators {

  // Properties to be accessed by calculator methods
  private static int userDecimalPlaces = 0;
  private static Double num1 = 0.0, num2 = 0.0;
  private static String userOperation;
  private static final String[] OPERATIONS = {"+", "-", "*", "/", "^", "sqrt"};

  public static void main(String[] args) {
    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    System.out.println("Hello! Welcome to the calculator!");

    /*Gets the user's input, if there weren't any error messages returned,
     * it calls the math function. If not, prints the error message.
     */
    final String errorMessage = GetUserValues();
    if (errorMessage.equals("No errors")) {
      System.out.println("Answer: " + Arithmetic(num1, num2, userDecimalPlaces, userOperation));
    } else {
      System.out.println(errorMessage);
    }
  }

  // Used to check if user's operator actually exists
  private static boolean IsValidOperation(String userOperation) {
    // Foreach loop iterates through all possible operations
    for (String operation : OPERATIONS) {
      if (userOperation.equals(operation)) {
        return true;
      }
    }
    // User entered invalid operation
    return false;
  }

  // Method used to retrieve the user's input'
  private static String GetUserValues() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter how many decimal places you want in your answers: ");
    try {
      userDecimalPlaces = sc.nextInt();
    } catch (Exception e) {
      return "You must only enter a whole number!";
    }

    // Call clears the input buffer to prevent input not being retrieved
    sc.nextLine();

    // Asks the user for the operation they want to use
    System.out.print("Enter the operation you wish to perform\n");
    System.out.print("(+, -, *, /, ^, sqrt)\n>> ");
    userOperation = sc.nextLine();
    if (IsValidOperation(userOperation) == false) {
      return "You must enter a listed operator!";
    }

    /*Gets numbers from the user. If the user wants to square root,
     * just tells them to enter one number.
     */
    if (userOperation.charAt(0) != 's') {
      System.out.print("Enter num 1: ");
      try {
        num1 = sc.nextDouble();
        System.out.print("Enter num 2: ");
        num2 = sc.nextDouble();
      } catch (Exception e) {
        return "You must only enter numbers!";
      }
    } else {
      System.out.print("Enter a number to square root: ");
      try {
        num1 = sc.nextDouble();
      } catch (Exception e) {
        return "You must enter a valid number!";
      }
    }
    // Returns any error messages or lack thereof
    return ErrorCheck();
  }

  // Function used to catch any mathematically impossible operations
  private static String ErrorCheck() {
    if (num2 == 0 && userOperation == "/") {
      return "You cannot divide by zero!";
    } else if (num1 < 0) {
      return "You can't take the square root of a negative number!";
    }
    // If no errors were found, returns string saying no errors found
    return "No errors";
  }

  // Method used to do math and rounding. Returns a string that can be printed.
  private static String Arithmetic(Double num1, Double num2, int decimalPlaces, String operation) {
    // Sets the decimal precision of the decimal formatter.
    DecimalFormat decimalFormatter = new DecimalFormat("0." + ("0".repeat(decimalPlaces)));
    Double result;
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
