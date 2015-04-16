import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * Created by Nicholas on 4/16/2015.
 */

class Vertex
{
    String name;
    HashSet<Edge> adj;

    Vertex(String nm) {name = nm;}
}

class Edge
{
    float weight;
    Vertex tailVertex;
    Vertex headVertex;

    Edge(Vertex tailVertex, Vertex headVertex, float weight)
    {this.tailVertex = tailVertex; this.headVertex = headVertex; this.weight = weight;}
}

class Graph
{
    HashSet<Vertex> nodes;
    HashSet<Edge> edges;

    void addEdge(Vertex tail, Vertex head, float weight)
    {
        tail.adj.add(new Edge(tail,head,weight));
        head.adj.add(new Edge(head,tail,weight));
        nodes.add(tail);
        nodes.add(head);
        edges.add(new Edge(tail,head,weight));
        edges.add(new Edge(head,tail,weight));
    }

    void deleteEdge(Edge a)
    {
        a.tailVertex.adj.remove(a); a.headVertex.adj.remove(a);
        edges.remove(a);
    }
}


public class Project_3
{
    public static void main(String[] args)
    {
        try{
            Graph megaGraph = readFile(args[0]);
        }
        catch(IOException e)
        {
            System.out.println("File not found");
        }

    }

    public static Graph readFile(String filePath) throws IOException
    {
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);



        return new Graph();
    }
}
