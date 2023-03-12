package up.mi.Debat_Chambaz_Cibier_Xu;

import java.util.ArrayList;

/**
 * Container class for the debate
 * It is modeled after the adjacency list
 */
public class Debate {
  protected ArrayList<Argument> arguments;

  /**
   * Initializes an empty debate
   */
  public Debate() {
    this.arguments = new ArrayList<Argument>();
  }

  /**
   * Initializes the debate with a number of arguments
   * @param n Number of arguments to start with
   * @return Initialized debate
   */
  public Debate(int n) {
    this();
    for (int i = 0; i < n; i++) {
      this.arguments.add(new Argument(i, ""));
    }
  }

  /**
   * Adds a contradiction for an argument
   * @param id The index of the argument to add a contradiction to
   * @param contradiction The contradiction to add
   */
  public void addContradiction(int id, Contradiction contradiction) throws Exception {
    try {
      if (this.arguments.get(id) == null) {
        throw new Exception("Error, argument is not in the list of arguments");
      }
    } catch (Exception e) {
      throw new Exception("Error, argument is out of the list of arguments");
    }

    try {
      if (this.arguments.get(contradiction.getContradicts()) == null) {
        throw new Exception("Error, contradiction makes reference to argument not in the list");
      }
    } catch (Exception e) {
      throw new Exception("Error, contradiction makes reference to argument out of the list");
    }

    if (contradiction.getContradicts() == id) {
      throw new Exception("Error, argument and contradiction can not have reference the same arguement");
    }

    for (Contradiction cont: this.arguments.get(id).getContradictions()) {
      if (cont.getContradicts() == contradiction.getContradicts()) {
        throw new Exception("Error, contradiction already present for this argument");
      }
    }

    this.arguments.get(id).addContradiction(contradiction);
  }

  /**
   * Adds an argument to the list of arguments
   * @param argument Argument to add
   */
  public void add(Argument argument) throws Exception {
    if (argument.getId() < this.arguments.size() && this.arguments.get(argument.getId()) != null) {
        throw new Exception("Error, argument already present in the solution");
    }
    if (argument.getId() < this.arguments.size()) {
      this.arguments.set(argument.getId(), argument);
    } else {
      this.arguments.add(argument);
    }
  }

  /**
   * Removes an argument from the list of arguments
   * @param id Index to remove
   */
  public void del(int id) throws Exception {
    try {
      if (this.arguments.get(id) == null) {
        throw new Exception("Error, argument is not in the list of arguments");
      }
    } catch (Exception e) {
      throw new Exception("Error, argument is out of the list of arguments");
    }

    this.arguments.set(id, null);
  }

  /**
   * Get the argument list
   * @return The argument list
   */
  public ArrayList<Argument> getArguments() {
    return this.arguments;
  }

  /**
   * Get number of argument
   * @return The number of argument
   */
  public int getNumberArguments() {
    return this.arguments.size();
  }

  /**
   * Gets an argument from its id
   * @param id Index of argument to get
   * @return The argument that has the id
   */
  public Argument getArgument(int id) {
    return this.arguments.get(id);
  }

  /**
   * Returns if an argument exists by name
   */
  public boolean exists(String name) {
    for (Argument arg : arguments) {
    	if(arg == null) {
    		continue;
    	}
      if (arg.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return id of first argument with name
   * @param name The name of the argument
   */
  public int getArgumentId(String name) {
    for (Argument arg : arguments) {
      if (arg.getName().equals(name)) {
        return arg.getId();
      }
    }
    return -1;
  }

  /**
   * Checks if a debate is empty
   * @return wether or not a debate has no parameter
   */
  public boolean isEmpty() {
    for (Argument arg : this.arguments) {
      if (arg != null) {
        return false;
      }
    }
    return true;
  }

  /**
   * Create a string from the debate
   * @return The string representation of the debate
   */
  public String toString() {
    String out = "";
    out += "[\n";
    for (int i = 0; i < this.arguments.size(); i++) {
      Argument argument = this.arguments.get(i);
      if (argument == null) {
        continue;
      }
      out += "  ";
      out += "A" + (i + 1) + " - ";
      out += "\"" + argument + "\"" + ": [";
      for (int j = 0; j < argument.getNumberContradictions(); j++) {
        out += argument.getContradiction(j);
        if (j < argument.getNumberContradictions() - 1) {
          out += ", ";
        }
      }
      out += "]\n";
    }
    out += "]";
    return out;
  }
}
