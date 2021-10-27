import DictionaryTypes.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class FinalHandinUnitTestExamples {
    
    // test BFS
    @Test
    public void test1() {
        TrieDictionary dictionaryDataStructure = new TrieDictionary();
        dictionaryDataStructure.addNewWord("brown");
        dictionaryDataStructure.addNewWord("brow");
        dictionaryDataStructure.addNewWord("broke");
        dictionaryDataStructure.addNewWord("a");
        dictionaryDataStructure.addNewWord("an");
        dictionaryDataStructure.addNewWord("ant");
        dictionaryDataStructure.addNewWord("ants");
        dictionaryDataStructure.addNewWord("art");

        ArrayList<Character> arrayToReturn = dictionaryDataStructure.BFS();
        
        if (arrayToReturn.get(0).equals('a') && 
            arrayToReturn.get(1).equals('b') && 
            arrayToReturn.get(2).equals('n') && 
            arrayToReturn.get(3).equals('r') && 
            arrayToReturn.get(4).equals('r') && 
            arrayToReturn.get(5).equals('t') && 
            arrayToReturn.get(6).equals('t') && 
            arrayToReturn.get(7).equals('o') && 
            arrayToReturn.get(8).equals('s') && 
            arrayToReturn.get(9).equals('k') && 
            arrayToReturn.get(10).equals('w') && 
            arrayToReturn.get(11).equals('e') && 
            arrayToReturn.get(12).equals('n')) {
        	assert(true);
            System.out.println("TEST CASE 1: PASSED");
        } else {
        	assert(false);
            System.out.println("TEST CASE 1: FAILED");
        }
    }
    
    // test DFS
    @Test
    public void test2() {
        TrieDictionary dictionaryDataStructure = new TrieDictionary();
        dictionaryDataStructure.addNewWord("brown");
        dictionaryDataStructure.addNewWord("brow");
        dictionaryDataStructure.addNewWord("broke");
        dictionaryDataStructure.addNewWord("a");
        dictionaryDataStructure.addNewWord("an");
        dictionaryDataStructure.addNewWord("ant");
        dictionaryDataStructure.addNewWord("ants");
        dictionaryDataStructure.addNewWord("art");

        ArrayList<Character> arrayToReturn = dictionaryDataStructure.DFS();
        
        if (arrayToReturn.get(0).equals('a') && 
            arrayToReturn.get(1).equals('n') && 
            arrayToReturn.get(2).equals('t') && 
            arrayToReturn.get(3).equals('s') && 
            arrayToReturn.get(4).equals('r') && 
            arrayToReturn.get(5).equals('t') && 
            arrayToReturn.get(6).equals('b') && 
            arrayToReturn.get(7).equals('r') && 
            arrayToReturn.get(8).equals('o') && 
            arrayToReturn.get(9).equals('k') && 
            arrayToReturn.get(10).equals('e') && 
            arrayToReturn.get(11).equals('w') && 
            arrayToReturn.get(12).equals('n')) {
        	assert(true);
            System.out.println("TEST CASE 2: PASSED");
        } else {
        	assert(false);
            System.out.println("TEST CASE 2: FAILED");
        }
    }
    
    
    // test insertion sort on ResizingArrayDictionary.java
    @Test
    public void test3() {
    	ResizingArrayDictionary dictionaryDataStructure = new ResizingArrayDictionary();
    	dictionaryDataStructure.CreateDictionary("src/Input/unsortedSmallExample.txt");
        String[] arr;
        dictionaryDataStructure.insertionSort(dictionaryDataStructure.getArray(), 30);
        arr = dictionaryDataStructure.getArray();

        boolean passed = true;
        File initialFile = new File("src/Input/sortedOutput.txt");
        InputStream targetStream;
        int count = 0;
        String word;
        try {
            targetStream = new FileInputStream(initialFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(targetStream));
            word = br.readLine();

            while (word != null) {
                if (!arr[count].equals(word)) {
                    passed = false;
                }
                count++;
                word = br.readLine();
            }
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (dictionaryDataStructure.getNumberOfElements() != 31) {
            passed = false;
        }

        if (passed) {
            System.out.println("TEST CASE 3: PASSED");
            assert(true);
        } else {
            System.out.println("TEST CASE 3: FAILED");
            assert(false);
        }
    }
    
	 // test insertion sort on sorted vs unsorted ArrayDictionary.java
    @Test
    public void test4() {
    	ResizingArrayDictionary dictionaryDataStructure = new ResizingArrayDictionary();
    	dictionaryDataStructure.CreateDictionary("src/Input/englishWords.txt");
        long start = 0;
        long finish = 0;
        start = System.currentTimeMillis();
        dictionaryDataStructure.insertionSort(dictionaryDataStructure.getDictionaryWords().toArray(new String[0]), 20000);
        finish = System.currentTimeMillis();
        long insertionTimeElapsed = finish - start;
        System.out.println("Time elapsed Insertion unsorted: " + insertionTimeElapsed);

        dictionaryDataStructure = new ResizingArrayDictionary();
        dictionaryDataStructure.CreateDictionary("src/Input/englishWordsSorted.txt");
        start = 0;
        finish = 0;
        start = System.currentTimeMillis();
        dictionaryDataStructure.insertionSort(dictionaryDataStructure.getDictionaryWords().toArray(new String[0]), 20000);
        finish = System.currentTimeMillis();
        long insertionTimeElapsedSorted = finish - start;
        System.out.println("Time elapsed Insertion sorted: " + insertionTimeElapsedSorted);

        if (insertionTimeElapsedSorted < insertionTimeElapsed) {
            System.out.println("TEST CASE 4: PASSED");
            assert(true);
        } else {
            System.out.println("TEST CASE 4: FAILED");
            assert(false);
        }
    }

    
    
    

}
