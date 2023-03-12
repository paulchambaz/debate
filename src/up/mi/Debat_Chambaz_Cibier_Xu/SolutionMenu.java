package up.mi.Debat_Chambaz_Cibier_Xu;

import java.io.File;
import java.util.Scanner;

/**
 * Class for the solution menu
 */
public class SolutionMenu extends ChoiceMenu {

  /**
   * Prints an admissible solution and prints it out to io
   * @param admissibleSolution Set of admissible solutions
   * @param admissibleSolutionIndex Index of the current admissible solution index chosen
   * @param solutionMenuSelection Type of the current menu selected - used as return value
   */
  public void searchAdmissibleSolution(Solution[] admissibleSolutions, SolutionIndex admissibleSolutionIndex, SolutionMenuSelectionWrapper solutionMenuSelection) {
    admissibleSolutionIndex.increment();
    admissibleSolutionIndex.loop(admissibleSolutions.length);
    System.out.println(admissibleSolutions[admissibleSolutionIndex.getIndex()]);
    solutionMenuSelection.setSolutionMenuSelection(SolutionMenuSelection.ADMISSIBLE_MENU);
  }

  /**
   * Prints an preferrend solution and prints it out to io
   * @param preferredSolution Set of preferred solutions
   * @param preferredSolutionIndex Index of the current preferred solution index chosen
   * @param solutionMenuSelection Type of the current menu selected - used as return value
   */
  public void searchPreferredSolution(Solution[] preferredSolutions, SolutionIndex preferredSolutionIndex, SolutionMenuSelectionWrapper solutionMenuSelection) {
    preferredSolutionIndex.increment();
    preferredSolutionIndex.loop(preferredSolutions.length);
    System.out.println(preferredSolutions[preferredSolutionIndex.getIndex()]);
    solutionMenuSelection.setSolutionMenuSelection(SolutionMenuSelection.PREFERRED_MENU);
  }

  /**
   * Saves currently selected solution (admissible or preferred) to a file
   * @param admissibleSolution Set of admissible solutions
   * @param admissibleSolutionIndex Index of the current admissible solution index chosen
   * @param preferredSolution Set of preferred solutions
   * @param preferredSolutionIndex Index of the current preferred solution index chosen
   * @param solutionMenuSelection Type of the current menu selected - used as return value
   * @param scanner Scanner to prompt user with
   */
  public void saveSolution(Solution[] admissibleSolutions, SolutionIndex admissibleSolutionIndex, Solution[] preferredSolutions, SolutionIndex preferredSolutionIndex, SolutionMenuSelectionWrapper solutionMenuSelection, Scanner scanner) {
    Solution toSave = null;
    switch (solutionMenuSelection.getSolutionMenuSelection()) {
      case DEFAULT_MENU:
        System.out.println("Please select a solution first");
        return;
      case ADMISSIBLE_MENU:
        if (admissibleSolutionIndex.getIndex() == -1) {
          System.out.println("Please select a solution first");
          return;
        }
        toSave = admissibleSolutions[admissibleSolutionIndex.getIndex()];
        break;
      case PREFERRED_MENU:
        if (preferredSolutionIndex.getIndex() == -1) {
          System.out.println("Please select a solution first");
          return;
        }
        toSave = preferredSolutions[preferredSolutionIndex.getIndex()];
    }
    System.out.println("Saving to disk");
    System.out.println("[ " + toSave + " ]");

    String path = "";
    while (path.isEmpty()) {
      System.out.println("Please enter a valid path for the file: ");
      path = scanner.nextLine();
      File file = new File(path);
      if (!file.exists()) {
        System.out.println("The path you entered is not valid.");
        continue;
      }
    }

    Util.saveStringToFile(toSave.toString(), path);
  }
}
