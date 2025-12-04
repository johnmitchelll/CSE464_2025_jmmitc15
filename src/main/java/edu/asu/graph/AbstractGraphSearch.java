package edu.asu.graph;

import java.util.*;

public abstract class AbstractGraphSearch {

    protected abstract void add(Path path);

    protected abstract Path next();

    protected abstract boolean isEmpty();

    public Path search(Graph graph, Node src, Node dst) {
        Set<Node> visited = new HashSet<>();
        add(new Path(src));

        while (!isEmpty()) {
            Path current = next();
            Node last = current.getLastNode();

            if (last.equals(dst)) {
                return current;
            }

            if (!visited.contains(last)) {
                visited.add(last);
                for (Node neighbor : graph.getNeighbors(last)) {
                    if (!visited.contains(neighbor)) {
                        Path nextPath = new Path(current);
                        nextPath.add(neighbor);
                        add(nextPath);
                    }
                }
            }
        }

        return null;
    }
}
