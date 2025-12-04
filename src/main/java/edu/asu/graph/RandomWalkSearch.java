package edu.asu.graph;

import java.util.*;

public class RandomWalkSearch extends AbstractGraphSearch {

    private final Random random = new Random();

    @Override
    protected void add(Path path) {
    }

    @Override
    protected Path next() {
        return null;
    }

    @Override
    protected boolean isEmpty() {
        return false;
    }

    @Override
    public Path search(Graph graph, Node src, Node dst) {
        Set<Node> visited = new HashSet<>();
        Path path = new Path(src);
        Node current = src;

        int safetyLimit = 100;

        while (!current.equals(dst) && safetyLimit-- > 0) {
            visited.add(current);
            List<Node> neighbors = new ArrayList<>(graph.getNeighbors(current));

            if (neighbors.isEmpty()) {
                break;
            }

            Collections.shuffle(neighbors);
            Node next = neighbors.get(0);

            path.add(next);
            current = next;
        }

        return path;
    }
}
