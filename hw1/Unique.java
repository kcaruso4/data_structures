/* Keilani Caruso kcaruso4 kcaruso4@jhu.edu
 * Unique.java
 */

package hw1;

/** A class with a main method for printing out unique numbers. */
public final class Unique {

    // Make checkstyle happy.
    private Unique() {
        throw new AssertionError("Can not instantiate class Unique\n");
    }

    /**
     * A main method to print the unique numerical command line arguments.
     * @param args The string array of arguments in the command line.
     */
    public static void main(String[] args) {
        //creates an array the size of the # of values in commandline
        int[] temp = new int[args.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = 0;
        }
        int index = 0; //tracks added vals
        for (String value: args) {
            boolean found = false;
            int tempnum;
            try {
                tempnum = Integer.parseInt(value);
            }
            catch (NumberFormatException nfe) {
                throw new IllegalArgumentException();
            }
            for (int i = 0; i < index; i++) {
                if (temp[i] == tempnum) {
                    found = true;
                }
            }

            if (!found) {
                temp[index] = tempnum;
                index++;
            }
        }


        //prints out unique values on individual lines
        for (int i = 0; i < index; i++) {
            System.out.println(temp[i]);
        }
    }
}
