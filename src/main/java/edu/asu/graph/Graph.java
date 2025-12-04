package edu.asu.graph;

import java.util.*;

public class Graph {

    private final Map<Node, List<Node>> adjList = new HashMap<>();

    public void addNode(Node node) {
        adjList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(Node from, Node to) {
        adjList.putIfAbsent(from, new ArrayList<>());
        adjList.putIfAbsent(to, new ArrayList<>());
        adjList.get(from).add(to);
    }

    public List<Node> getNeighbors(Node node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    public Set<Node> getAllNodes() {
        return adjList.keySet();
    }
}
