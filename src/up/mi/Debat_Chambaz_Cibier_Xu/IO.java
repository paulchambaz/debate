package up.mi.Debat_Chambaz_Cibier_Xu;

import java.util.Scanner;

/**
 * Collection of io helper functions
 */
public class IO {
  /**
   * Queries io for an int
   * @param scanner Initialized scanner
   * @param prompt Message to display to io
   * @return An int
   */
  public static int ioGetInt(Scanner scanner, String prompt) {
    while (true) {
      System.out.print(prompt + ": ");
      String answerStr = scanner.nextLine();
      answerStr = Util.trim(answerStr);
      int answer;
      try {
        answer = Integer.parseInt(answerStr);
      } catch (Exception e) {
        System.out.println("Error, invalid entry");
        continue;
      }
      return answer;
    }
  }

  /**
   * Queries io for a choice between a list
   * @param scanner Initialized scanner
   * @param prompt Message to display to io
   * @param choices Varargs of choices to display to io
   * @return An int between 0 and choices.length -1
   */
  public static int ioGetChoice(Scanner scanner, String prompt, String ... choices) {
    while (true) {
      for (int i = 0; i < choices.length; i++) {
        System.out.println((i + 1) + ". " + choices[i]);
      }
      int choice = ioGetInt(scanner, prompt + " (1-" + choices.length + ")");
      if (choice >= 1 && choice <= choices.length) {
        return choice - 1;
      }
      System.out.println("Error, enter a number between 1 and " + choices.length);
    }
  }
  /**
   * Queries io for an argument
   * @param scanner Initialized scanner
   * @param prompt Message to display to io
   * @return An argument
   */
  public static int ioGetArg(Scanner scanner, String prompt) {
    while (true) {
      System.out.print(prompt + ": ");
      String answerStr = scanner.nextLine();

      answerStr = Util.trim(answerStr);

      int arg = 0;
      try {
        arg = Util.parseArgIndex(answerStr) - 1;
      } catch (Exception e) {
        System.out.println("Error, could not parse argument"); 
        System.out.println(e.getMessage());
        continue;
      }

      return arg;
    }
  }
  /**
   * Queries io for a tab of argument (2 arguments)
   * @param scanner Initialized scanner
   * @param prompt Message to display to io
   * @return A tab of index of arguments
   */
  public static int[] ioGetArgs(Scanner scanner, String prompt, int size) {
    while (true) {
      // NOTE format of input is \s*A1\s*A2\s* and has to be trimmed correctly
      System.out.print(prompt + ": ");
      String answerStr = scanner.nextLine();

      // trim
      answerStr = Util.trim(answerStr);

      // split arguments
      String[] argsStr = Util.split(answerStr, ' ');

      // check at least two arguments
      if (argsStr.length < 2) {
        System.out.println("Error, not enough arguments");
        continue;
      }

      // check no non empty extra arguments
      boolean emptyMiddle = true;
      for (int i = 1; i < argsStr.length - 1; i++) {
        if (argsStr[i].length() != 0) {
          emptyMiddle = false;
        }
      }
      if (!emptyMiddle) {
        System.out.println("Error, not enough arguments");
        continue;
      }


      int[] args = new int[2];
      // parse arg 1
      try {
        args[0] = Util.parseArgIndex(argsStr[0]) - 1;
      } catch (Exception e) {
        System.out.println("Error, could not parse argument n°1"); 
        System.out.println(e.getMessage());
        continue;
      }

      // parse arg 2
      try {
        args[1] = Util.parseArgIndex(argsStr[argsStr.length - 1]) - 1;
      } catch (Exception e) {
        System.out.println("Error, could not parse argument n°2"); 
        System.out.println(e.getMessage());
        continue;
      }

      if (args[0] == args[1]) {
        System.out.println("Error, the arguments and the contradiction are identical");
        continue;
      }
      return args;
    }
  }
}
