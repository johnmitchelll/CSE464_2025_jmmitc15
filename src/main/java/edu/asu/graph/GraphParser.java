package edu.asu.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphParser {

    public static Graph parseGraph(String filepath) throws IOException {
        Graph g = new Graph();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) continue;
                if (line.startsWith("//")) continue;
                if (line.startsWith("#")) continue;
                if (line.startsWith("digraph")) continue;
                if (line.startsWith("graph")) continue;
                if (line.startsWith("{")) continue;
                if (line.startsWith("}")) continue;

                if (line.endsWith(";")) {
                    line = line.substring(0, line.length() - 1).trim();
                }

                if (line.contains("->")) {
                    String[] parts = line.split("->");
                    if (parts.length == 2) {
                        String src = parts[0].trim();
                        String dst = parts[1].trim();
                        if (!src.isEmpty() && !dst.isEmpty()) {
                            g.addEdge(src, dst);
                        }
                    }
                } else {
                    String nodeLabel = line.trim();
                    if (!nodeLabel.isEmpty()) {
                        g.addNode(nodeLabel);
                    }
                }
            }
        }

        return g;
    }
}
