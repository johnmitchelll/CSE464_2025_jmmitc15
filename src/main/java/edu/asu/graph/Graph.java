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

    public void removeNode(String label) {
        if (label == null || label.isBlank()) {
            throw new IllegalArgumentException("Node label cannot be null or blank");
        }
        String trimmed = label.trim();
        if (!nodes.contains(trimmed)) {
            throw new IllegalArgumentException("Node does not exist: " + trimmed);
        }

        nodes.remove(trimmed);

        // remove all edges touching this node
        edges.removeIf(e -> {
            String[] parts = e.split("->");
            if (parts.length != 2) return false;
            String src = parts[0].trim();
            String dst = parts[1].trim();
            return src.equals(trimmed) || dst.equals(trimmed);
        });
    }

    public void removeNodes(String[] labels) {
        if (labels == null) {
            throw new IllegalArgumentException("Labels array cannot be null");
        }
        for (String l : labels) {
            removeNode(l);
        }
    }

    public void removeEdge(String srcLabel, String dstLabel) {
        if (srcLabel == null || dstLabel == null) {
            throw new IllegalArgumentException("Labels cannot be null");
        }
        String src = srcLabel.trim();
        String dst = dstLabel.trim();
        if (src.isBlank() || dst.isBlank()) {
            throw new IllegalArgumentException("Labels cannot be blank");
        }

        String edgeRep = src + " -> " + dst;
        if (!edges.contains(edgeRep)) {
            throw new IllegalArgumentException("Edge does not exist: " + edgeRep);
        }
        edges.remove(edgeRep);
    }

    private Map<String, List<String>> buildAdjacency() {
        Map<String, List<String>> adj = new LinkedHashMap<>();
        for (String n : nodes) {
            adj.put(n, new ArrayList<>());
        }
        for (String e : edges) {
            String[] parts = e.split("->");
            if (parts.length != 2) continue;
            String src = parts[0].trim();
            String dst = parts[1].trim();
            if (!adj.containsKey(src)) {
                adj.put(src, new ArrayList<>());
            }
            adj.get(src).add(dst);
        }
        return adj;
    }

    public Path GraphSearch(Node src, Node dst, Algorithm algo) {
        if (src == null || dst == null || algo == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        String s = src.getLabel();
        String d = dst.getLabel();

        if (!nodes.contains(s) || !nodes.contains(d)) {
            return null;
        }

        Map<String, List<String>> adj = buildAdjacency();

        switch (algo) {
            case BFS:
                return bfs(adj, s, d);
            case DFS:
                return dfs(adj, s, d);
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algo);
        }
    }

    private Path bfs(Map<String, List<String>> adj, String s, String d) {
        Queue<String> q = new ArrayDeque<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        q.add(s);
        visited.add(s);

        while (!q.isEmpty()) {
            String cur = q.remove();
            if (cur.equals(d)) {
                return buildPath(parent, s, d);
            }
            for (String nei : adj.getOrDefault(cur, Collections.emptyList())) {
                if (!visited.contains(nei)) {
                    visited.add(nei);
                    parent.put(nei, cur);
                    q.add(nei);
                }
            }
        }
        return null;
    }

    private Path dfs(Map<String, List<String>> adj, String s, String d) {
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();
        if (!dfsVisit(adj, s, d, visited, parent)) {
            return null;
        }
        return buildPath(parent, s, d);
    }

    private boolean dfsVisit(Map<String, List<String>> adj,
                            String cur,
                            String target,
                            Set<String> visited,
                            Map<String, String> parent) {
        visited.add(cur);
        if (cur.equals(target)) {
            return true;
        }
        for (String nei : adj.getOrDefault(cur, Collections.emptyList())) {
            if (!visited.contains(nei)) {
                parent.put(nei, cur);
                if (dfsVisit(adj, nei, target, visited, parent)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Path buildPath(Map<String, String> parent, String s, String d) {
        java.util.List<String> list = new java.util.ArrayList<>();
        String cur = d;
        list.add(cur);
        while (!cur.equals(s)) {
            String p = parent.get(cur);
            if (p == null) {
                return null;
            }
            list.add(p);
            cur = p;
        }
        java.util.Collections.reverse(list);
        return new Path(list);
    }

}
