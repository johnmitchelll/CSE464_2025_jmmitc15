package edu.asu.graph;

import java.io.*;
import java.util.*;

public class GraphExporter {

    public static void exportToDot(Graph g, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        writer.write("digraph G {\n");

        for (Node node : g.getAllNodes()) {
            for (Node neighbor : g.getNeighbors(node)) {
                writer.write("    " + node.getName() + " -> " + neighbor.getName() + ";\n");
            }
        }

        writer.write("}\n");
        writer.close();
    }
}
