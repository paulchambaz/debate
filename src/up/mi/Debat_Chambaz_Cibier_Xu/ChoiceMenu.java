package up.mi.Debat_Chambaz_Cibier_Xu;

import java.util.ArrayList;

/**
 * Class for a choice menu
 * A choice menu is used to query user to a choice
 * The menu gives a list of choices and a prompt
 * Each choice is associated with a function used
 * when the choice is asked
 */
abstract public class ChoiceMenu extends Menu {

  private boolean running;

  private ArrayList<String> choices;
  private ArrayList<Runnable> functions;

  /**
   * Initializes a choice menu
   */
  public ChoiceMenu() {
    this.prompt = null;
    this.scanner = null;
    this.running = true;
    this.choices = new ArrayList<String>();
    this.functions = new ArrayList<Runnable>();
  }

  /**
   * Adds a new choice
   * @param choice String that will be displayed to user
   * @param funciton Function pointer to the function being run when user selects that choice
   */
  public void addChoice(String choice, Runnable function) {
    this.choices.add(choice);
    this.functions.add(function);
  }

  /**
   * Checks if a menu is correctly initialized
   * @throws Exception menu is not fully initialized
   */
  private void check() throws Exception {
    if (scanner == null)
      throw new Exception("Invalid scanner");
    if (prompt == null)
      throw new Exception("Invalid prompt");
    if (choices == null || choices.size() == 0)
      throw new Exception("Empty choices");
    if (functions == null || functions.size() == 0)
      throw new Exception("Invalid functions");
    if (choices.size() != functions.size())
      throw new Exception("Choices and functions are not same size");
  }

  /**
   * Full run for the choice menu
   * @throws exception if menu is not initialized
   */
  public void run() throws Exception {
    // error handling
    try {
      check();
    } catch (Exception e) {
      throw e;
    }

    System.out.println(this.prompt);
    while (this.running) {
      String[] choices_arr = new String[choices.size()];
      for (int i = 0; i < choices.size(); i++) {
        choices_arr[i] = choices.get(i);
      }
      int choice = IO.ioGetChoice(
          this.scanner,
          this.prompt,
          choices_arr);

      for (int i = 0; i < functions.size(); i++) {
        if (choice == i) {
          functions.get(i).run();
          break;
        }
      }
    }
  }

  /**
   * Ends the menu - used as function pointer
   */
  public void end() {
    this.running = false;
  }
}
