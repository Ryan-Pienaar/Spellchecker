package DictionaryTypes.TrieStructureComponents;

import java.util.ArrayList;

public class State {
    private String word;
    private boolean isEnd;
    private int index;
    private State currentState = this;

    private ArrayList<Edge> outgoingEdges = new ArrayList<>();

    /** Creates a new State.
     * @param NN The index number of the State.
     * @param accept If the State is at an accept state or not.
     * @param wordUpUntil The word that the state represents up until.
     */
    public State(int NN, boolean accept, String wordUpUntil)
    {
        word = wordUpUntil;
        isEnd = accept;
        index = NN;
    }

    /**
     * @return Returns an array containig all the outgoing Edges characters.
     */
    public ArrayList<Character> getOutgoingEdgesChar()
    {
        ArrayList<Character> c = new ArrayList<>();
        if (outgoingEdges.size() >= 0)
        {
            for (int i = 0; i < outgoingEdges.size(); i++)
            {
                c.add(outgoingEdges.get(i).getEdgeChar());
            }
            return c;
        }
        return null;
    }

    /**
     * @return Returns an ArrayList containing all the outgoing Edges.
     */
    public ArrayList<Edge> getOutgoingEdges()
    {
        return outgoingEdges;
    }

    /**
     * @return Returns an int value of the amount of outgoing Edges.
     */
    public int getNumbOfOutgoingEdges()
    {
        return outgoingEdges.size();
    }

    /** Checks if a State is in a accept state
     * @return Returns a boolean value if the word is in a accept state.
     */
    public boolean isEnd()
    {
        return isEnd;
    }

    public void changeAccpectState(boolean accept)
    {
        isEnd = accept;
    }

    /**
     * @return Returns a string containing the word that the State represents.
     */
    public String stateWord()
    {
        return word;
    }

    /** Removes a link between two States
     * @param s The state from which the Edge is pointing from.
     * @param ch The character of the Edge that needs to removed.
     */
    public void removeLink(State s, Character ch) {
        outgoingEdges.remove(0);
    }

    /** Adds a link between two States.
     * @param s The state from which a link is to be added.
     * @param ch The character that the Edge is representing.
     */
    public void addLink(State s, Character ch)
    {
        Edge currentEdge = new Edge(ch, s);
        outgoingEdges.add(currentEdge);
    }
}
