package edu.asu.graph;

public class Main {

    // args:
    // [0] input DOT file path
    // [1] output text summary file path
    // [2] output PNG file path
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Main <input.dot> <out.txt> <out.png>");
            return;
        }

        String dotIn = args[0];
        String summaryOut = args[1];
        String pngOut = args[2];

        try {
            Graph g = GraphParser.parseGraph(dotIn);

            System.out.println("Parsed graph:");
            System.out.println(g.toString());

            g.outputGraph(summaryOut);

            GraphExporter.outputGraphics(g, pngOut, "png");

            System.out.println("Wrote summary to " + summaryOut);
            System.out.println("Wrote PNG to " + pngOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
