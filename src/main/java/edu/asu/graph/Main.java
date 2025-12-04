package edu.asu.graph;

public class Main {

    public static void main(String[] args) {
        try {
            Graph g = GraphParser.parseDotFile("sample.dot");

            Node src = new Node("a");
            Node dst = new Node("c");

            System.out.println("BFS Search:");
            Path bfsPath = g.graphSearch(src, dst, Algorithm.BFS);
            System.out.println(bfsPath);

            System.out.println("\nDFS Search:");
            Path dfsPath = g.graphSearch(src, dst, Algorithm.DFS);
            System.out.println(dfsPath);

            System.out.println("\nRandom Walk Search:");
            Path randomPath = g.graphSearch(src, dst, Algorithm.RANDOM);
            System.out.println(randomPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
