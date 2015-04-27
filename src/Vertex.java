import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nicholas on 4/20/2015.
 */
public class Vertex
{
    String name;
    Set<Vertex> adj = new HashSet<Vertex>();

    Vertex(String nm) {name = nm;}

    @Override
    public boolean equals(Object o)
    {
        Vertex test = (Vertex)o;
        return this.name.equals(test.name);
    }

    @Override
    public int hashCode()
    {
        int hash = name.hashCode();
        return hash;
    }

    public String getName()
    {
        return name;
    }
}