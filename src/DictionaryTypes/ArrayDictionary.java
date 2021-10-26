package DictionaryTypes;

import DictionaryTypes.abstractClasses.ArrayTypeDictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayDictionary extends ArrayTypeDictionary implements DictionaryInterface {
    private String[] dictionary;
    private int size = 0;

    public ArrayDictionary()
    {
        dictionary = new String[30000];
    }

    /** Creates the dictionary.
     * @param filepath Specifies where the text document containing the dictionary words are located.
     */
    @Override
    public void CreateDictionary(String filepath) {

        File file = new File(filepath);
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        //int pos = 0;
        while (scanner.hasNextLine())
        {
            //dictionary[pos] = scanner.nextLine();
            //pos++;
            this.addNewWord(scanner.nextLine());
            size++;
            if (size == 30000)
            {
                break;
            }
        }
    }

    /** Adds a new word to the dictionary.
     * @param word The word to be added to the dictionary.
     */
    @Override
    public boolean isWord(String word) {
        for (int i = 0; i < size; i++)
        {
            if (dictionary[i] != null)
            {
                if (dictionary[i].equals(word))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /** Gives you all the words present in the dictionary.
     * @return Returns an array list containing all the words in the dictionary.
     */
    @Override
    public void addNewWord(String word) {
        if (!this.isWord(word))
        {
            int emptyPos = 0;
            while (dictionary[emptyPos] != null)
            {
                emptyPos++;
            }
            String string = word;
            dictionary[emptyPos] = string;
            size++;
        }
    }

    /** Removes a word from the dictionary.
     * @param word The word to be removed.
     */
    @Override
    public void removeWord(String word) {
        int count = 0;
        for (int i = 0; i < size; i++)
        {
            if (dictionary[i].equals(word))
            {
                dictionary[i] = null;
            }
        }
    }

    /** Gives you all the words present in the dictionary.
     * @return Returns an array list containing all the words in the dictionary.
     */
    @Override
    public ArrayList<String> getDictionaryWords() {
        ArrayList<String> dr = new ArrayList<String>();
        for (int i = 0; i < size; i++)
        {
            if (dictionary[i] != null)
            {
                dr.add(dictionary[i]);
            }
        }
        return dr;
    }

    /** Gives the amount of words present in the dictionary.
     * @return Returns an int value of the number of words in the dictionary.
     */
    @Override
    public int getNumberOfElements() {
        int count = 0;
        for (int i = 0; i < size; i++)
        {
            if (dictionary[i] != null)
            {
                count++;
            }
        }
        if (count > 0)
        {
            return count;
        }
        return 0;
    }
}
