package edu.asu.graph;

public class Algorithm {

    public static Path bfs(Graph graph, Node src, Node dst) {
        BFSSearch bfs = new BFSSearch();
        return bfs.search(graph, src, dst);
    }

    public static Path dfs(Graph graph, Node src, Node dst) {
        DFSSearch dfs = new DFSSearch();
        return dfs.search(graph, src, dst);
    }
}
