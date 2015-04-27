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
        Object [] nodeBuilder = nodes.toArray();
        Vertex [] graphNodes = new Vertex[nodeBuilder.length];

        for (int i = 0; i < nodeBuilder.length; i++)
            graphNodes[i] = ((Vertex)nodeBuilder[i]);

        for(int i = 0; i < graphNodes.length; i++)
        {
            if (graphNodes[i].equals(headVertex)) {
                headVertex = graphNodes[i];
                graphNodes[i].adj.add(tailVertex);
            }
            if (graphNodes[i].equals(tailVertex)) {
                tailVertex = graphNodes[i];
                graphNodes[i].adj.add(headVertex);
            }
        }


        edges.add(new Edge(tailVertex, headVertex, weight));
        edges.add(new Edge(headVertex, tailVertex, weight));
    }

    void addVertex(Vertex v)
    {
        nodes.add(v);
    }

    void deleteEdge(Vertex headVertex, Vertex tailVertex)
    {

        Object [] nodeBuilder = nodes.toArray();
        Vertex [] graphNodes = new Vertex[nodeBuilder.length];

        for (int i = 0; i < nodeBuilder.length; i++)
            graphNodes[i] = ((Vertex)nodeBuilder[i]);

        for(int i = 0 ; i < graphNodes.length; i++) {
            if (headVertex.equals(graphNodes[i]))
                headVertex = graphNodes[i];
            if (tailVertex.equals(graphNodes[i]))
                tailVertex = graphNodes[i];
        }

        headVertex.adj.remove(tailVertex); tailVertex.adj.remove(headVertex);
        nodes.add(headVertex); nodes.add(tailVertex);

        Edge toRemove1 = new Edge(headVertex, tailVertex, 0);
        Edge toRemove2 = new Edge(tailVertex, headVertex, 0);

        Object [] edgeBuilder = edges.toArray();
        Edge [] graphEdges = new Edge[edgeBuilder.length];

        for (int i = 0; i < edgeBuilder.length; i++)
            graphEdges[i] = ((Edge)edgeBuilder[i]);

        for(int i = 0; i < graphEdges.length; i++) {
            if (graphEdges[i].headVertex.equals(headVertex) && graphEdges[i].tailVertex.equals(tailVertex))
                toRemove1 = graphEdges[i];
            if (graphEdges[i].tailVertex.equals(headVertex) && graphEdges[i].headVertex.equals(tailVertex))
                toRemove2 = graphEdges[i];
        }


        edges.remove(toRemove1); edges.remove(toRemove2);
    }

    void printGraph()
    {
        Vertex [] nodeArray = alphabeticalSort(nodes.toArray());
        Object [] edgeBuilder = edges.toArray();
        Edge [] graphEdges = new Edge[edgeBuilder.length];

        for (int i = 0; i < 0; i++)
            graphEdges[i] = ((Edge)edgeBuilder[i]);


        for(int i = 0; i < nodeArray.length; i++)
        {
            System.out.println((nodeArray[i]).name);
            Vertex [] adjArray = alphabeticalSort(nodeArray[i].adj.toArray());


            for (int j = 0; j < adjArray.length; j++) {
                float edgeWeight = getEdgeWeight(nodeArray[i], adjArray[j]);
                System.out.println("    " + (adjArray[j]).name + " " + edgeWeight);
            }

        }
    }

    void shortestPath(Vertex startPoint, Vertex endPoint)
    {

    }

    void reachable()
    {
        Vertex [] nodeArray = alphabeticalSort(nodes.toArray());

        for(int i = 0; i < nodeArray.length; i++)
        {
            System.out.println((nodeArray[i]).name);
            Vertex [] adjArray = alphabeticalSort(nodeArray[i].adj.toArray());


            for (int j = 0; j < adjArray.length; j++) {
                System.out.println("    " + (adjArray[j]).name);
            }

        }
    }

    Vertex getNode(String name)
    {
        Object [] toArray = nodes.toArray();
        Vertex[] vertArray = new Vertex[toArray.length];

        for(int i = 0; i < toArray.length; i++)
            vertArray[i] = ((Vertex)toArray[i]);


        for(int i = 0; i < toArray.length; i++)
            if(vertArray[i].getName().equals(name))
                return vertArray[i];

        return new Vertex("");
    }

    float getEdgeWeight(Vertex tailVertex, Vertex headVertex)
    {
        Object [] edgeBuilder = edges.toArray();
        Edge [] edgeArray = new Edge[edgeBuilder.length];

        for (int i = 0; i < edgeBuilder.length; i++)
            edgeArray[i] = ((Edge)edgeBuilder[i]);


        for(int i = 0; i < edgeArray.length; i++)
            if(edgeArray[i].headVertex.equals(headVertex) && edgeArray[i].tailVertex.equals(tailVertex))
                return edgeArray[i].getWeight();

        return 0;
    }

    Vertex[] alphabeticalSort(Object[] arrayIn)
    {
        Vertex[] toReturn = new Vertex[arrayIn.length];

        for (int i = 0; i < arrayIn.length; i++)        // Converting object Array to Vertex Array
            toReturn[i] = (Vertex)arrayIn[i];

        for (int i = 0; i < toReturn.length; i++)           // Performing insertion sort to sort the verticies.
            for (int j = i + 1; j < toReturn.length; j++)
            {
                if(toReturn[i].getName().compareTo(toReturn[j].getName()) > 0 )
                {
                    Vertex temp = toReturn[i];
                    toReturn[i] = toReturn[j];
                    toReturn[j] = temp;
                }
            }


        return toReturn;
    }
}