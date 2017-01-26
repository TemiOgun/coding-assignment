/**
 Evernote coding Challenge
 
 @author Temi Ogunsanya
 Write a function that takes two parameters: 
(1) a String representing the contents of a text document 
(2) an integer providing the number of items to return

Implement the function such that it returns a list of Strings ordered by word frequency, the most frequently occurring word first. 
Use your best judgement to decide how words are separated. 
Implement this function as you would for a production / commercial system 
You may use any standard data structures. 
Please use your preferred language for the solution.
Performance Constraints:

Your solution should run in O(n) time where n is the number of characters in the document. 
Please provide reasoning on how the solution obeys the O(n) constraint.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Evernote{
	 /**
	   * Helper class for  PriorityQueue. Used to determine rank of word by frequency.
	   */
	  private static class Frequency implements Comparable<Frequency> {
	    private final int count;
	    private final String word;

	    /**
	     * Constructor for word frequency.
	     *
	     * @param entry the word to count frequency map entry
	     */
	    public Frequency(Map.Entry<String, Integer> entry) {
	      this.word = entry.getKey();
	      this.count = entry.getValue();
	    }

	    @Override
	    public int compareTo(Frequency item) {
	      if (item == null) {
	        return 1;
	      }
	      if (count == item.count) {
	        return 0;
	      }
	      return count > item.count ? -1 : 1;
	    }
	  }
	
	/**
	   * Reads a text file and returns a list of the words ordered by frequency
	   *
	   * @param text - the file contents as a string
	   * @param count - the number of results to return
	   * @return a list of most frequently used words
	   */
	public static List<String> wordFrequency(String text,int count){
		
		
		//check if string is null
		if(text.length() == 0) return (List<String>) Collections.<String>emptyList();
		
		//convert all characters to lower case and remove all non-character values
		String cleanedText = text.toLowerCase().replaceAll("[^a-z]", " ");
		
		//move all words into a character array
		String[] words = cleanedText.trim().split("\\s+");
		
		HashMap<String, Integer> frequency = new HashMap<String, Integer>();
		
		
		//iterates through the array of words. 
		//If the value is not currently present add it to the hashtable
		//else increase the frequency by one
		//O(n) operation where n is the number of words
		for(String word : words){
			Integer val = frequency.get(word);
			if(val == null){
			  //no count registered for the word yet
			  frequency.put(word, 1);
			}else{
			  frequency.put(word, count+1);
			}
		}
		
		//Using a priority queue along with the helper class to organize the results of the hashmap
		//Iterates through the size of the frequency hashmap O(n) where n is
		//the amount of entries
		Queue<Frequency> queue = new PriorityQueue<Frequency>(frequency.size());
	    for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
	      queue.offer(new Frequency(entry));
	    }

	    // extract top results
	    //O(n) where n is the result size
	    final int resultSize = Math.min(count, frequency.size());
	    final List<String> result = new ArrayList<String>(resultSize);
	    for (int i = 0; i < resultSize; i++) {
	      result.add(queue.poll().word);
	    }
		
		return result;
		
		
		
	}
	

}

