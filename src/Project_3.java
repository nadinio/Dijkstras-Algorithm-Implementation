import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by Nicholas on 4/16/2015.
 */

public class Project_3
{
    public static void main(String[] args)
    {
        Graph megaGraph = new Graph();
        try{
            megaGraph = readFile(args[0]);
        }
        catch(IOException e)
        {
            System.out.println("File not found");
        }

        System.out.println();


       /* Graph build = new Graph();
        Vertex v1 = new Vertex("Vertex");
        Vertex v2 = new Vertex("Vertex");

        build.addVertex(v1);
        build.addVertex(v2);
            */
        System.out.println();



    }

    public static Graph readFile(String filePath) throws IOException
    {
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);

        Graph graphBuilder = new Graph();

        for (String line; (line = br.readLine()) != null; ) {
            String [] splitString = line.split(" ", 3);
            Vertex new1 = new Vertex(splitString[0]);
            Vertex new2 = new Vertex(splitString[1]);
            Edge toAdd = new Edge(new1, new2, Float.valueOf(splitString[2]));
            graphBuilder.addVertex(new1);
            graphBuilder.addVertex(new2);
            graphBuilder.addEdge(toAdd);
        }

        System.out.println();

        return graphBuilder;
    }
}









