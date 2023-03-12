package up.mi.Debat_Chambaz_Cibier_Xu;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    premiere_phase(args);
  }

  public static void seconde_phase(String[] args) {

    // print usage when no parameter are provided
    if (args.length == 0) {
      System.out.println("usage: Debat_Chambaz_Cibier_Xu file_path");
      System.exit(1);
    }

    // done since this variable cannot be final for lambda expression since it would be out of scope
    Debate tryDebate = null;
    try {
    	tryDebate = GraphReader.readFile(args[0]);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return;
    }
    Debate debate = tryDebate;

    // print the debate graph to the user
    System.out.println("Loaded debate");
    System.out.println(debate);

    // first we cheat a little bit since we will calculate all the solution now,
    //this will allow for control over what is being displayed to the user
    // this operation is fast so it does not cost much - that being said,
    //if it were not the case we could increase an arraylist and search at user time for solutions
    Solution[] admissibleSolutions = SolutionSearcher.getAdmissible(debate);
    Solution[] preferredSolutions = SolutionSearcher.getPreferred(admissibleSolutions);
    
    SolutionIndex admissibleSolutionIndex = new SolutionIndex();
    SolutionIndex preferredSolutionIndex = new SolutionIndex();
    SolutionMenuSelectionWrapper solutionMenuSelection = new SolutionMenuSelectionWrapper();

    Scanner scanner = new Scanner(System.in);

    // creation of the menu for the user
    SolutionMenu solutionMenu = new SolutionMenu();
    solutionMenu.addScanner(scanner);
    solutionMenu.addPrompt("Select a choice");
    solutionMenu.addChoice("Search for an admissible solution", () -> solutionMenu.searchAdmissibleSolution(admissibleSolutions, admissibleSolutionIndex, solutionMenuSelection));
    solutionMenu.addChoice("Search for a preferred solution", () -> solutionMenu.searchPreferredSolution(preferredSolutions, preferredSolutionIndex, solutionMenuSelection));
    solutionMenu.addChoice("Save solution", () -> solutionMenu.saveSolution(admissibleSolutions, admissibleSolutionIndex, preferredSolutions, preferredSolutionIndex, solutionMenuSelection, scanner));
    solutionMenu.addChoice("End", () -> solutionMenu.end());

    // the menu is running
    try {
      solutionMenu.run();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      scanner.close();
      return;
    }

  }

  public static void premiere_phase(String[] args) {
    Scanner scanner = new Scanner(System.in);

    NumberArgMenu numberArgMenu =  new NumberArgMenu();
    numberArgMenu.addScanner(scanner);
    numberArgMenu.addPrompt("Enter number of arguments");
    numberArgMenu.addFunction(() -> numberArgMenu.setValueFromIO());

    try {
      numberArgMenu.run();
    } catch (Exception e) {
      System.out.println(e);
      scanner.close();
      return;
    }
    int numberArguments = numberArgMenu.getValue();
    Debate debate = new Debate(numberArguments);

    System.out.println(debate);

    ContradictionChoiceMenu contradictionChoiceMenu = new ContradictionChoiceMenu();
    contradictionChoiceMenu.addScanner(scanner);
    contradictionChoiceMenu.addPrompt("Select a choice");
    contradictionChoiceMenu.addChoice("Add a contradiction", () -> contradictionChoiceMenu.contradictionAdd(debate));
    contradictionChoiceMenu.addChoice("End", () -> contradictionChoiceMenu.end());

    try {
      contradictionChoiceMenu.run();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      scanner.close();
      return;
    }

    System.out.println("Debate:");
    System.out.println(debate);

    Solution solution = new Solution(debate);

    ArgumentChoiceMenu argumentChoiceMenu = new ArgumentChoiceMenu();
    argumentChoiceMenu.addScanner(scanner);
    argumentChoiceMenu.addPrompt("Select a choice");
    argumentChoiceMenu.addChoice("Add an argument", () -> argumentChoiceMenu.argumentAdd(debate, solution));
    argumentChoiceMenu.addChoice("Remove an argument", () -> argumentChoiceMenu.argumentDel(solution));
    argumentChoiceMenu.addChoice("Check solution", () -> argumentChoiceMenu.check(debate, solution));
    argumentChoiceMenu.addChoice("End", () -> argumentChoiceMenu.end());

    try {
      argumentChoiceMenu.run();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      scanner.close();
      return;
    }

    scanner.close();
  }
}
