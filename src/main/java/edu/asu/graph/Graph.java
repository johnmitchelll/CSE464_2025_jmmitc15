package edu.asu.graph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Graph {
    private final Set<String> nodes = new LinkedHashSet<>();
    private final Set<String> edges = new LinkedHashSet<>();

    public Graph() {
    }

    public void addNode(String label) {
        if (label == null || label.isBlank()) return;
        nodes.add(label.trim());
    }

    public void addNodes(String[] labels) {
        if (labels == null) return;
        for (String l : labels) {
            addNode(l);
        }
    }

    public void addEdge(String srcLabel, String dstLabel) {
        if (srcLabel == null || dstLabel == null) return;
        String src = srcLabel.trim();
        String dst = dstLabel.trim();
        if (src.isBlank() || dst.isBlank()) return;

        addNode(src);
        addNode(dst);

        String edgeRep = src + " -> " + dst;
        edges.add(edgeRep);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Number of nodes: ").append(nodes.size()).append("\n");
        sb.append("Nodes: ").append(nodes).append("\n");
        sb.append("Number of edges: ").append(edges.size()).append("\n");
        sb.append("Edges:\n");
        for (String e : edges) {
            sb.append(e).append("\n");
        }
        return sb.toString();
    }

    public void outputGraph(String filepath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
            bw.write(this.toString());
        }
    }

    public Set<String> getNodes() {
        return Collections.unmodifiableSet(nodes);
    }

    public Set<String> getEdges() {
        return Collections.unmodifiableSet(edges);
    }
}
