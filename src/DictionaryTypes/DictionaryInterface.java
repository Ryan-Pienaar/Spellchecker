package DictionaryTypes;
import java.util.ArrayList;

public interface DictionaryInterface {
    /**
     * This method reads in a list of words from a file in the specified filepath and creates a dictionary consisting of those words.
     * @param filepath The path of where the file with the words is.
     */
    void CreateDictionary(String filepath);

    /**
     * A method to check whether the specified word is in the dictionary or not.
     * @param word The word to be checked
     * @return Returns true if the word is in the dictionary and false if not.
     */
    boolean isWord(String word);

    /**
     * Adds a new word to the current dicitonary. The method first checks if the word to be added is already in the dicitonary by using the isWord() method.
     * If the word is not in the dictionary it is to be added and if it is then the method does nothing.
     * @param word The word to be added to the dictionary.
     */
    void addNewWord(String word);

    /**
     * Removes the specified word from the dictionary if the dictionary contains the specified word.
     * @param word The word to be removed from the dictionary.
     */
    void removeWord(String word);

    /**
     * This method searches and returns an ArrayList conatining all the words in the dictionary.
     * @return Returns an ArrayList containing all the dictionary words.
     */
    ArrayList<String> getDictionaryWords();

    /**
     * This method returns a String array which contains the specified amount of top suggestions for a misspelled word.
     * @param dictionaryWords The dictionary to be looked through.
     * @param word The misspelled word.
     * @param N The integer amount of words to be returned.
     * @return Returns a String array containing the top N specified words for the misspelled word.
     */
    String[] getTopNSuggestions(ArrayList<String> dictionaryWords, String word, int N);

    /**
     * Compares two strings and gets the levenshtein distaince between the two words.
     * @param wrongWord The wrong word.
     * @param actualWord The correctly spelled word.
     * @return The minimum amount of changes required to make the wrong word correctly spelled (The Levenshtein Distance).
     */
    int getLevenshteinDistance(String wrongWord, String actualWord);

    /**
     * Searches through the dictionary and returns the amount of words/elements in the dictionary.
     * @return Returns an integer value of the amount of words/elements in the dictionary.
     */
    int getNumberOfElements();
}
