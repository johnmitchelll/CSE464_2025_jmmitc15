package edu.asu.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    public void testBFSSearch() throws Exception {
        Graph g = GraphParser.parseDotFile("sample.dot");

        Node src = new Node("a");
        Node dst = new Node("c");

        Path result = g.graphSearch(src, dst, Algorithm.BFS);
        assertNotNull(result);
        assertEquals("c", result.getLastNode().getName());
    }

    @Test
    public void testDFSSearch() throws Exception {
        Graph g = GraphParser.parseDotFile("sample.dot");

        Node src = new Node("a");
        Node dst = new Node("c");

        Path result = g.graphSearch(src, dst, Algorithm.DFS);
        assertNotNull(result);
        assertEquals("c", result.getLastNode().getName());
    }

    @Test
    public void testRandomSearch() throws Exception {
        Graph g = GraphParser.parseDotFile("sample.dot");

        Node src = new Node("a");
        Node dst = new Node("c");

        Path result = g.graphSearch(src, dst, Algorithm.RANDOM);
        assertNotNull(result);
        assertEquals("c", result.getLastNode().getName());
    }

    @Test
    public void testExporter() throws Exception {
        Graph g = GraphParser.parseDotFile("sample.dot");
        GraphExporter.exportToDot(g, "test_output.dot");
    }
}
