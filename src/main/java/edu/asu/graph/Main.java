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
            Node dst = new Node("c");

            System.out.println();
            System.out.println("BFS Search:");
            System.out.println(g.graphSearch(src, dst, Algorithm.BFS));

            System.out.println();
            System.out.println("DFS Search:");
            System.out.println(g.graphSearch(src, dst, Algorithm.DFS));

            System.out.println();
            System.out.println("Random Walk Search:");
            System.out.println(g.graphSearch(src, dst, Algorithm.RANDOM));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
