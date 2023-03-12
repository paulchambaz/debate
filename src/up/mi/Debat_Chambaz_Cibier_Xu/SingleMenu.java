package up.mi.Debat_Chambaz_Cibier_Xu;

/**
 * Class for a single menu
 * A single menu is used to query user for a single
 * question and recieve a result
 * The answer is then passed to a value
 */
public class SingleMenu extends Menu {
  private Runnable function;

  /**
   * Creates a number arg menu
   */
  public SingleMenu() {
    this.prompt = null;
    this.scanner = null;
    this.function = null;
  }
/**
 * Add a function to the Single Menue
 * To run it when a value is enter by the user
 * @param function
 */
  public void addFunction(Runnable function) {
    this.function = function;
  }
/**
 * run the single menu and the associated function
 * @throws Exception if scanner or prompt or function is null
 */
  public void run() throws Exception {
    // error handling
    if (scanner == null)
      throw new Exception("Invalid scanner");
    if (prompt == null)
      throw new Exception("Invalid prompt");
    if (function == null)
      throw new Exception("Invalid function");
    function.run();
  }
}
