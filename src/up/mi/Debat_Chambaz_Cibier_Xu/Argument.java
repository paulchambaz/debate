package up.mi.Debat_Chambaz_Cibier_Xu;

import java.util.ArrayList;

/**
 * An argument is a logical argument and the list of contradiction
 * It represents a single index of an adjacency list
 */
public class Argument {
  int id;
  String name;
  private ArrayList<Contradiction> contradictions;

  /**
   * Initializes an argument
   * @param id id of the parameter
   * @return The generated argument
   */
  public Argument(int id, String name) {
    this.id = id;
    this.name = new String(name);
    this.contradictions = new ArrayList<Contradiction>();
  }

  /**
   * Initalizes an argument from an already existing one
   * @param argument The argument to initialize from
   * @return The generated argument
   */
  public Argument(Argument argument) {
    this.id = argument.getId();
    this.name = new String(argument.getName());
    this.contradictions = argument.getContradictions();
  }

  /**
   * Adds a contradiction between two arguements
   * @param contradiction The contradiction to add
   */
  public void addContradiction(Contradiction contradiction) {
    this.contradictions.add(contradiction);
  }
  /**
   * Get all contradictions of the argument
   */
  public ArrayList<Contradiction> getContradictions() {
    return this.contradictions;
  }
   
  /**
   * Get the contradiction of the index i
   * @param i The index of the contradiction to get
   */
  public Contradiction getContradiction(int i) {
    return this.contradictions.get(i);
  }
  /**
   * Get the number of contradiction of the argument
   */
  public int getNumberContradictions() {
    return this.contradictions.size();
  }
  /**
   * Get the id (index of the argument in the graph).
   */
  public int getId() {
    return this.id;
  }

  /**
   * Get the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Create a string from the argument
   * @return The string representation of argument
   */
  public String toString() {
    return this.name;
  }
  /**
   * Checks if this argument contradicts with the argument arg.
   * @param arg The argument to check if it contradicts with
   * @return true if the argument contradicts the argument; false if not
   */
  public boolean contradicts(Argument arg) {
    for (Contradiction cont : this.getContradictions()) {
      if (cont.getContradicts() == arg.getId()) {
        return true;
      }
    }
    return false;
  }
}
