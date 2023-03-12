package up.mi.Debat_Chambaz_Cibier_Xu;

import java.util.ArrayList;

/**
 * Solution class
 */
public class Solution extends Debate {

  /**
   * Initializes the solution from a debate
   */
  public Solution(Debate debate) {
    this.arguments = new ArrayList<Argument>();
    for (int i = 0; i < debate.getNumberArguments(); i++) {
      this.arguments.add(null);
    }
  }

  /**
   * Generate a solution from a debate and a list of the indexes of the debate to add
   * @param debate the debate to generate from
   * @param indexes the list of indexes of arguments from the debate to add
   */
  public Solution(Debate debate, int[] indexes) {
    this(debate);
    for (int i = 0; i < indexes.length; i++) {
      try {
        Argument argument = debate.getArgument(indexes[i]);
        this.add(argument);
      } catch (Exception e) {
        System.out.println(e.getMessage());
        return;
      }
    }
  }

  /**
   * Checks if a debate is admissible or not
   * @return wether or not the debate is admissible
   */
  public boolean check(Debate debate) {
    for (Argument arg : this.getArguments()) {
      if (arg == null) continue;
      for (Argument other : this.getArguments()) {
        if (other == null) continue;
        if (other.contradicts(arg)) {
          return false;
        }
      }
    }
    for (Argument arg : this.getArguments()) {
      if (arg == null) continue;
      for (Argument other : debate.getArguments()) {
        if (other.contradicts(arg)) {
          boolean isDefended = false;
          for (Argument defender : this.getArguments()) {
            if (defender == null) continue;
            if (defender.contradicts(other)) {
              isDefended = true;
              break;
            }
          }
          if (!isDefended) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Checks if this solution contains another
   * @return true iff this solution contains the other solution
   */
  public boolean contains(Solution other) {
    // an empty set cannot contain another set
    if (this.isEmpty()) {
      return false;
    }
    // for each argument in the solution
    for (Argument otherArgument : this.getArguments()) {
      if (otherArgument == null)
        continue;

      // we will check if it is present in the other solution
      boolean present = false;
      // we iterate over every argument in the other solution
      for (Argument argument : other.getArguments()) {
        if (argument == null)
          continue;
        // if we get one that has the same id, then they are identical and our argument is present in the other solution
        if (argument.getId() == otherArgument.getId()) {
          present = true;
          break;
        }
      }
      // if the argument is not present - then we know it is not a subset
      if (!present) {
        return true;
      }
    }
    return false;
  }

  /**
   * Create a string from the solution
   * @return The string representation of the solution
   */
  public String toString() {
    if (this.isEmpty()) {
      return "";
    }
    String out = "";
    for (int i = 0; i < this.arguments.size(); i++) {
      Argument argument = this.arguments.get(i);
      if (argument == null)
        continue;
      out += argument;
      out += ",";
    }
    out = out.substring(0, out.length() - 1);
    return out;
  }
}
