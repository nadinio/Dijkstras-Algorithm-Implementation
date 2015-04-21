/**
 * Created by Nicholas on 4/20/2015.
 */
public class Edge
{
    float weight;
    Vertex tailVertex;
    Vertex headVertex;

    Edge(Vertex tailVertex, Vertex headVertex, float weight)
    {
        this.tailVertex = tailVertex; this.headVertex = headVertex; this.weight = weight;
        tailVertex.adj.add(headVertex); headVertex.adj.add(tailVertex);
    }


}