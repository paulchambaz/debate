package up.mi.Debat_Chambaz_Cibier_Xu;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import up.mi.Debat_Chambaz_Cibier_Xu.SolutionSearcher;
class SolutionSearcherTest {

	@ParameterizedTest
	@CsvSource({"'data/test1.txt', 'data/admissible1.txt'",
		"'data/test2.txt', 'data/admissible2.txt'",
		"'data/test3.txt', 'data/admissible3.txt'",
		"'data/test4.txt', 'data/admissible4.txt'"})
	void testGetAdmissible(String file, String admissibleFile) throws FileNotFoundException {
		Boolean condition = false;
		Debate try_debate = null;
	    try {
	      try_debate = GraphReader.readFile(file);
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	      return;
	    }
	    Debate debate = try_debate;
		Solution[] res = SolutionSearcher.getAdmissible(debate);
		Boolean exist_all = true;
	    File myObj = new File(admissibleFile);
	      Scanner myReader = new Scanner(myObj);
	      
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        String [] tab = data.split(",");
	        if (data.equals("")) {
	        	
	        	Boolean existInAll = false;
	        	for(Solution y : res) {
	        		Boolean isEmpty = true;
	        		for(Argument arg : y.getArguments()) {
	        			if(arg != null) {
	        				isEmpty = false;
	        			}
	        		}
	        		if (isEmpty == true ) {
	        			existInAll = true;
	        			break;
	        		}
	        	}
	        	
	        	assertTrue(existInAll);
	        	continue;
	        }
	        
	        Boolean exist_in = false;
	        for(Solution y : res) {
	        	Boolean exist_in_this = true;
        		for(String x : tab) {
    	        	Boolean on_loop = true;
    	        	if(y.exists(x) == false) {
    	        		exist_in_this = false;
    	        	}
    	        }
        		if(exist_in_this == true) {
        			exist_in = true;
        			break;
        		}
        	}
	        if(exist_in == false) {
	        	exist_all = false;
	        	break;
	        }
	        System.out.println(data);
	      }
	    myReader.close();
	    
		
		assertTrue(exist_all);
		//fail("Not yet implemented");
	}

	@ParameterizedTest
	@CsvSource({"'data/test1.txt', 'data/admissible1.txt', 'data/preferer1.txt'",
		"'data/test2.txt', 'data/admissible2.txt', 'data/preferer2.txt'",
		"'data/test3.txt', 'data/admissible3.txt', 'data/preferer3.txt'",
		"'data/test4.txt', 'data/admissible4.txt', 'data/preferer4.txt'"})
	void testGetPreferred(String file, String admissibleFile, String prefs) throws FileNotFoundException {
		Solution [] res;
		try {
			res = GetAdmissible(file, admissibleFile);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		    return;
		}
		Boolean exist_all = true;
	    File myObj = new File(admissibleFile);
	      Scanner myReader = new Scanner(myObj);
	      
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        String [] tab = data.split(",");
	        if (data.equals("")) {
	        	
	        	Boolean existInAll = false;
	        	for(Solution y : res) {
	        		Boolean isEmpty = true;
	        		for(Argument arg : y.getArguments()) {
	        			if(arg != null) {
	        				isEmpty = false;
	        			}
	        		}
	        		if (isEmpty == true ) {
	        			existInAll = true;
	        			break;
	        		}
	        	}
	        	
	        	assertTrue(existInAll);
	        	continue;
	        }
	        
	        Boolean exist_in = false;
	        for(Solution y : res) {
	        	Boolean exist_in_this = true;
        		for(String x : tab) {
    	        	Boolean on_loop = true;
    	        	if(y.exists(x) == false) {
    	        		exist_in_this = false;
    	        	}
    	        }
        		if(exist_in_this == true) {
        			exist_in = true;
        			break;
        		}
        	}
	        if(exist_in == false) {
	        	exist_all = false;
	        	break;
	        }
	        System.out.println(data);
	      }
	    myReader.close();
		
		
	}
	Solution [] GetAdmissible(String file, String admissibleFile) throws FileNotFoundException {
		Boolean condition = false;
		Debate try_debate = null;
	    try {
	      try_debate = GraphReader.readFile(file);
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	      return null;
	    }
	    Debate debate = try_debate;
		Solution[] res = SolutionSearcher.getAdmissible(debate);
		Boolean exist_all = true;
	    File myObj = new File(admissibleFile);
	      Scanner myReader = new Scanner(myObj);
	      
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        String [] tab = data.split(",");
	        if (data.equals("")) {
	        	
	        	Boolean existInAll = false;
	        	for(Solution y : res) {
	        		Boolean isEmpty = true;
	        		for(Argument arg : y.getArguments()) {
	        			if(arg != null) {
	        				isEmpty = false;
	        			}
	        		}
	        		if (isEmpty == true ) {
	        			existInAll = true;
	        			break;
	        		}
	        	}
	        	
	        	assertTrue(existInAll);
	        	continue;
	        }
	        
	        Boolean exist_in = false;
	        for(Solution y : res) {
	        	Boolean exist_in_this = true;
	    		for(String x : tab) {
		        	Boolean on_loop = true;
		        	if(y.exists(x) == false) {
		        		exist_in_this = false;
		        	}
		        }
	    		if(exist_in_this == true) {
	    			exist_in = true;
	    			break;
	    		}
	    	}
	        if(exist_in == false) {
	        	exist_all = false;
	        	break;
	        }
	        System.out.println(data);
	      }
	    myReader.close();
	    
		assertTrue(exist_all);
		return res;
	}

}

