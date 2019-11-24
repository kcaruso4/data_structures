// Calc.java
//Keilani Caruso kcaruso4 kcaruso4@jhu.edu


package hw4;

import java.util.Scanner;


/** A program for an RPN calculator that uses a stack. */
public final class Calc {

    //private stack in class
    private static Stack<Integer> stack;
    //keeps track of size of stack
    private static int count;


    // Hush checkstyle
    private Calc() {}


    /**
    * This function provides the length of the Stack.
    * @return the count variable (keeps track of length)
    */
    public static int length() {
        return count;
    }

    /**
    * This function modularizes adding a value.
    * @param operation is the operator entered by the user
    */
    public static void combine(String operation) {
        if (length() >= 2) {
            boolean revert = false;
            //checks to make sure there are enough arguments
            int newval = stack.top();
            stack.pop();
            count--;
            switch (operation) {
                case "+":
                    newval = stack.top() + newval;
                    break;
                case "-":
                    newval = stack.top() - newval;
                    break;
                case "*":
                    newval = stack.top() * newval;
                    break;
                case "/":
                    if (newval != 0) {
                        newval = stack.top() / newval;
                    }
                    else {
                        System.err.println("ERROR: Division by 0");
                        revert = true;
                        stack.push(newval);
                        count++;
                    }
                    break;
                case "%":
                    newval = stack.top() % newval;
                    break;
                default:
                    break;
            }
            if (!revert) {
                stack.pop();
                stack.push(newval);
            }
        }
        else {
            System.err.println("ERROR: Not enough arguments");
        }
    }

    /**
    * this function pops the Stack.
    */
    public static void popStack() {
        if (length() >= 1) {
            System.out.println(stack.top());
            stack.pop();
            count--;
        }
        else {
            System.err.println("ERROR: Not enough arguments");
        }
    }

    /**
    * Determines the function performed based on operation.
    * @param operation input from user
    */
    public static void operCommand(String operation) {
        switch (operation) {
            case "?":
                //prints the Stack
                System.out.println(stack.toString());
                break;
            case ".":
                popStack();
                break;
            case "!":
                //exits the program
                break;
            case "+": case "-": case "/": case "*": case "%":
                combine(operation);
                break;
            default:
                System.err.println("ERROR: Bad token");
                break;
        }
    }


    /**
     * The main function.
     * @param args Not used.
     */
    public static void main(String[] args) {

        //creates the scanner for command line
        Scanner sc = new Scanner(System.in);
        stack = new ListStack<Integer>();
        count = 0;
        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                //adds value to stack
                stack.push(sc.nextInt());
                count++;
            }
            else {
                //performs operation or throws an error
                String operation = sc.next();
                if ("!".equals(operation)) {
                    //exit the program
                    return;
                }
                operCommand(operation);
            }
        }
    }
}
