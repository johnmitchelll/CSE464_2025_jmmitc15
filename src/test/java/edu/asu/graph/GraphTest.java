package edu.asu.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;

public class GraphTest {

    @Test
    public void testAddNodesAndEdges() {
        Graph g = new Graph();
        g.addNode("a");
        g.addNode("b");
        g.addNode("a"); // duplicate
        g.addEdge("a", "b");
        g.addEdge("a", "b"); // duplicate edge ignored

        String out = g.toString();
        assertTrue(out.contains("Number of nodes: 2"));
        assertTrue(out.contains("Number of edges: 1"));
        assertTrue(out.contains("a -> b"));
    }

    @Test
    public void testParseGraphSimple() throws Exception {
        File tmp = File.createTempFile("testgraph", ".dot");
        Files.writeString(tmp.toPath(),
                "digraph G {\n" +
                        "  a -> b;\n" +
                        "  b -> c;\n" +
                        "  c;\n" +
                        "}\n");

        Graph g = GraphParser.parseGraph(tmp.getAbsolutePath());
        String out = g.toString();

        assertTrue(out.contains("Number of nodes: 3"));
        assertTrue(out.contains("a -> b"));
        assertTrue(out.contains("b -> c"));
        assertTrue(out.contains("Nodes: [a, b, c]") ||
                out.contains("Nodes: [a, b, c,]"));
    }

    @Test
    public void testOutputGraphAndDOT() throws Exception {
        Graph g = new Graph();
        g.addEdge("x", "y");
        g.addEdge("y", "z");

        File summary = File.createTempFile("summary", ".txt");
        g.outputGraph(summary.getAbsolutePath());
        String summaryText = Files.readString(summary.toPath());
        assertTrue(summaryText.contains("x -> y"));
        assertTrue(summaryText.contains("y -> z"));

        File dotOut = File.createTempFile("graph", ".dot");
        GraphExporter.outputDOTGraph(g, dotOut.getAbsolutePath());
        String dotText = Files.readString(dotOut.toPath());
        assertTrue(dotText.contains("digraph G"));
        assertTrue(dotText.contains("x -> y;"));
    }

    @Test
    public void testRemoveNodeAndEdges() {
        Graph g = new Graph();
        g.addEdge("a", "b");
        g.addEdge("b", "c");
        g.addEdge("c", "a"); // triangle

        g.removeNode("b");

        String out = g.toString();
        assertTrue(out.contains("Number of nodes: 2"));
        // make sure the remaining nodes are a and c
        assertTrue(out.contains("Nodes: [a, c]") || out.contains("Nodes: [c, a]"));
        // make sure edges touching b are gone
        assertFalse(out.contains("a -> b"));
        assertFalse(out.contains("b -> c"));
    }

    @Test
    public void testRemoveNodesThrowsOnMissing() {
        Graph g = new Graph();
        g.addNode("a");
        g.addNode("b");

        assertThrows(IllegalArgumentException.class, () -> g.removeNode("x"));

        assertThrows(IllegalArgumentException.class,
                () -> g.removeNodes(new String[]{"a", "x"}));
    }

    @Test
    public void testRemoveEdgeAndThrowOnMissing() {
        Graph g = new Graph();
        g.addEdge("a", "b");

        g.removeEdge("a", "b");
        String out = g.toString();
        assertTrue(out.contains("Number of edges: 0"));

        assertThrows(IllegalArgumentException.class,
                () -> g.removeEdge("a", "b"));

        assertThrows(IllegalArgumentException.class,
                () -> g.removeEdge("x", "y"));
    }

    @Test
    public void testGraphSearchBfsAndDfs() {
        Graph g = new Graph();
        g.addEdge("a", "b");
        g.addEdge("b", "c");
        g.addEdge("c", "d");

        Node src = new Node("a");
        Node dst = new Node("d");

        Path bfsPath = g.GraphSearch(src, dst, Algorithm.BFS);
        Path dfsPath = g.GraphSearch(src, dst, Algorithm.DFS);

        assertNotNull(bfsPath);
        assertNotNull(dfsPath);
        assertEquals("[a, b, c, d]", bfsPath.getNodes().toString());
        assertEquals("[a, b, c, d]", dfsPath.getNodes().toString());
    }
}