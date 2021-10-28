package DictionaryTypes.abstractClasses;

import DictionaryTypes.DictionaryInterface;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Dictionary implements DictionaryInterface
{

    /** Gets the top N suggestions for a word out of the dictionary that has been misspelled.
     *
     * @param dictionaryWords Takes a dictionarie's word to find the best suggestions.
     * @param word The misspelled word to be checked.
     * @param N The amount of top suggestions to be returned.
     * @return Returns a string array containing the top suggestions.
     */
    public String[] getTopNSuggestions(ArrayList<String> dictionaryWords, String word, int N) {
        ArrayList<Integer> levDist = new ArrayList<Integer>();
        String[] bestSuggestions;
        if (dictionaryWords.size() < N) {
            bestSuggestions = new String[dictionaryWords.size()];
        }
        else {
            bestSuggestions = new String[N];
        }

        for (int i = 0; i < dictionaryWords.size(); i++)
        {
            levDist.add(getLevenshteinDistance(word, dictionaryWords.get(i)));
        }
        int i = 0;
        int currentDist = 0;
        int index = 0;

        while (i < dictionaryWords.size())
        {
            if (levDist.get(index) == currentDist)
            {
                bestSuggestions[i] = dictionaryWords.get(index);
                i++;
            }
            index++;
            if (index == levDist.size())
            {
                index = 0;
                currentDist++;
            }
        }
        return bestSuggestions;
    }

    /** Gets the Levenshtein distance between two words.
     * @param wrongWord Wrong word .
     * @param actualWord Actual word.
     * @return Returns an int value representing the Levenshtein distance between the two words.
     */
    public int getLevenshteinDistance(String wrongWord, String actualWord) {
        if (wrongWord.isEmpty()) {
            return actualWord.length();
        }

        if (actualWord.isEmpty()) {
            return wrongWord.length();
        }

        int replace = getLevenshteinDistance(wrongWord.substring(1), actualWord.substring(1)) + replaceAmount(wrongWord.charAt(0), actualWord.charAt(0));
        int delete = getLevenshteinDistance(wrongWord.substring(1), actualWord) + 1;
        int insert = getLevenshteinDistance(wrongWord, actualWord.substring(1)) + 1;

        int[] minNumber = {replace, delete, insert};

        return Arrays.stream(minNumber).min().orElse(Integer.MAX_VALUE);
    }

    /** Method to check the amount of characters that need to be replaced
     * @param a First word
     * @param b Second word
     * @return Returns the amount of words that need to be replced for word a to be the same as word b.
     */
    private int replaceAmount(char a, char b)
    {
        return a == b ? 0 : 1;
    }
    
}
