package hw5;

import java.util.Scanner;
import java.lang.String;

/**
 * Filter unique integers from standard input to standard output.
 *
 * If you're benchmarking this program, you may want to suppress
 * the output by redirecting it to /dev/null.
 */
public final class Unique {
    private static Set<String> data;

    // Make checkstyle happy.
    private Unique() {}

    /**
     *  Main method.
     *  @param args Command line arguments (ignored).
     */
    public static void main(String[] args) {
        data = new MoveToFrontListSet<String>();
        Scanner scanner = new Scanner(System.in);

        double seconds = 0;
        while (scanner.hasNext()) {
            String i = scanner.next();
            long before = System.nanoTime();
            data.insert(i);
            long after = System.nanoTime();
            seconds += (after - before) / 1e9;

        }

        /**
        * Used for finding time efficiency and making sure ListSet works.
        *
        * System.out.printf("Inserting took %,-12f seconds\n", seconds);
        */
        for (String i: data) {
           System.out.println(i);
        }

    }
}
