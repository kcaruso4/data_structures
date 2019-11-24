/** IndexException custom class
*/

public class IndexException extends RuntimeException{
  /**This is to avoid a java compuler warning */
  private static final long serialVersionUID = 0L;

  /** custom constructor */
  public IndexException() {
    super("bad index value");
  }
}
