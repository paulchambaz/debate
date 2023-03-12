package up.mi.Debat_Chambaz_Cibier_Xu;

/**
 * Class for the argument choice menu
 */
public class ArgumentChoiceMenu extends ChoiceMenu {
  /**
   * Adds an argument to the list of arguments
   * @param source arguments to copy from
   * @param dest arguments to copy to
   */
  public void argumentAdd(Debate source, Debate dest) {
    System.out.println("Debate:");
    System.out.println(source);
    int arg = IO.ioGetArg(this.scanner, "Enter argument to add (An)");
    try {
      dest.add(source.getArgument(arg));
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return;
    }
    System.out.println("Solution:");
    System.out.println(dest);
  }

  /**
   * Deletes an argument from the list of arguments
   * @param arguments The arguments in which to delete an argument
   */
  public void argumentDel(Debate arguments) {
    System.out.println("Solution:");
    System.out.println(arguments);
    int arg = IO.ioGetArg(this.scanner, "Enter argument to remove (An)");
    try {
      arguments.del(arg);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return ;
    }
    System.out.println("Solution:");
    System.out.println(arguments);
  }

  /**
   * Checks the validity of a logical state
   * @param arguments List of arguments
   * @param solution Subset of arguments, the solution candidates
   */
  public void check(Debate debate, Solution solution) {
    if (solution.check(debate)) {
      System.out.println("This set of solution is admissible");
    } else {
      System.out.println("This set of solution is not admissible");
    }
  }
}
