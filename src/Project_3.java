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

    }

    ////////////////////////////////////////////////////////////////////////////////////////
    //             readFile() reads the input file and returns the built Graph            //
    ////////////////////////////////////////////////////////////////////////////////////////
    public static Graph readFile(String filePath) throws IOException
    {
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);

        Graph graphBuilder = new Graph();

        for (String line; (line = br.readLine()) != null; )
        {
            String [] splitString = line.split(" ", 3);            //splits each text entry.
            Vertex new1 = new Vertex(splitString[0]); Vertex new2 = new Vertex(splitString[1]);  //creates new nodes based on text entries
            graphBuilder.addVertex(new1); graphBuilder.addVertex(new2);         // adds nodes to the graph
            graphBuilder.addEdge(new1, new2, Float.valueOf(splitString[2]));
            graphBuilder.addEdge(new2, new1, Float.valueOf(splitString[2]));          // adds edges to the graph
        }

        fr.close(); br.close();         // closes file readers

        return graphBuilder;
    }
}









