import DictionaryTypes.ArrayDictionary;
import DictionaryTypes.ResizingArrayDictionary;
import DictionaryTypes.TrieDictionary;
import DictionaryTypes.abstractClasses.ArrayTypeDictionary;

import java.util.ArrayList;
import java.util.List;

public class Testing
{
    public static void main(String[] args)
    {
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

        System.out.println(arrayToReturn);
    }
}
