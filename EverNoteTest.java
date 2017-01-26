
import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Test;



public class EverNoteTest {


  @Test
  public void testWordFrequency() {
    
    List<String> words = Evernote.wordFrequency("Test;Evernote;test;test;evernote", 2);
    assertArrayEquals(new Object[] {"test", "evernote"}, words.toArray());
  }

  
  @Test
  public void testEmptyString() {
    List<String> words = Evernote.wordFrequency("", 0);
    assertArrayEquals(new Object[] {}, words.toArray());
  }

  @Test
  public void testNonWords() {
    List<String> words = Evernote.wordFrequency("!/@??", 0);
    assertArrayEquals(new Object[] {}, words.toArray());
  }
  
  @Test
  public void testWordsWithLetters() {
    List<String> words = Evernote.wordFrequency("World!!! Wow, fun hello, hello, wow// hello", 4);
    assertArrayEquals(new Object[] {"hello", "wow","fun", "world",}, words.toArray());
  }
  
  @Test
  public void testEmptySpaces() {
    List<String> words = Evernote.wordFrequency("      ", 0);
    assertArrayEquals(new Object[] {}, words.toArray());
  }

}