package up.mi.Debat_Chambaz_Cibier_Xu;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;

import java.util.ArrayList;

/**
 * Collection of helper functions
 */
public class Util {
  /**
   * Trims whitespace before and after a string
   * @param in The input string to trim
   * @return Trimed String
   */
  public static String trim(String in) {
    int start = 0, end = in.length() - 1;
    for (int i = start; i < in.length(); i++) {
      if (in.charAt(i) != ' ') {
        start = i;
        break;
      }
    }
    for (int i = end; i >= 0; i--) {
      if (in.charAt(i) != ' ') {
        end = i;
        break;
      }
    }
    return in.substring(start, end + 1);
  }

  /**
   * Parse an argument index
   * @param in The input string to be parse (format: A{N})
   * @return The parsed int
   */
  public static int parseArgIndex(String in) throws Exception {
    if (in.length() < 2) {
      throw new Exception("Invalid argument length");
    }
    if (in.charAt(0) != 'A') {
      throw new Exception("Invalid argument header");
    }
    int answer;
    try {
      answer = Integer.parseInt(in.substring(1));
    } catch (Exception e) {
      throw new Exception("Invalid argument, not a number");
    }
    return answer;
  }

  /**
   * Split argument around to array of string seperated by delimeter
   * @param in Input string
   * @param delimeter Delimeter to split around
   * @return A list of string - some can be null
   */
  public static String[] split(String in, char delimeter) {
    int count = 0;
    for (int i = 0; i < in.length(); i++) {
      if (in.charAt(i) == delimeter) {
        count++;
      }
    }
    String[] ret = new String[count + 1];
    count = 0;
    int start = 0;
    for (int i = 0; i <= in.length(); i++) {
      if (i == in.length() || in.charAt(i) == delimeter) {
        ret[count] = in.substring(start, i);
        start = i + 1;
        count++;
      }
    }
    return ret;
  }

  /**
   * Removes all whitespace from a string
   * @param in String to remove whitespace from
   * @return The string without whitespace
   */
  public static String removeWhitespace(String in) {
    String out = "";
    for (char c : in.toCharArray()) {
      if (c != ' ') {
        out += c;
      }
    }
    return out;
  }

  /**
   * Reads the content of a file and populate a string list
   * @param path The path of the file
   * @throws Exception file pointed by path cannot be opened or read
   * @return A table of string containing the lines of the file
   */
  public static String[] getLinesFromPath(String path) throws Exception {
    File file = new File(path);
    if (!file.exists() || !file.isFile())
      throw new Exception("File (" + path + ") does not exist");

    BufferedReader bufferedReader = null;
    try { 
      bufferedReader = new BufferedReader(new FileReader(path));
    } catch (Exception e) {
      throw new Exception("Could to open file (" + path + ")");
    }

    ArrayList<String> lines = new ArrayList<String>();
    String line = null;
    try {
      while ((line = bufferedReader.readLine()) != null) {
        lines.add(line);
      }
    } catch (Exception e) {
      bufferedReader.close();
      throw e;
    }

    bufferedReader.close();
    String[] out = new String[lines.size()];
    for (int i = 0; i < lines.size(); i++) {
      out[i] = new String(lines.get(i));
    }
    return out;
  }
  
  /**
   * Saves content of string to a file
   * @param content The string to save
   * @param path The path to save the file to
   */
  public static void saveStringToFile(String content, String path) {
	FileWriter writer;
	try {
		writer = new FileWriter(path);
		writer.write(content);
		writer.close();
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
  }
}

