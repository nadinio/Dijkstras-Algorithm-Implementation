import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nicholas on 4/20/2015.
 */
public class Vertex
{
    String name;
    Set<Edge> adj = new HashSet<Edge>();

    Vertex(String nm) {name = nm;}

    @Override
    public boolean equals(Object o)
    {
        Vertex test = (Vertex)o;
        return this.name == test.name;
    }

    @Override
    public int hashCode(){
        int hash = name.hashCode();
        return hash;
    }
}