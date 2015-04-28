import java.util.*;

/**
 * Created by Nicholas Dinio on 4/20/2015.
 */
public class Graph
{
    Set<Vertex> nodes = new HashSet<Vertex>();
    Set<Edge> edges = new HashSet<Edge>();

    void addVertex(Vertex v) {nodes.add(v);}    // Adds a Vertex to the graph

    ////////////////////////////////////////////////////////////////////////////////////////////
    //                Adds Edge to graph and updates adjacency list                           //
    ////////////////////////////////////////////////////////////////////////////////////////////
    void addEdge(Vertex tailVertex, Vertex headVertex, float weight)
    {
        Vertex [] graphNodes = buildVertexArray(nodes.toArray());       // Converts the nodes to an array


        for(int i = 0; i < graphNodes.length; i++)
        {
            if (graphNodes[i].equals(headVertex)) {                     // Does the head vertex already exist?
                headVertex = graphNodes[i];                             // If so, work with that vertex
                //graphNodes[i].adj.add(tailVertex);                      // Also, update its adjacency list.
            }
            if (graphNodes[i].equals(tailVertex)) {                     // Does the tail vertex already exist?
                tailVertex = graphNodes[i];                             // If so, work with that vertex
                graphNodes[i].adj.add(headVertex);                      // Also, update its adjacency list.
            }
        }


        edges.add(new Edge(tailVertex, headVertex, weight));            // Add edge to graph
        //edges.add(new Edge(headVertex, tailVertex, weight));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    //                Deletes Edge from graph and updates adjacency list                      //
    ////////////////////////////////////////////////////////////////////////////////////////////
    void deleteEdge(Vertex tailVertex, Vertex headVertex)
    {
        Vertex [] graphNodes = buildVertexArray(nodes.toArray());       // Gets vertices as an array


        for(int i = 0 ; i < graphNodes.length; i++) {
            if (headVertex.equals(graphNodes[i]))                       // If headVertex already exists,
                headVertex = graphNodes[i];                             // work with that vertex
            if (tailVertex.equals(graphNodes[i]))                       // If tailVertex already exists,
                tailVertex = graphNodes[i];                             // work with that vertex
        }


        tailVertex.adj.remove(headVertex);                              // Update adjacency list
        nodes.add(headVertex); nodes.add(tailVertex);                   // Re-add vertices to graph.

        Edge toRemove1 = new Edge(headVertex, tailVertex, 0);           // Create mock edge


        Edge [] graphEdges = buildEdgeArray(edges.toArray());           // Get current edges

        // Finds edges that currently exists and sets them to be removed.
        //
        for(int i = 0; i < graphEdges.length; i++)
            if (graphEdges[i].headVertex.equals(headVertex) && graphEdges[i].tailVertex.equals(tailVertex))
                toRemove1 = graphEdges[i];


        edges.remove(toRemove1);                                          // Remove edge from the graph
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    //             Prints all vertices, their adjacency lists, and edge weights               //
    ////////////////////////////////////////////////////////////////////////////////////////////
    void printGraph()
    {
        Vertex [] nodeArray = alphabeticalSort(nodes.toArray());    // Gets vertices as an alphabetically sorted array

        for(int i = 0; i < nodeArray.length; i++)
        {
            System.out.println((nodeArray[i]).name);                // Prints node name.
            Vertex [] adjArray = alphabeticalSort(nodeArray[i].adj.toArray());      // Gets adjacency list as
                                                                                    // alphabetically sorted array
            for (int j = 0; j < adjArray.length; j++)
            {
                float edgeWeight = getEdgeWeight(nodeArray[i], adjArray[j]);        // Gets edge weight
                System.out.println("    " + (adjArray[j]).name + " " + edgeWeight); // Prints adjacency list with weight
            }
        }
        System.out.println("");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    //             Implements Dijkstra's Algorithm to find shortest path                      //
    ////////////////////////////////////////////////////////////////////////////////////////////
    void shortestPath(Vertex startPoint, Vertex endPoint)
    {
        Edge [] edgeArray = buildEdgeArray(edges.toArray());

        startPoint.distance = 0;

        for(int i = 0; i < edgeArray.length; i++)
            if(edgeArray[i].tailVertex.equals(startPoint))
            {
                edgeArray[i].headVertex.distance = edgeArray[i].weight;
                edgeArray[i].headVertex.predecessor = startPoint;
                nodes.add(edgeArray[i].headVertex);
            }

        // Build a min heap based off of distance
        PriorityQueue<Vertex> minHeap = new PriorityQueue<Vertex>();

        Vertex [] vertexArray = buildVertexArray(nodes.toArray());

        for(int i = 0; i < vertexArray.length; i++)
            minHeap.add(vertexArray[i]);


        // Do previous loop with smallest distance

        while(minHeap.size() != 0) {
            for (int i = 0; i < edgeArray.length; i++)
                if (edgeArray[i].tailVertex.equals(minHeap.peek())) {
                    if(edgeArray[i].weight + edgeArray[i].tailVertex.distance < edgeArray[i].headVertex.distance)
                    {
                        edgeArray[i].headVertex.distance = edgeArray[i].weight + edgeArray[i].tailVertex.distance;
                        edgeArray[i].headVertex.predecessor = minHeap.peek();
                        nodes.add(edgeArray[i].headVertex);
                    }
                }
            minHeap.remove(minHeap.peek());
        }

        vertexArray = buildVertexArray(nodes.toArray());

        for(int i = 0; i < vertexArray.length; i++)
            if(vertexArray[i].equals(endPoint))
                endPoint = vertexArray[i];

        Vertex tempVert = endPoint;
        String builderString = "";

        while(tempVert != null)
        {
            builderString = tempVert.name + " " + builderString ;
            tempVert = tempVert.predecessor;
        }

        System.out.println(builderString + endPoint.distance + "\n");

        for (int i = 0; i < vertexArray.length; i++)
        {
            vertexArray[i].predecessor = null;
            vertexArray[i].distance = Float.POSITIVE_INFINITY;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    //             Prints all nodes and what nodes they can reach                             //
    ////////////////////////////////////////////////////////////////////////////////////////////
    void reachable()
    {
        Vertex [] nodeArray = alphabeticalSort(nodes.toArray());            // Gets nodes as an Array
        Edge [] edgesArray = buildEdgeArray(edges.toArray());
        Stack<Vertex> dfs = new Stack<Vertex>();

        HashSet<Vertex> inStack = new HashSet<Vertex>();
        LinkedList<Vertex> printList = new LinkedList<Vertex>();

        for(int i = 0; i < nodeArray.length; i++)
        {
            dfs.add(nodeArray[i]);
            inStack.add(nodeArray[i]);

            Vertex startNode = dfs.peek();
            while(!dfs.empty())
            {
                for(int j = 0; j < edgesArray.length; j++)
                    if(edgesArray[j].tailVertex.equals(startNode))
                    {
                        if(!inStack.contains(edgesArray[j].headVertex)) {
                            dfs.add(edgesArray[j].headVertex);
                            inStack.add(edgesArray[j].headVertex);
                        }
                    }
                startNode = dfs.peek();
                printList.add(dfs.pop());
            }
            System.out.println(nodeArray[i].name);
            for(int j = 0; j < printList.size(); j++)
                System.out.println("   " + printList.get(j).name);
            printList = new LinkedList<Vertex>();
            inStack = new HashSet<Vertex>();
        }

        System.out.println("");
    }



    ////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                        //
    //------------------------------ Graph Helper Functions ----------------------------------//
    //                                                                                        //
    ////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////
    //                             Returns a Vertex by name.                                  //
    ////////////////////////////////////////////////////////////////////////////////////////////
    Vertex getNode(String name)
    {
        Vertex[] vertArray = buildVertexArray(nodes.toArray());         // Gets vertices as Array

        for(int i = 0; i < vertArray.length; i++)
            if(vertArray[i].getName().equals(name))                     // Finds vertex by name.
                return vertArray[i];                                    // Returns vertex.

        return new Vertex("");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    //                             Returns Weight of an edge                                  //
    ////////////////////////////////////////////////////////////////////////////////////////////
    float getEdgeWeight(Vertex tailVertex, Vertex headVertex)
    {
        Edge [] edgeArray = buildEdgeArray(edges.toArray());            // Gets all edges as an Array

        // Finds a specific edge in the array and returns its weight
        //
        for(int i = 0; i < edgeArray.length; i++)
            if(edgeArray[i].headVertex.equals(headVertex) && edgeArray[i].tailVertex.equals(tailVertex))
                return edgeArray[i].getWeight();

        return 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    //                Returns an array of vertices sorted alphabetically                      //
    ////////////////////////////////////////////////////////////////////////////////////////////
    Vertex[] alphabeticalSort(Object[] arrayIn)
    {
        Vertex[] toReturn = buildVertexArray(arrayIn);      // Gets vertices as an array

        for (int i = 0; i < toReturn.length; i++)           // Performing insertion sort to sort the vertices.
            for (int j = i + 1; j < toReturn.length; j++)
            {
                if(toReturn[i].getName().compareTo(toReturn[j].getName()) > 0 )
                {
                    Vertex temp = toReturn[i];              // Uses insertion sort to sort vertices alphabetically
                    toReturn[i] = toReturn[j];
                    toReturn[j] = temp;
                }
            }


        return toReturn;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    //                           Returns an array of all vertices                             //
    ////////////////////////////////////////////////////////////////////////////////////////////
    Vertex[] buildVertexArray(Object[] arrayIn)
    {
        Vertex[] toReturn = new Vertex[arrayIn.length];

        for (int i = 0; i < toReturn.length; i++)
            toReturn[i] = ((Vertex)arrayIn[i]);                    // Converts each item in the object array
                                                                   // into a Vertex object
        return toReturn;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    //                           Returns an array of all edges                                //
    ////////////////////////////////////////////////////////////////////////////////////////////
    Edge[] buildEdgeArray(Object[] arrayIn)
    {
        Edge[] toReturn = new Edge[arrayIn.length];

        for (int i = 0; i < toReturn.length; i++)
            toReturn[i] = ((Edge)arrayIn[i]);                    // Converts each item in the object array
                                                                 // into a Edge object
        return toReturn;
    }
}