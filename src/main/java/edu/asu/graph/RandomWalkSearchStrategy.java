package edu.asu.graph;

public class RandomWalkSearchStrategy implements SearchStrategy {

    @Override
    public Path search(Graph graph, Node src, Node dst) {
        RandomWalkSearch random = new RandomWalkSearch();
        return random.search(graph, src, dst);
    }
}
