package up.mi.Debat_Chambaz_Cibier_Xu;

/**
 * Class for the contradiction choice menu
 */
public class ContradictionChoiceMenu extends ChoiceMenu {
  /**
   * Adds a contradiction
   * @param debate The debate that a contradiction will be added to
   */
  public void contradictionAdd(Debate debate) {
    int args[] = IO.ioGetArgs(this.scanner, "Enter the two debate (An Am)", debate.getNumberArguments());
    try {
    	debate.addContradiction(args[0], new Contradiction(args[1]));
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return;
    }
    System.out.println(debate);
  }
}
