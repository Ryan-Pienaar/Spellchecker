import DictionaryTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.nio.channels.*;
import org.junit.jupiter.api.Test;

/**
 * Just some test cases for a spellchecker
 *
 * @version 2.1
 * @author Noshy
 */
public class CorncobTest {

    static final int UNIQUE_WORDS = 58108;
    static final int ARR_MAX_WORDS = 30000;
    static final int LINES = 60000;

    static DictionaryInterface dict;
    static final String TEST_FILE_URL = "https://gist.githubusercontent.com/NotNoshy/1a464ce1d54a5fffb2a322321b26747b/raw/58ce8e1455234b7b62d58cfaab83f453c551cd0b/corncobInput.txt";
    static final String TEST_FILE = "corncobInput.txt";
    static final String EMPTY_TEST_FILE = "empty.txt";

    // Write code to return the array size of the ResizingArrayDictionary - you
    // could do this my making another method in your ResizingArrayDictionary or by
    // making the array public and reading its value here.
    private static int getResizingArrLen(ResizingArrayDictionary a) {
        // replace this with your own code
        return a.size();
    }

    // #region Run this to download the test files & create an empty file
    public static void main(String[] args) {
        if (!(new File(TEST_FILE).exists())) {
            try {
                System.out.println("File downloading...");
                ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(TEST_FILE_URL).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(TEST_FILE);
                fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
                System.out.println("File downloaded");
            } catch (Exception e) {
                System.err.println("File could not be downloaded");
            }
        }

        try {
            new File(EMPTY_TEST_FILE).createNewFile();
            System.out.println("Empty file created");
        } catch (Exception e) {
            System.err.println("Empty file could not be created");
        }

    }
    // #endregion

    // #region Helper methods
    private static boolean hasAllWords(String[] words, ArrayList<String> dictionary) {
        boolean has = true;
        for (String word : words) {
            has &= dictionary.contains(word);
        }
        return has;
    }
    // #endregion

    // General tests
    // #region Test 1: test create dictionary and check all words added
    @Test
    public void trieTest1() {
        dict = new TrieDictionary();
        dict.CreateDictionary(TEST_FILE);
        ArrayList<String> list = dict.getDictionaryWords();
        String[] words = { "abundantly", "crowbars", "danceable", "infinitives" };

        assertEquals(UNIQUE_WORDS, dict.getNumberOfElements());
        assertEquals(UNIQUE_WORDS, list.size());
        assertTrue(hasAllWords(words, list));
    }

    @Test
    public void arrTest1() {
        // Tests if words from first half are added
        dict = new ArrayDictionary();
        dict.CreateDictionary(TEST_FILE);
        ArrayList<String> list = dict.getDictionaryWords();
        String[] words = { "footballer", "deference", "commercialise", "surrogate" };

        assertEquals(ARR_MAX_WORDS, dict.getNumberOfElements());
        assertEquals(ARR_MAX_WORDS, list.size());
        assertTrue(hasAllWords(words, list));
    }

    @Test
    public void reArrTest1() {
        dict = new ResizingArrayDictionary();
        dict.CreateDictionary(TEST_FILE);
        ArrayList<String> list = dict.getDictionaryWords();
        String[] words = { "abundantly", "crowbars", "danceable", "infinitives" };

        assertEquals(UNIQUE_WORDS, dict.getNumberOfElements());
        assertEquals(UNIQUE_WORDS, list.size());
        assertTrue(hasAllWords(words, list));
        assertEquals(UNIQUE_WORDS + 5, getResizingArrLen((ResizingArrayDictionary) dict));
    }
    // #endregion

    // #region Test 2A: test empty input file
    @Test
    public void trieTest2A() {
        dict = new TrieDictionary();
        dict.CreateDictionary(EMPTY_TEST_FILE);
        ArrayList<String> list = dict.getDictionaryWords();

        assertEquals(0, dict.getNumberOfElements());
        assertEquals(0, list.size());
    }

    @Test
    public void arrTest2A() {
        dict = new ArrayDictionary();
        dict.CreateDictionary(EMPTY_TEST_FILE);
        ArrayList<String> list = dict.getDictionaryWords();

        assertEquals(0, dict.getNumberOfElements());
        assertEquals(0, list.size());
    }

    @Test
    public void reArrTest2A() {
        dict = new ResizingArrayDictionary();
        dict.CreateDictionary(EMPTY_TEST_FILE);
        ArrayList<String> list = dict.getDictionaryWords();

        assertEquals(0, dict.getNumberOfElements());
        assertEquals(0, list.size());
        ;
        assertEquals(5, getResizingArrLen((ResizingArrayDictionary) dict));
    }
    // #endregion

    // #region Test 2B: test empty input file and add words
    @Test
    public void trieTest2B() {
        dict = new TrieDictionary();
        dict.CreateDictionary(EMPTY_TEST_FILE);
        String[] words = { "shrimp", "shrimps", "chimp", "crimp", "ship", "shriek", "shrift", "shrill", "shrine",
                "shrink" };

        for (String word : words) {
            dict.addNewWord(word);
        }
        ArrayList<String> list = dict.getDictionaryWords();

        assertEquals(words.length, dict.getNumberOfElements());
        assertEquals(words.length, list.size());
        assertTrue(hasAllWords(words, list));
    }

    @Test
    public void arrTest2B() {
        dict = new ArrayDictionary();
        dict.CreateDictionary(EMPTY_TEST_FILE);
        String[] words = { "shrimp", "shrimps", "chimp", "crimp", "ship", "shriek", "shrift", "shrill", "shrine",
                "shrink" };

        for (String word : words) {
            dict.addNewWord(word);
        }
        ArrayList<String> list = dict.getDictionaryWords();

        assertEquals(words.length, dict.getNumberOfElements());
        assertEquals(words.length, list.size());
        assertTrue(hasAllWords(words, list));
    }

    @Test
    public void reArrTest2B() {
        dict = new ResizingArrayDictionary();
        dict.CreateDictionary(EMPTY_TEST_FILE);
        String[] words = { "shrimp", "shrimps", "chimp", "crimp", "ship", "shriek", "shrift", "shrill", "shrine",
                "shrink" };

        for (String word : words) {
            dict.addNewWord(word);
        }
        ArrayList<String> list = dict.getDictionaryWords();

        assertEquals(words.length, dict.getNumberOfElements());
        assertEquals(words.length, list.size());
        assertTrue(hasAllWords(words, list));
        assertEquals(10, getResizingArrLen((ResizingArrayDictionary) dict));
    }
    // #endregion

    // #region Test 2C: test no input file and add words
    @Test
    public void trieTest2C() {
        dict = new TrieDictionary();
        String[] words = { "shrimp", "shrimps", "chimp", "crimp", "ship", "shriek", "shrift", "shrill", "shrine",
                "shrink" };

        for (String word : words) {
            dict.addNewWord(word);
        }
        ArrayList<String> list = dict.getDictionaryWords();

        assertEquals(words.length, dict.getNumberOfElements());
        assertEquals(words.length, list.size());
        assertTrue(hasAllWords(words, list));
    }

    @Test
    public void arrTest2C() {
        dict = new ArrayDictionary();
        String[] words = { "shrimp", "shrimps", "chimp", "crimp", "ship", "shriek", "shrift", "shrill", "shrine",
                "shrink" };

        for (String word : words) {
            dict.addNewWord(word);
        }
        ArrayList<String> list = dict.getDictionaryWords();

        assertEquals(words.length, dict.getNumberOfElements());
        assertEquals(words.length, list.size());
        assertTrue(hasAllWords(words, list));
    }

    @Test
    public void reArrTest2C() {
        dict = new ResizingArrayDictionary();
        String[] words = { "shrimp", "shrimps", "chimp", "crimp", "ship", "shriek", "shrift", "shrill", "shrine",
                "shrink" };

        for (String word : words) {
            dict.addNewWord(word);
        }
        ArrayList<String> list = dict.getDictionaryWords();

        assertEquals(words.length, dict.getNumberOfElements());
        assertEquals(words.length, list.size());
        assertTrue(hasAllWords(words, list));
        assertEquals(10, getResizingArrLen((ResizingArrayDictionary) dict));
    }
    // #endregion

    // Test 3: skipped, content covered in other tests (isWord that is in
    // dictionary)

    // #region Test 4: test isWord that is not in dictionary
    @Test
    public void trieTest4() {
        dict = new TrieDictionary();
        String[] words = { "shrimp", "shrimps", "chimp", "crimp", "ship", "shriek", "shrift", "shrill", "shrine",
                "shrink" };

        for (String word : words) {
            dict.addNewWord(word);
        }
        assertFalse(dict.isWord("shrip"));
    }

    @Test
    public void arrTest4() {
        dict = new ArrayDictionary();
        String[] words = { "shrimp", "shrimps", "chimp", "crimp", "ship", "shriek", "shrift", "shrill", "shrine",
                "shrink" };

        for (String word : words) {
            dict.addNewWord(word);
        }
        assertFalse(dict.isWord("shrip"));
    }

    @Test
    public void reArrTest4() {
        dict = new ResizingArrayDictionary();
        String[] words = { "shrimp", "shrimps", "chimp", "crimp", "ship", "shriek", "shrift", "shrill", "shrine",
                "shrink" };

        for (String word : words) {
            dict.addNewWord(word);
        }
        assertFalse(dict.isWord("shrip"));
    }
    // #endregion

    // Test 5: skipped, content covered in other tests (test addNewWord)

    // #region Test 6: test removeWord
    @Test
    public void trieTest6() {
        dict = new TrieDictionary();
        String[] words = { "shrimp", "shrimp", "shrimps", "chimp", "crimp", "ship", "shriek", "shrift", "shrill",
                "shrine", "shrink" };

        for (String word : words) {
            dict.addNewWord(word);
        }
        dict.removeWord("shrimp");
        assertFalse(dict.isWord("shrimp"));
    }

    @Test
    public void arrTest6() {
        dict = new ArrayDictionary();
        String[] words = { "shrimp", "shrimp", "shrimps", "chimp", "crimp", "ship", "shriek", "shrift", "shrill",
                "shrine", "shrink" };

        for (String word : words) {
            dict.addNewWord(word);
        }
        dict.removeWord("shrimp");
        assertFalse(dict.isWord("shrimp"));
    }

    @Test
    public void reArrTest6() {
        dict = new ResizingArrayDictionary();
        String[] words = { "shrimp", "shrimp", "shrimps", "chimp", "crimp", "ship", "shriek", "shrift", "shrill",
                "shrine", "shrink" };

        for (String word : words) {
            dict.addNewWord(word);
        }
        dict.removeWord("shrimp");
        assertFalse(dict.isWord("shrimp"));
    }
    // #endregion

    // Tests 7, 8, 9 skipped. (levenshtein distance)

    // #region Test 10: test duplicate words from input file and add words twice
    @Test
    public void trieTest10() {
        dict = new TrieDictionary();
        dict.CreateDictionary(TEST_FILE);
        dict.addNewWord("firearm"); // word in file
        dict.removeWord("firearm");
        dict.removeWord("patriotic"); // duplicate word in file
        assertFalse(dict.isWord("patriotic"));
        assertFalse(dict.isWord("firearm"));
    }

    @Test
    public void arrTest10() {
        // Tests if words from first half are added
        dict = new ArrayDictionary();
        dict.CreateDictionary(TEST_FILE);
        dict.addNewWord("firearm"); // word in file
        dict.removeWord("firearm");
        dict.removeWord("patriotic"); // duplicate word in file
        assertFalse(dict.isWord("patriotic"));
        assertFalse(dict.isWord("firearm"));
    }

    @Test
    public void reArrTest10() {
        dict = new ResizingArrayDictionary();
        dict.CreateDictionary(TEST_FILE);
        dict.addNewWord("firearm"); // word in file
        dict.removeWord("firearm");
        dict.removeWord("patriotic"); // duplicate word in file
        assertFalse(dict.isWord("patriotic"));
        assertFalse(dict.isWord("firearm"));
    }
    // #endregion

    // ResizingArray tests
    // #region ResizingArrayInit: test that the resizing array initialises to size 5
    @Test
    public void resizingArrayInit() {
        dict = new ResizingArrayDictionary();
        assertEquals(5, getResizingArrLen((ResizingArrayDictionary) dict));
    }
    // #endregion

    // #region resizingArrayInitEmpty: test that the resizing array initialises to
    // size 5 after reading empty file
    @Test
    public void resizingArrayInitEmpty() {
        dict = new ResizingArrayDictionary();
        dict.CreateDictionary(EMPTY_TEST_FILE);
        assertEquals(5, getResizingArrLen((ResizingArrayDictionary) dict));
    }
    // #endregion

    // Trie tests
    // #region TireTestDFS: dfs using given example
    // (https://learn.sun.ac.za/mod/page/view.php?id=1574200)
    @Test
    public void trieTestDFS() {
        TrieDictionary trie = new TrieDictionary();
        String[] words = { "ab", "ba", "bo", "cd" };

        for (String word : words) {
            trie.addNewWord(word);
        }

        ArrayList<Character> list = trie.DFS();
        Character[] expected = { 'a', 'b', 'b', 'a', 'o', 'c', 'd' };
        Character[] output = new Character[list.size()];
        output = list.toArray(output);

        assertArrayEquals(expected, output);
    }
    // #endregion

    // #region TireTestBFS: bfs using given example
    // (https://learn.sun.ac.za/mod/page/view.php?id=1574200)
    @Test
    public void trieTestBFS() {
        TrieDictionary trie = new TrieDictionary();
        String[] words = { "ab", "ba", "bo", "cd" };

        for (String word : words) {
            trie.addNewWord(word);
        }

        ArrayList<Character> list = trie.BFS();
        Character[] expected = { 'a', 'b', 'c', 'b', 'a', 'o', 'd' };
        Character[] output = new Character[list.size()];
        output = list.toArray(output);

        assertArrayEquals(expected, output);
    }
    // #endregion

    // Sorting tests
    // #region insertionSort
    @Test
    public void insertionSort() {
        for (int i = 0; i < 12; i++) {
            String[] words = { "sorrowfully", "photovoltaic", "assuaged", "rayon", "unrelieved", "effort", "except",
                    "jumpstart", "magnetometers", "polygons" };

            String[] expected = Arrays.copyOf(words, words.length);
            Arrays.sort(expected, 0, Math.min(i + 1, expected.length));

            new ResizingArrayDictionary().insertionSort(words, i);
            assertArrayEquals(expected, words);
        }
    }

    // #endregion

    // #region mergeSort
    @Test
    public void mergeSort() {
        for (int i = 0; i < 12; i++) {
            String[] words = { "sorrowfully", "photovoltaic", "assuaged", "rayon", "unrelieved", "effort", "except",
                    "jumpstart", "magnetometers", "polygons" };

            String[] expected = Arrays.copyOf(words, words.length);
            Arrays.sort(expected, 0, Math.min(i + 1, expected.length));

            new ResizingArrayDictionary().mergeSort(words, i);
            assertArrayEquals(expected, words);
        }
    }
    // #endregion

    // #region hybridSort
    @Test
    public void hybridSort() {
        for (int i = 0; i < 12; i++) {
            for (int j = 1; j < 11; j++) {
                String[] words = { "sorrowfully", "photovoltaic", "assuaged", "rayon", "unrelieved", "effort", "except",
                        "jumpstart", "magnetometers", "polygons" };

                String[] expected = Arrays.copyOf(words, words.length);
                Arrays.sort(expected, 0, Math.min(i + 1, expected.length));

                new ResizingArrayDictionary().hybridSort(words, j, i);
                assertArrayEquals(expected, words);
            }
        }
    }
    // #endregion

}