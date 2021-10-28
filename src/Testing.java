import DictionaryTypes.ArrayDictionary;
import DictionaryTypes.ResizingArrayDictionary;
import DictionaryTypes.TrieDictionary;
import DictionaryTypes.abstractClasses.ArrayTypeDictionary;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Testing
{
    @Test
    public void shouldSortTwoLetterWords() {
        ArrayDictionary array = new ArrayDictionary();
        array.CreateDictionary("src/Input/testing.txt");
        //String[] arr = {"zz", "bb", "cb", "ba", "za", "zb", "cz", "ab", "aa"};
        List<String> words = array.getDictionaryWords();
        String[] wordsArray = new String[words.size()];
        for (int i = 0; i < words.size(); i++)
        {
            wordsArray[i] = words.get(i);
        }
        array.insertionSort(wordsArray, 5);
        Assert.assertEquals(wordsArray, new String[]{"aa", "ab", "ba", "bb", "cb", "cz", "za", "zb", "zz"});
    }

    public static void main(String[] args)
    {
        //ArrayDictionary array = new ArrayDictionary();
        //array.CreateDictionary("src/Input/testing.txt");
        String[] arr = { "sorrowfully", "photovoltaic", "assuaged", "rayon", "unrelieved", "effort", "except",
                "jumpstart", "magnetometers", "polygons" };
        //List<String> words = array.getDictionaryWords();
        //String[] wordsArray = new String[words.size()];
        //for (int i = 0; i < words.size(); i++)
        //{
        //    wordsArray[i] = words.get(i);
        //}

        new ResizingArrayDictionary().insertionSort(arr, 10);

        for (int i = 0; i < arr.length; i++)
        {
            System.out.println(arr[i]);
        }
    }
}
