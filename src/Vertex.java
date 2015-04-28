import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nicholas Dinio on 4/20/2015.
 */
public class Vertex implements Comparable
{
    String name;
    Set<Vertex> adj = new HashSet<Vertex>();
    float distance = Float.POSITIVE_INFINITY;
    Vertex predecessor = null;

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

    public int compareTo(Object obj)
    {
       Vertex test = ((Vertex)obj);

        if (this.distance > test.distance)
            return 1;
        if (this.distance < test.distance)
            return -1;
        if(this.distance == test.distance)
            return 0;
        else
            return 0;
    }

    public String getName()
    {
        return name;
    }       // Returns Vertex name
}