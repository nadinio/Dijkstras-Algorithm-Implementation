import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nicholas Dinio on 4/20/2015.
 */
public class Vertex
{
    String name;
    Set<Vertex> adj = new HashSet<Vertex>();

    Vertex(String nm) {name = nm;}          // Initializes Vertex with name

    // Overrides the default equals function to compare vertices
    //
    @Override
    public boolean equals(Object o)
    {
        Vertex test = (Vertex)o;
        return this.name.equals(test.name);
    }

    // Overrides the default hashCode function to work with HashSets
    //
    @Override
    public int hashCode()
    {
        int hash = name.hashCode();
        return hash;
    }

    public String getName()
    {
        return name;
    }       // Returns Vertex name
}