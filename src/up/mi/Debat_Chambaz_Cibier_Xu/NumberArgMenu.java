package up.mi.Debat_Chambaz_Cibier_Xu;

/**
 * Class for the number of argument menu
 */
public class NumberArgMenu extends SingleMenu {
  private int value;

  /**
   * Creates a number arg menu
   */
  public NumberArgMenu() {
    super();
    this.value = 0;
  }
  /**
   * Set Value from IO.ioGetInt function
   */
  public void setValueFromIO() {
    this.value = IO.ioGetInt(
        this.scanner,
        this.prompt);
  }
/**
 * Gets the value of the menu
 * @return the value
 */
  public int getValue() {
    return this.value;
  }
}
