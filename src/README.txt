Nicholas Dinio - Project 3

There are four source files in this project. Three of then, Graph, Edge, and Vertex, are the three classes that perform all of the operations related to the graph. The fourth, Project_3 is where the main function is located and is where the program will need to be ran from. The program will allow for one additional argument in the form as as text file. The program will build the graph from this program. The program will wait for an command-line input  This project was written in the IntelliJ IDE.

The program will respond to several commands:

addedge tailvertex headvertex transmit_time
deleteedge tailvertex headvertex
path from_vertex to_vertex
reachable
print

All of these commands, if the correct parameters are used, will work correctly.

The reachable command implements a breath-first search to find all accessible nodes from each node. It then passes the data found into an insertion sort to sort the list of names alphabetically.


The path command implements Dijkstra's Algorithm to find the shortest possible path between any two nodes. A PriorityQueue is implemented to build a MinHeap to be used in Dijkstra's Algorithm