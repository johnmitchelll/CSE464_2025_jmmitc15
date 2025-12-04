package edu.asu.graph;

import java.util.*;

public class Algorithm {

    public static Path bfs(Graph graph, Node src, Node dst) {
        Queue<Path> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        queue.add(new Path(src));

        while (!queue.isEmpty()) {
            Path current = queue.poll();
            Node last = current.getLastNode();

            if (last.equals(dst)) {
                return current;
            }

            if (!visited.contains(last)) {
                visited.add(last);
                expandNeighbors(graph, current, queue, visited);
            }
        }

        return null;
    }

    public static Path dfs(Graph graph, Node src, Node dst) {
        Stack<Path> stack = new Stack<>();
        Set<Node> visited = new HashSet<>();

        stack.push(new Path(src));

        while (!stack.isEmpty()) {
            Path current = stack.pop();
            Node last = current.getLastNode();

            if (last.equals(dst)) {
                return current;
            }

            if (!visited.contains(last)) {
                visited.add(last);
                expandNeighbors(graph, current, stack, visited);
            }
        }

        return null;
    }

    private static void expandNeighbors(Graph graph, Path current, Collection<Path> frontier, Set<Node> visited) {
        Node last = current.getLastNode();

        for (Node neighbor : graph.getNeighbors(last)) {
            if (!visited.contains(neighbor)) {
                Path next = new Path(current);
                next.add(neighbor);
                frontier.add(next);
            }
        }
    }
}
