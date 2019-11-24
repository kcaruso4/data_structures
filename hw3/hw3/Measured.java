/* 601.226
 * Measured.java
 */

package hw3;

/**
 * An object that can provide measured statistics about itself.
 *
 * We count operations that access or mutate the object in question. An
 * access doesn't modify the object itself, a mutation does.
 * For example, if we make an Array measured, get() and length() would be
 * accesses whereas put() would be a mutation.
 *
 * @param <T> The type the object holds
 */
public interface Measured<T> {
    /**
     * Reset the statistics collected so far. After this is called, all
     * counts are 0 again.
     */
    void reset();

    /**
     * Accesses so far.
     * @return Number of accesses so far, always &ge; 0.
     */
    int accesses();

    /**
     * Mutations so far.
     * @return Number of mutations so far, always &ge; 0.
     */
    int mutations();

    /**
     * Get the count of occurrences of t in the data.
     * @param t The value to count.
     * @return The count of occurrences of t in the data.
     */
    int count(T t);
}