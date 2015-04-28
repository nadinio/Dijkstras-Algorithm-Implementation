/**
 * Created by Nicholas Dinio on 4/20/2015.
 */
public class Edge
{
    float weight;
    Vertex tailVertex;
    Vertex headVertex;

    Edge(Vertex tailVertex, Vertex headVertex, float weight)
    {
        this.tailVertex = tailVertex; this.headVertex = headVertex; this.weight = weight;  // Initialize variables
    }

    float getWeight() {return weight;}              // Returns edge weight


    // Overrides the default equals function to compare edges
    //
    @Override
    public boolean equals(Object obj)
    {
        return (tailVertex.equals(((Edge) obj).tailVertex) && headVertex.equals(((Edge) obj).headVertex));
    }
}