package Lab10.zad1;
//Ваша задача е да креирате неориентиран нетежински граф со листа на соседство, каде темињата како информација содржат број. Графот го креирате според наредбите кои се добиваат. Ќе ви биде дадена низа од команди што можат да бидат од следните типови:
//
//CREATE - треба да креирате нов граф.
//
//ADDEDGE [број1] [број2] - треба да креирате ребро меѓу темињата со реден број број1 и реден број број2.
//
//DELETEEDGE [број1] [број2] - треба да го избришете реброто меѓу темињата со реден број број1 и реден број број2.
//
//ADЈACENT [број1] [број2] - треба да испечатите true доколку темињата со реден број број1 и реден број број2 се соседни, во спротивно false.
//
//PRINTGRAPH - Треба да ја испечатите листата на соседство
//
//Во првата линија на влезот е даден бројот на команди кои ќе следуваат.
//
///
//Your task is to create an unoriented unweighted graph by using an adjacency list, where each vertex information is аn integer. You create the graph according to the received commands. You will be given an array of commands that can be one of the following:
//
//CREATE - you should create a new graph.
//
//ADDEDGE [number1] [number2] - you should create an edge between the vertices with ordinal number number1 and ordinal number number2.
//
//DELETEEDGE [number1] [number2] - you should remove the edge between the vertices with ordinal number number1 and ordinal number number2.
//
//ADЈACENT [number1] [number2] - you should print true if the vertices with ordinal number number1 and ordinal number number2 are adjacent, otherwise print false.
//
//PRINTGRAPH - you should print the adjacency list.
//
//The number of commands is given in the first input line.
//
//
//For example:
//
//Input	Result
//5
//CREATE
//ADDEDGE 0 3
//PRINTGRAPH
//ADJACENT 0 2
//DELETEEDGE 3 0


//0: [3]
//3: [0]
//
//false
import java.util.*;
import java.util.Iterator;
import java.util.*;

class AdjacencyListGraph<T> {
    private Map<T, Set<T>> adjacencyList;
    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }
    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        // Remove the vertex from all adjacency lists
        for (Set<T> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        // Remove the vertex's own entry in the adjacency list
        adjacencyList.remove(vertex);
    }

    // Add an edge to the graph
    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source); // for undirected graph
    }

    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
        if (adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(source); // for undirected graph
        }
    }
    // Get all neighbors of a vertex
    public Set<T> getNeighbors (T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }

    public void print()
    {
        adjacencyList.forEach((x,y) ->
                System.out.printf("%s: %s\n", x.toString(), y));
    }

}

class prog{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String [] parts = line.split("\\s+");
            if (parts[0].equals("ADDEDGE")){
                graph.addEdge(Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
                continue;
            }
            if (parts[0].equals("DELETEEDGE")){
                graph.removeEdge(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                continue;
            }
            if (parts[0].equals("PRINTGRAPH")){
                graph.print();
                System.out.println();
            }
            if (parts[0].equals("ADJACENT")){
                System.out.println((graph.getNeighbors(Integer.parseInt(parts[1])).contains(Integer.parseInt(parts[2]))) ? "true" : "false");
            }
        }
    }
}


