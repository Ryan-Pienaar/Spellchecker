import DictionaryTypes.*;

import java.util.Arrays;

public class SpellChecker {

    DictionaryInterface spellChecker;

    public SpellChecker(String checkerType) {
        if (checkerType.equals("Trie")) {
            spellChecker = new TrieDictionary();
        } else if (checkerType.equals("Array")) {
            spellChecker = new ArrayDictionary();
        } else if (checkerType.equals("ResizingArray")) {
            spellChecker = new ResizingArrayDictionary();
        } else {
            System.out.println("Invalid command line input, use one of: Trie, Array, or ResizingArray");
            System.exit(0);
        }
    }

    /** Checks if a word is spelled correctly.
     * @param word Takes a string input.
     * @return Returns a true or false based on if the word is spelled correctly or not.
     */
    public boolean check(String word)
    {
        if (spellChecker.isWord(word))
        {
            return true;
        }
        return false;
    }

    /**
     * @param wordToCheck The word to be checked.
     * @return Returns the best suggestion of how the program thinks the word is supposed to be spelled.
     */
    public String getBestSuggestion(String wordToCheck)
    {
        if (!this.check(wordToCheck))
        {
            return Arrays.toString(spellChecker.getTopNSuggestions(spellChecker.getDictionaryWords(), wordToCheck, 1));
        }
        return null;
    }
}
