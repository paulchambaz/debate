package up.mi.Debat_Chambaz_Cibier_Xu;

public class Contradiction {

  private int contradicts;

  /**
   * Initializes a contradiction
   * @param contradicts Index of argument contradicted by  the contradiction
   */
  public Contradiction(int contradicts) {
    this.contradicts = contradicts;
  }

  /**
   * Returns index that contradiction contradicts
   * @return The contradicts value
   */
  public int getContradicts() {
    return this.contradicts;
  }

  /**
   * Create a string from the contradiction
   * @return The string representation of the argument
   */
  public String toString() {
    return "A" + (this.contradicts + 1);
  }

}
