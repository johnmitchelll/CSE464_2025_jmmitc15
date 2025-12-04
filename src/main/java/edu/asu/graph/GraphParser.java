package edu.asu.graph;

import java.io.*;
import java.util.*;

public class GraphParser {

    public static Graph parseDotFile(String filePath) throws IOException {
        Graph graph = new Graph();
        Map<String, Node> nodeCache = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (line.contains("->")) {
                String[] parts = line.replace(";", "").split("->");
                String fromLabel = parts[0].trim();
                String toLabel = parts[1].trim();

                Node from = nodeCache.computeIfAbsent(fromLabel, Node::new);
                Node to = nodeCache.computeIfAbsent(toLabel, Node::new);

                graph.addNode(from);
                graph.addNode(to);
                graph.addEdge(from, to);
            } else if (line.endsWith(";") && !line.contains("digraph")) {
                String label = line.replace(";", "").trim();
                Node node = nodeCache.computeIfAbsent(label, Node::new);
                graph.addNode(node);
            }
        }

        reader.close();
        return graph;
    }
}
