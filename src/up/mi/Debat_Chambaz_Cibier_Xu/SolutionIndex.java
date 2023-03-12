package up.mi.Debat_Chambaz_Cibier_Xu;

/**
 * SolutionIndex allows for information on the current selected solution
 */
public class SolutionIndex {
  private int index;

  /**
   * Creates a solution index
   */
  public SolutionIndex() {
    this.index = -1;
  }

  /**
   * Increments the solution index
   */
  public void increment() {
    this.index++;
  }

  /**
   * Allows for proper looping of the solution index
   */
  public void loop(int n) {
    this.index %= n;
  }

  /**
   * Get the current index of the solution index
   */
  public int getIndex() {
    return this.index;
  }
}
