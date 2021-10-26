package DictionaryTypes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import DictionaryTypes.TrieStructureComponents.Edge;
import DictionaryTypes.TrieStructureComponents.State;
import DictionaryTypes.abstractClasses.Dictionary;

public class TrieDictionary extends Dictionary implements DictionaryInterface {

    private final State startState;
    private State current;
    private int index = 0;

    public TrieDictionary()
    {
        startState = new State(index, false, "");
        current = startState;
        index++;
    }

    public ArrayList<Character> BFS()
    {
        ArrayList<Character> bfs = new ArrayList<>();
        Queue<State> queue = new LinkedList<State>();
        ArrayList<Character> sortList = new ArrayList<>();
        queue.add(startState);
        while (queue.size() > 0)
        {
            current = queue.poll();
            for (int i = 0; i < current.getNumbOfOutgoingEdges(); i++)
            {
                queue.add(current.getOutgoingEdges().get(i).getChildState());
                sortList.add(current.getOutgoingEdges().get(i).getEdgeChar());
            }
            for (int i = 0; i < sortList.size(); i++)
            {
                bfs.add(sortList.get(0));
                sortList.remove(0);
            }
        }
        current = startState;
        return bfs;
    }

    public ArrayList<Character> DFS()
    {
        ArrayList<Character> dfs = new ArrayList<>();
        ArrayList<Edge> queue = new ArrayList<Edge>();
        for (int i = 0; i < startState.getNumbOfOutgoingEdges(); i++)
        {
            queue.add(startState.getOutgoingEdges().get(i));
            while (!queue.isEmpty())
            {
                Edge element = queue.get(0);
                queue.remove(0);
                if (!element.visited)
                {
                    dfs.add(element.getEdgeChar());
                    element.visited = true;
                }
                List<Edge> children = element.getChildState().getOutgoingEdges();
                for (int j = 0; j < children.size(); j++)
                {
                    Edge n = children.get(j);
                    if (n != null && !n.visited)
                    {
                        queue.add(n);
                    }
                }
            }
        }
        return dfs;
    }

    /** Removes a word from the dictionary.
     * @param word The word to be removed.
     */
    @Override
    public void removeWord(String word) {
        Queue<State> queue = new LinkedList<State>();
        queue.add(startState);
        while (queue.size() > 0)
        {
            current = queue.poll();
            if(current.isEnd() && current.stateWord().equals(word))
            {
               current.changeAccpectState(false );
            }
            for (int i = 0; i < current.getNumbOfOutgoingEdges(); i++)
            {
                queue.add(current.getOutgoingEdges().get(i).getChildState());
            }
        }
    }

    /** Creates the dictionary.
     * @param filePath Specifies where the text document containing the dictionary words are located.
     */
    @Override
    public void CreateDictionary(String filePath)
    {
        File file = new File(filePath);
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        int pos = 0;
        while (scanner.hasNextLine())
        {
            this.addNewWord(scanner.nextLine());
        }
        current = startState;
    }

    /** Checks to see if a word is in the dictionary.
     * @param word The word to be checked.
     * @return Returns true or false based on if the word is present in the dictionary.
     */
    @Override
    public boolean isWord(String word)
    {
        return getDictionaryWords().contains(word);
    }

    /** Adds a new word to the dictionary.
     * @param w The word to be added to the dictionary.
     */
    @Override
    public void addNewWord(String w)
    {
        if (!this.isWord(w))
        {
            int increment = 0;
            for (int i = 0; i < w.length(); i++)
            {
                boolean accept = w.substring(0, i + 1).equals(w);
                Character c = w.charAt(i + increment);
                int edgeNumb = -1;
                for (int j = 0; j < current.getOutgoingEdges().size(); j++)
                {
                    if (c == current.getOutgoingEdges().get(j).getEdgeChar())
                    {
                        edgeNumb = j;
                    }
                }
                if (edgeNumb != -1)
                {
                    current = current.getOutgoingEdges().get(edgeNumb).getChildState();
                }
                else if (edgeNumb == -1)
                {
                    State child = new State(index, accept, w.substring(0, i + 1));
                    current.addLink(child, c);
                    current = child;
                    index++;
                }
                if (accept)
                {
                    current.changeAccpectState(true);
                    current = startState;
                }
            }
        }
    }

    /** Gives you all the words present in the dictionary.
     * @return Returns an array list containing all the words in the dictionary.
     */
    @Override
    public ArrayList<String> getDictionaryWords()
    {
        ArrayList<String> words = new ArrayList<>();
        Queue<State> queue = new LinkedList<State>();
        State checkState = startState;
        queue.add(checkState);
        while (queue.size() > 0)
        {
            checkState = queue.poll();
            if(checkState.isEnd())
            {
                words.add(checkState.stateWord());
            }
            for (int i = 0; i < checkState.getNumbOfOutgoingEdges(); i++)
            {
                queue.add(checkState.getOutgoingEdges().get(i).getChildState());
            }
        }
        return words;
    }

    /** Gives the amount of words present in the dictionary.
     * @return Returns an int value of the number of words in the dictionary.
     */
    @Override
    public int getNumberOfElements()
    {
        if (getDictionaryWords().size() > 0)
        {
            return getDictionaryWords().size();
        }
        return 0;
    }

    /** Finds the longest common prefix in the dictionary.
     * @param word The word to be compared to other words in the dictionary.
     * @return Returns a string containing the longest common prefix.
     */
    public String findCommonPrefix(String word)
    {
        int size = getDictionaryWords().size();
        String[] arr = new String[size];
        String[] prefixes = new String[size];
        String commonPrefix = "";
        for (int i = 0; i < size; i++)
        {
            arr[i] = getDictionaryWords().get(i);
        }
        for (int i = 0; i < size; i++)
        {
            prefixes[i] = commonPrefixUtil(word, arr[i]);
        }

        int index = 0;
        int elementLength = prefixes[0].length();
        for(int i=1; i< prefixes.length; i++) {
            if(prefixes[i].length() > elementLength) {
                index = i; elementLength = prefixes[i].length();
            }
        }
        commonPrefix = prefixes[index];

        return commonPrefix;
    }

    /** Compares two strings to find the longest common prefix.
     * @param s1 First string to be compared.
     * @param s2 Second string to be compares to.
     * @return Returns a string containing the longest common prefix.
     */
    private String commonPrefixUtil(String s1, String s2)
    {
        StringBuilder result = new StringBuilder();
        int a = s1.length(), b = s2.length();

        for (int i = 0, j = 0; i <= a - 1 && j <= b - 1; i++, j++) {
            if (s1.charAt(i) != s2.charAt(j)) {
                break;
            }
            result.append(s1.charAt(i));
        }
        return (result.toString());
    }

}
