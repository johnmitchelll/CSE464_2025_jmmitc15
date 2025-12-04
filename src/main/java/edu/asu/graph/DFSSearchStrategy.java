package edu.asu.graph;

public class DFSSearchStrategy implements SearchStrategy {

    @Override
    public Path search(Graph graph, Node src, Node dst) {
        DFSSearch dfs = new DFSSearch();
        return dfs.search(graph, src, dst);
    }
}
