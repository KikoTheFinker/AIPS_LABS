package Lab10.zad2;
//Нека е даден совршен лавиринт (во форма како на аудиториските вежби - како влез од карактери). Ваша задача е да изгенерирате граф со матрица на соседство (ненасочен и нетежински) од дадениот влез и да ја испечатите патеката од почетното (темето означено со S) до крајното теме (темето означено со Е).
//
//Патеката ја печатите на следниот начин: ги печатите координатите на јазлите кои ги изминувате (т.е ги печатите позициите од влезот)
//
///
//
//It is given a perfect maze (in the same form as presented on the aud. exercises - input of characters). Your task is to create a graph (unoriented and unweighted) by using the adjacency matrix from the given input and to print the path from the initial vertex (vertex denoted by S) to the final vertex (vertex denoted by E).
//
//Print the path as follows: print the coordinates of the unfolded vertices (i.e. print the positions from the input).
//
//
//
//For example:
//
//Input	Result
//6,6
//######
//#S# E#
//# # ##
//#   ##
//######
//######

//1,1
//2,1
//3,1
//3,2
//3,3
//2,3
//1,3
//1,4
import java.util.*;

class AdjacencyMatrixGraph<T> {
    private int numVertices;
    private int[][] matrix;
    private T[] vertices;

    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(int numVertices) {
        this.numVertices = numVertices;
        matrix = new int[numVertices][numVertices];
        vertices = (T[]) new Object[numVertices];
    }

    public void addVertex(int index, T data) {
        vertices[index] = data;
    }

    public T getVertex(int index) {
        return vertices[index];
    }

    public void addEdge(int source, int destination) {
        matrix[source][destination] = 1;
        matrix[destination][source] = 1; // For undirected graph
    }

    public boolean isEdge(int source, int destination) {
        return matrix[source][destination] == 1;
    }


    public void removeEdge(int source, int destination) {
        matrix[source][destination] = 0;
        matrix[destination][source] = 0; // For undirected graph
    }

    @SuppressWarnings("unchecked")
    public void removeVertex(int vertexIndex) {
        if (vertexIndex < 0 || vertexIndex >= numVertices) {
            throw new IndexOutOfBoundsException("Vertex index out of bounds!");
        }
        int[][] newMatrix = new int[numVertices - 1][numVertices - 1];
        T[] newVertices = (T[]) new Object[numVertices - 1];
        // Copy the vertices and matrix excluding the given vertex
        int ni = 0;
        for (int i = 0; i < numVertices; i++) {
            if (i == vertexIndex) continue;
            int nj = 0;
            for (int j = 0; j < numVertices; j++) {
                if (j == vertexIndex) continue;
                newMatrix[ni][nj] = matrix[i][j];
                nj++;
            }
            newVertices[ni] = vertices[i];
            ni++;
        }
        // Replace the old matrix and vertices with the new ones
        matrix = newMatrix;
        vertices = newVertices;
        numVertices--;
    }

    public List<T> getNeighbors(int vertexIndex) {
        List<T> neighbors = new ArrayList<>();
        for (int i = 0; i < matrix[vertexIndex].length; i++) {
            if (matrix[vertexIndex][i] == 1) {
                neighbors.add(vertices[i]);
            }
        }
        return neighbors;
    }


    public AdjacencyListGraph<T> toAdjacencyList() {
        AdjacencyListGraph<T> result = new AdjacencyListGraph<>();

        for (int i = 0; i < numVertices; i++) {
            result.addVertex(vertices[i]);
        }

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (matrix[i][j] > 0) {
                    result.addEdge(vertices[i], vertices[j]);
                }
            }
        }
        return result;
    }


    @Override
    public String toString() {
        String ret = "  ";
        for (int i = 0; i < numVertices; i++)
            ret += vertices[i] + " ";
        ret += "\n";
        for (int i = 0; i < numVertices; i++) {
            ret += vertices[i] + " ";
            for (int j = 0; j < numVertices; j++)
                ret += matrix[i][j] + " ";
            ret += "\n";
        }
        return ret;
    }

}

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
    public Set<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }

    public void DFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        DFSUtil(startVertex, visited);
    }

    private void DFSUtil(T vertex, Set<T> visited) {
        // Mark the current node as visited and print it
        visited.add(vertex);
        System.out.print(vertex + " ");

        // Recur for all the vertices adjacent to this vertex
        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                DFSUtil(neighbor, visited);
            }
        }
    }


    public void DFSNonRecursive(T startVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(startVertex);
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                System.out.print(vertex + " ");
                for (T neighbor : getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public void BFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            System.out.print(vertex + " ");

            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public void findPath(T startVertex, T endVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> invertedPath = new Stack<>();
        visited.add(startVertex);
        invertedPath.push(startVertex);

        while(!invertedPath.isEmpty() && !invertedPath.peek().equals(endVertex)) {
            T currentVertex = invertedPath.peek();
            T tmp = currentVertex;

            for(T vertex : getNeighbors(currentVertex)) {
                tmp = vertex;
                if(!visited.contains(vertex)) {
                    break;
                }
            }

            if(!visited.contains(tmp)) {
                visited.add(tmp);
                invertedPath.push(tmp);
            }
            else {
                invertedPath.pop();
            }
        }

        Stack<T> path = new Stack<>();
        while(!invertedPath.isEmpty()) {
            path.push(invertedPath.pop());
        }
        while(!path.isEmpty()) {
            System.out.println(path.pop());
        }
    }

    @Override
    public String toString() {
        String ret = new String();
        for (Map.Entry<T, Set<T>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }



}
class prog {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String tmp = sc.nextLine();
        String parts[] = tmp.split(",");

        int m = Integer.parseInt(parts[0]);
        int n = Integer.parseInt(parts[1]);

        String lines[] = new String[m];

        AdjacencyListGraph<String> mazeGraph = new AdjacencyListGraph<>();

        String startVertex = "";
        String endVertex = "";

        for(int i=0;i<m;i++) {
            lines[i] = sc.nextLine();

            for(int j = 0; j < n; j++) {
                if(lines[i].charAt(j) != '#') {
                    mazeGraph.addVertex(i + "," + j);

                    if(lines[i].charAt(j) == 'S') {
                        startVertex = i + "," + j;
                    } else if(lines[i].charAt(j) == 'E') {
                        endVertex = i + "," + j;
                    }

                    if(i>0 && lines[i-1].charAt(j) != '#') {
                        mazeGraph.addEdge((i-1) + "," + j, i + "," + j);
                    }

                    if(j>0 && lines[i].charAt(j-1) != '#') {
                        mazeGraph.addEdge(i + "," + (j-1), i + "," + j);
                    }
                }
            }
        }
        sc.close();

        mazeGraph.findPath(startVertex, endVertex);
    }

}

