package DictionaryTypes.TrieStructureComponents;

public class Edge {
    private char c;
    private State currentState;
    private State childState;
    public boolean visited = false;

    /** Creates a Edge which points to the next state.
     * @param ch The character that the Edge contains.
     * @param s The state to which the Edge points.
     */
    public Edge(Character ch, State s) {
        c = ch;
        childState = s;
    }

    /**
     * @return Returns the State which the Edge points to
     */
    public State getChildState()
    {
        return childState;
    }

    /**
     * @return Returns the character which the Edge represents.
     */
    public Character getEdgeChar()
    {
        return c;
    }
}
