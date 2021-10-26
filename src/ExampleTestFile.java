import DictionaryTypes.*;
import DictionaryTypes.abstractClasses.Dictionary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class ExampleTestFile {

	static DictionaryInterface dictionaryDataStructure;
    
    // Trie: test create dictionary and check all words added
    @Test
    public void test1() {
        dictionaryDataStructure = new TrieDictionary();
        boolean hasAllWords = true;
        dictionaryDataStructure.CreateDictionary("src/Input/fruitInput.txt");
        ArrayList<String> dictionary = dictionaryDataStructure.getDictionaryWords();
        if (!dictionary.contains("apple") || !dictionary.contains("orange") || !dictionary.contains("grape") || !dictionary.contains("kiwi")) {
            hasAllWords = false;
        }

        assertTrue(hasAllWords && dictionary.size() == 4);
    }

     // Trie: test addNewWord without CreateDictionary
     @Test
     public void test2() {
    	 dictionaryDataStructure = new TrieDictionary();
         dictionaryDataStructure.addNewWord("lemon");
         ArrayList<String> dictionary = dictionaryDataStructure.getDictionaryWords();
         
         assertTrue(dictionary.contains("lemon") && dictionary.size() == 1);
     }

     // Trie: test removeWord without CreateDictionary
     @Test
     public void test3() {
    	 dictionaryDataStructure = new TrieDictionary();
    	 dictionaryDataStructure.addNewWord("apple");
         dictionaryDataStructure.removeWord("apple");
         ArrayList<String> dictionary = dictionaryDataStructure.getDictionaryWords();
   
         assertTrue(dictionary.size() == 0);
     }
     
  // Trie: test addWord and then CreateDictionary afterwards (initial words and new word should be contained)
     @Test
     public void test4() {
    	 boolean hasAllWords = true;
    	 dictionaryDataStructure = new TrieDictionary();
    	 dictionaryDataStructure.addNewWord("bubble");
    	 dictionaryDataStructure.CreateDictionary("src/Input/fruitInput.txt");
         
         ArrayList<String> dictionary = dictionaryDataStructure.getDictionaryWords();
         if (!dictionary.contains("apple") || !dictionary.contains("orange") || !dictionary.contains("grape") 
        		 || !dictionary.contains("kiwi") || !dictionary.contains("bubble")) {
             hasAllWords = false;
         }

         assertTrue(hasAllWords && dictionary.size() == 5);
     }
     
  // Array: test create dictionary and check all words added
     @Test
     public void test5() {
         dictionaryDataStructure = new ArrayDictionary();
         boolean hasAllWords = true;
         dictionaryDataStructure.CreateDictionary("src/Input/fruitInput.txt");
         ArrayList<String> dictionary = dictionaryDataStructure.getDictionaryWords();
         if (!dictionary.contains("apple") || !dictionary.contains("orange") || !dictionary.contains("grape") || !dictionary.contains("kiwi")) {
             hasAllWords = false;
         }

         assertTrue(hasAllWords && dictionary.size() == 4);
     }

      // Array: test addNewWord
      @Test
      public void test6() {
     	 dictionaryDataStructure = new ArrayDictionary();
          dictionaryDataStructure.addNewWord("lemon");
          ArrayList<String> dictionary = dictionaryDataStructure.getDictionaryWords();
          
          assertTrue(dictionary.contains("lemon") && dictionary.size() == 1);
      }

      // Array: test removeWord
      @Test
      public void test7() {
     	 dictionaryDataStructure = new ArrayDictionary();
     	 dictionaryDataStructure.addNewWord("apple");
          dictionaryDataStructure.removeWord("apple");
          ArrayList<String> dictionary = dictionaryDataStructure.getDictionaryWords();
    
          assertTrue(dictionary.size() == 0);
      }
      
      // Trie data structure: getTopNSuggestions
      @Test
      public void test8() {
    	  dictionaryDataStructure = new TrieDictionary();

    	  dictionaryDataStructure.addNewWord("book");
    	  dictionaryDataStructure.addNewWord("boomerang");
    	  dictionaryDataStructure.addNewWord("books");
          dictionaryDataStructure.addNewWord("boo");
    	  
          String[] nSuggestions = dictionaryDataStructure.getTopNSuggestions(dictionaryDataStructure.getDictionaryWords(), "back", 5);

          boolean hasBoo = false;
          boolean hasBook = false;
          boolean hasBooks = false;

          for (int i = 0; i < nSuggestions.length; i++) {
              if (nSuggestions[i].equals("book")) {
                  hasBook = true;
              } else if (nSuggestions[i].equals("books")) {
                  hasBooks = true;
              } else if (nSuggestions[i].equals("boo")) {
                  hasBoo = true;
              }
          }

          assertTrue(nSuggestions.length == 4 && hasBoo && hasBook && hasBooks);
      }
      
      // ResizingArray data structure: getTopNSuggestions
      @Test
      public void test9() {
    	  dictionaryDataStructure = new ResizingArrayDictionary();
    	  
    	  dictionaryDataStructure.addNewWord("book");
    	  dictionaryDataStructure.addNewWord("boomerang");
    	  dictionaryDataStructure.addNewWord("books");
    	  dictionaryDataStructure.addNewWord("boo");
    	  
          String[] nSuggestions = dictionaryDataStructure.getTopNSuggestions(dictionaryDataStructure.getDictionaryWords(), "back", 5);

          boolean hasBoo = false;
          boolean hasBook = false;
          boolean hasBooks = false;

          for (int i = 0; i < nSuggestions.length; i++) {
              if (nSuggestions[i].equals("book")) {
                  hasBook = true;
              } else if (nSuggestions[i].equals("books")) {
                  hasBooks = true;
              } else if (nSuggestions[i].equals("boo")) {
                  hasBoo = true;
              }
          }

          assertTrue(nSuggestions.length == 4 && hasBoo && hasBook && hasBooks);
      }

}
