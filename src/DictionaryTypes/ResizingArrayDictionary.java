package DictionaryTypes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.reflect.Array;

import DictionaryTypes.abstractClasses.ArrayTypeDictionary;

public class ResizingArrayDictionary extends ArrayTypeDictionary implements DictionaryInterface {

    private String[] dictionary;
    private ArrayList<String> buffer = new ArrayList<String>();
    private int size = 0;
    private int max = 5;

    public ResizingArrayDictionary() {
        dictionary = new String[5];
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

        while (scanner.hasNextLine())
        {
            String word = scanner.nextLine();
            if (!buffer.contains(word))
            {
                buffer.add(word);
                size++;
            }
        }

        max = size + 5;
        resizeArray(); //Resizes the array accordingly

        for (int i = 0; i < buffer.size(); i++)
        {
            dictionary[i] = buffer.get(i);
        }
        buffer.clear();
    }

    /** Resizes the array accordingly
     * @return Returns the resized array containing all the element
     */
    private String[] resizeArray() {

        int a = Math.round(max / 4);

        if (size == max)
        {
            max = max * 2;
        }

        if (max > 5 && size <= a)
        {
            if (a < 5)
            {
                max = 5;
            }
            else
            {
                max = max / 2;
            }
        }

        dictionary = new String[max];

        return dictionary;
    }

    /** Checks to see if a word is in the dictionary.
     * @param word The word to be checked.
     * @return Returns true or false based on if the word is present in the dictionary.
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
        if(!this.isWord(word))
        {
            if (size == max)
            {
                for (int i = 0; i < max; i++)
                {
                    buffer.add(dictionary[i]);
                    dictionary[i] = null;
                }

                resizeArray();

                for (int i = 0; i < size; i++)
                {
                    dictionary[i] = buffer.get(i);
                }
                buffer.clear();
            }

            dictionary[size] = word;
            size++;
        }
    }

    /** Removes a word from the dictionary.
     * @param word The word to be removed.
     */
    @Override
    public void removeWord(String word) {

        for (int i = 0; i < size; i++)
        {
            if (dictionary[i] != null)
            {
                if (dictionary[i].equals(word))
                {
                    dictionary[i] = null;
                    size--;
                }
            }
        }

        int a = Math.round(max / 4);

        for (int i = 0; i < max; i++)
        {
            if (dictionary[i] != null)
            {
                buffer.add(dictionary[i]);
                dictionary[i] = null;
            }
        }

        resizeArray();

        for (int i = 0; i < buffer.size(); i++)
        {

            dictionary[i] = buffer.get(i);
        }
        buffer.clear();

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

    //For Unit Testing purposes.
    public String[] getArray()
    {
        return dictionary;
    }

}
