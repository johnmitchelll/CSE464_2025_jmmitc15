package edu.asu.graph;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            Graph g = GraphParser.parseDotFile("sample.dot");

            Set<Node> nodes = g.getAllNodes();
            int edgeCount = 0;

            for (Node n : nodes) {
                edgeCount += g.getNeighbors(n).size();
            }

            System.out.println("Number of nodes: " + nodes.size());

            List<String> names = new ArrayList<>();
            for (Node n : nodes) {
                names.add(n.getName());
            }
            Collections.sort(names);
            System.out.println("Nodes: " + names);

            System.out.println("Number of edges: " + edgeCount);
            System.out.println("Edges:");

            for (Node n : nodes) {
                for (Node neighbor : g.getNeighbors(n)) {
                    System.out.println(n.getName() + " -> " + neighbor.getName());
                }
            }

            Node src = new Node("a");
            Node dst = new Node("h");

            System.out.println();
            System.out.println("BFS Final Path:");
            System.out.println(g.graphSearch(src, dst, Algorithm.BFS));

            System.out.println();
            System.out.println("DFS Final Path:");
            System.out.println(g.graphSearch(src, dst, Algorithm.DFS));

            System.out.println();
            System.out.println("Random Walk Attempts:");

            for (int i = 1; i <= 5; i++) {
                Path randomPath = g.graphSearch(src, dst, Algorithm.RANDOM);
                System.out.println("Attempt " + i + ": " + randomPath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
