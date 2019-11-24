package hw8;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Stack;
import java.util.ArrayList;

/**
 * A beautiful search engine uwu.
 *
 */
public final class JHUgle {

    // Hush checkstyle.
    private JHUgle() {}

    /**
     * The function to handle operations.
     * @param pat is the identified operation
     * @param logic is the created Stack
     * @return bool to assign to run (only returns false if pat is !)
     */
    public static boolean operate(String pat, Stack<ArrayList<String>> logic) {
        if ("!".equals(pat)) {
            return false;
        }

        if (logic.empty()) {
            System.err.println("ERROR: Not enough arguments.");
            return true;
        }

        if ("?".equals(pat)) {
            for (int i = 0; i < logic.peek().size(); i++) {
                System.out.println(logic.peek().get(i));
            }
            return true;
        }

        ArrayList<String> first;
        first = logic.peek();
        logic.pop();

        if (logic.empty()) {
            System.err.println("ERROR: Not enough arguments.");
            logic.push(first);
            return true;
        }

        ArrayList<String> second;
        second = logic.peek();
        logic.pop();

        switch (pat) {
            case "&&":
                first.retainAll(second);
                logic.push(first);
                break;
            case "||":
                for (int i = 0; i < second.size(); i++) {
                    if (!first.contains(second.get(i))) {
                        first.add(second.get(i));
                    }
                }
                logic.push(first);
                break;
            default:
                logic.push(second);
                logic.push(first);
                return true;
        }
        return true;
    }

    /**
     * Main method.
     * @param args Command line arguments to indicate filename
     * @throws IOException if file is not opened correctly
     */
    public static void main(String[] args) throws IOException {
        File urlFile = new File(args[0]);

        Scanner fileScanner = new Scanner(urlFile);
        String url;
        String[] keys;
        Map<String, ArrayList<String>> index =
            new HashMap<String, ArrayList<String>>();


        //Build index
        while (fileScanner.hasNext()) {
            url = fileScanner.nextLine();
            keys = fileScanner.nextLine().split("\\s+");
            for (String key: keys) {
                if (index.has(key)) {
                    ArrayList<String> val = index.get(key);
                    val.add(url);
                    index.put(key, val);
                } else {
                    ArrayList<String> val = new ArrayList<String>();
                    val.add(url);
                    index.insert(key, val);
                }
            }
        }

        //Indicate Completion
        System.out.println("Index Created");
        System.out.print(">");

        boolean run = true;
        Scanner userScanner = new Scanner(System.in);
        Pattern operation = Pattern.compile("\\?|\\!|\\|\\||\\&\\&");
        String pat;
        String operand;
        Stack<ArrayList<String>> logic = new Stack<ArrayList<String>>();

        //UserInput forms boolean expression for Index Search Engine
        while (run && userScanner.hasNext()) {
            if (userScanner.hasNext(operation)) {
                pat = userScanner.next(operation);
                run = operate(pat, logic);
            } else {
                operand = userScanner.next();
                if (index.has(operand)) {
                    logic.push(index.get(operand));
                } else {
                    logic.push(new ArrayList<String>());
                }
            }

            if (run) {
                System.out.print(">");
            }
        }

        userScanner.close();
        fileScanner.close();
    }
}
