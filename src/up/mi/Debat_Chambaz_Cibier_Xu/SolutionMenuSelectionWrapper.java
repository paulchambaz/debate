package up.mi.Debat_Chambaz_Cibier_Xu;

/**
 * Wrapper for the solution menu selection enum - used to pass by reference
 */
public class SolutionMenuSelectionWrapper {

  private SolutionMenuSelection solutionMenuSelection;

  /**
   * Initializes the solution menu selection wrapper
   */
  public SolutionMenuSelectionWrapper() {
    this.solutionMenuSelection = SolutionMenuSelection.DEFAULT_MENU;
  }

  /**
   * Get the content of the solution menu selection
   */
  public SolutionMenuSelection getSolutionMenuSelection() {
    return this.solutionMenuSelection;
  }

  /**
   * Set the content of the solution menu selection
   */
  public void setSolutionMenuSelection(SolutionMenuSelection solutionMenuSelection) {
    // cant set to default menu
    if (solutionMenuSelection == SolutionMenuSelection.DEFAULT_MENU) {
      return;
    }
    this.solutionMenuSelection = solutionMenuSelection;
  }
}
