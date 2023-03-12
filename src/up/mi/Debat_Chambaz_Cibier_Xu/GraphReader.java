package up.mi.Debat_Chambaz_Cibier_Xu;

import java.util.regex.Pattern;

/**
 * Reads the content of a graph file
 */
public class GraphReader {

  /**
   * Reads a debate file to populate a graph
   * @param path The path to the file to read
   * @throws Exception file could not be read correctly
   */
  public static Debate readFile(String path) throws Exception {
    Debate debate = new Debate();
    String[] lines;
    try {
      lines = Util.getLinesFromPath(path);
    } catch (Exception e) {
      throw new Exception("Could not read file: " + path + ".Error: " + e.getMessage());
    }

    int argument_id = 0;
    for (int i = 0; i < lines.length; i++) {
      if (!lineCheck(lines[i])) {
        throw new Exception("Error on line " + (i + 1) + " '" + lines[i] + "': bad format");
      }
      LineType lineType = getLineType(lines[i]);
      switch (lineType) {
        case ARGUMENT: {
          String arg = getArgumentArg(lines[i]);
          if (!argCheck(arg)) {
            throw new Exception("Error on line " + (i + 1) + " '" + lines[i] + "': bad argument format");
          }

          if (debate.exists(arg)) {
            throw new Exception("Error on line " + (i + 1) + " '" + lines[i] + ": argument already exists");
          }

          debate.add(new Argument(argument_id, arg));
          argument_id++;

          break;
        }
        case CONTRADICTION: {
          String[] args = getContradictionArgs(lines[i]);
          for (String arg : args) {
            if (!argCheck(arg)) {
              throw new Exception("Error on line " + (i + 1) + " '" + lines[i] + "': bad argument format");
            }
            if (!debate.exists(arg)) {
              throw new Exception("Error on line " + (i + 1) + " '" + lines[i] + ": argument '" + arg + "' does not exists");
            }
          }

          try {
            debate.addContradiction(debate.getArgumentId(args[0]), new Contradiction(debate.getArgumentId(args[1])));
          } catch (Exception e) {
            throw e;
          }
          break;
        }
      }
    }
    return debate;
  }

  /**
   * Basic check that the line is verb(arg,..).
   * @param in The line to check
   */
  private static boolean lineCheck(String in) {
    String regex_args = "\\s*.*\\s*";
    // defines the rule for the argument directives - argument(arg).
    String regex_argument = "^\\s*argument\\s*\\(" + regex_args + "\\)\\s*\\.\\s*$";
    // defines the rule for the contradiction directives - contradiction(arg,arg).
    String regex_contradiction = "^\\s*contradiction\\s*\\(" + regex_args + "\\s*,\\s*" + regex_args + "\\)\\s*\\.\\s*$";
    return Pattern.compile(regex_argument + "|" + regex_contradiction).matcher(in).matches();
  }

  private static boolean argCheck(String in) {
    return !Pattern.compile("argument|contradiction|,|\\(|\\)").matcher(in).matches();
  }

  /**
   * Types for the lines arguments
   */
  private enum LineType {
    ARGUMENT,
    CONTRADICTION
  }

  /**
   * Gets the type of a line from its line
   * @param in The string that contains information about the graph
   * @return The Line Type of the line
   */
  private static LineType getLineType(String in) {
    // we find the open parenthesis - we start at argument.length() because it cannot be before
    int end = findOpenParenthesis(in);
    if (in.substring(0, end).equals("argument")) {
      return LineType.ARGUMENT;
    } else {
      return LineType.CONTRADICTION;
    }
  }

  /**
   * Get the arguments of an argument from its line
   * @param in The string that contains information about the graph
   * @return The arguement
   */
  private static String getArgumentArg(String in) {
    int start = findOpenParenthesis(in);
    int end = in.length() - 2;
    return Util.trim(in.substring(start + 1, end));
  }

  /**
   * Get the arguements of a contradiction from its line
   * @param in The string that contains information about the graph
   * @return An array of the arguments
   */
  private static String[] getContradictionArgs(String in) {
    int start = findOpenParenthesis(in);
    int end = in.length() - 2;
    String args_str = in.substring(start + 1, end);
    String[] out = args_str.split(",");
    for (int i = 0; i < out.length; i++)
      out[i] = Util.trim(out[i]);
    return out;
  }

  /**
   * Finds the open parenthesis - which means the start of the arguments
   * @param in The string that contains information about the graph
   * @return The index of the first parenthesis
   */
  private static int findOpenParenthesis(String in) {
    for (int i = "argument".length(); i < in.length(); i++) {
      if (in.charAt(i) == '(') {
        return i;
      }
    }
    return -1;
  }
}
