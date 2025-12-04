package edu.asu.graph;

import java.util.*;

public class Path {

    private final List<Node> nodes;

    public Path(Node startNode) {
        this.nodes = new ArrayList<>();
        nodes.add(startNode);
    }

    public Path(Path existingPath) {
        this.nodes = new ArrayList<>(existingPath.nodes);
    }

    public void add(Node node) {
        nodes.add(node);
    }

    public Node getLastNode() {
        return nodes.get(nodes.size() - 1);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        return "Path{nodes=" + nodes + '}';
    }
}
