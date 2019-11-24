package hw7;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Count unique words.
 *
 * Read text from standard input, count how often each unique word appears,
 * write the results to standard output. Note that the definiton of "word"
 * is rather arbitrary and won't make the linguists among you very happy.
 */
public final class Words {
    private static Map<String, Integer> data;

    // Make checkstyle happy.
    private Words() {}

    /**
     * Main method.
     * @param args Command line arguments (ignored).
     */
    public static void main(String[] args) {
        data = new TreapMap<String, Integer>();
        Scanner scanner = new Scanner(System.in);
        try {
            scanner = new Scanner(new File(args[0]));
        }
        catch (FileNotFoundException e) {
            System.out.println("Please enter a valid file");
        }
        double seconds = 0;

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            // The regular expression splits strings on whitespace and
            // non-word characters (anything except [a-zA-Z_0-9]). Far
            // from perfect, but close enough for this simple program.
            String[] words = s.split("[\\s\\W]+");
            for (String word: words) {
                if (word.length() <= 1) {
                    // Skip "short" words, most of which just "dirty up"
                    // the statistics.
                    continue;
                }
                long before = System.nanoTime();
                if (data.has(word)) {
                    data.put(word, data.get(word) + 1);
                } else {
                    data.insert(word, 1);
                }
                long after = System.nanoTime();
                seconds += (after - before) / 1e9;
            }
        }

        /**for (String word: data) {
            System.out.println(word + ": " + data.get(word));
        }*/
        System.out.println("Time to check/insert: " + seconds);
    }
}
