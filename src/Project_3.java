import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


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


        Scanner sc = new Scanner(System.in);
        String command = "";

        while(!command.equals("quit"))
        {
            command = sc.nextLine();

            if(command.equals("print"))
                megaGraph.printGraph();

            if(command.equals("reachable"))
                megaGraph.reachable();


            String [] splitString = command.split(" ", 4);

            if(splitString[0].equals("addedge"))
                megaGraph.addEdge(new Vertex(splitString[1]), new Vertex(splitString[2]), Float.valueOf(splitString[3]));

            if(splitString[0].equals("deleteedge"))
                megaGraph.deleteEdge(new Vertex(splitString[1]), new Vertex(splitString[2]));

            if(splitString[0].equals("path"))
                megaGraph.shortestPath(new Vertex(splitString[1]), new Vertex(splitString[2]));

            if (!command.equals("quit"))
                command = "";
        }

        System.out.println("Program Exited");
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
            
            if(graphBuilder.nodes.contains(new1))
                new1 = graphBuilder.getNode(new1.getName());
            if(graphBuilder.nodes.contains(new2))
                new2 = graphBuilder.getNode(new2.getName());

            graphBuilder.addVertex(new1); graphBuilder.addVertex(new2);         // adds nodes to the graph
            graphBuilder.addEdge(new2, new1, Float.valueOf(splitString[2]));          // adds edges to the graph
        }

        fr.close(); br.close();         // closes file readers

        return graphBuilder;
    }
}









