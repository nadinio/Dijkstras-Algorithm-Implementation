import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nicholas on 4/20/2015.
 */
public class Graph
{
    Set<Vertex> nodes = new HashSet<Vertex>();
    Set<Edge> edges = new HashSet<Edge>();

    void addEdge(Edge e)
    {
        edges.add(e);
    }

    void addVertex(Vertex v)
    {
        nodes.add(v);
    }

    void deleteEdge(Edge a)
    {
        a.tailVertex.adj.remove(a); a.headVertex.adj.remove(a);
        edges.remove(a);
    }
}