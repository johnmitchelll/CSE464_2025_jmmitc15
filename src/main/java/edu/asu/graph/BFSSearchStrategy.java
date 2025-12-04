package edu.asu.graph;

public class BFSSearchStrategy implements SearchStrategy {

    @Override
    public Path search(Graph graph, Node src, Node dst) {
        BFSSearch bfs = new BFSSearch();
        return bfs.search(graph, src, dst);
    }
}
