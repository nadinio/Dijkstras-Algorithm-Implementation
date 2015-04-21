import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nicholas on 4/20/2015.
 */
public class Graph
{
    Set<Vertex> nodes = new HashSet<Vertex>();
    Set<Edge> edges = new HashSet<Edge>();

    void addEdge(Vertex tailVertex, Vertex headVertex, float weight)
    {
        if(nodes.contains(tailVertex))
            tailVertex.adj.add(headVertex);
        if(nodes.contains(headVertex))
            headVertex.adj.add(tailVertex);


        edges.add(new Edge(tailVertex, headVertex, weight));
    }

    void addVertex(Vertex v)
    {
        nodes.add(v);
    }

    void deleteEdge(Edge edge)
    {
        edge.tailVertex.adj.remove(edge.headVertex); edge.headVertex.adj.remove(edge.tailVertex);
        edges.remove(edge);
    }

    void printGraph()
    {
        Object [] nodeArray = nodes.toArray();
        for(int i = 0; i < nodeArray.length; i++)
        {
            System.out.println(((Vertex)nodeArray[i]).name);
            Object [] adjArray = ((Vertex)nodeArray[i]).adj.toArray();
            for (int j = 0; i < adjArray.length; j++)
            {
                System.out.println("    " + ((Vertex)adjArray[j]).name + " " + "DISTANCE");
            }
        }
    }

}