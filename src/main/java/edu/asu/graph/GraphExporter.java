package edu.asu.graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GraphExporter {

    public static void outputDOTGraph(Graph g, String path) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("digraph G {\n");

            for (String n : g.getNodes()) {
                bw.write("    " + n + ";\n");
            }

            for (String e : g.getEdges()) {
                bw.write("    " + e + ";\n");
            }

            bw.write("}\n");
        }
    }

    public static void outputGraphics(Graph g, String outputPath, String format)
            throws IOException, InterruptedException {

        File outFile = new File(outputPath);
        File parent = outFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        File tempDot = File.createTempFile("graph_export_", ".dot");
        outputDOTGraph(g, tempDot.getAbsolutePath());

        ProcessBuilder pb = new ProcessBuilder(
                "dot",
                "-T" + format,
                tempDot.getAbsolutePath(),
                "-o",
                outputPath
        );

        pb.redirectErrorStream(true);
        Process p = pb.start();
        int exit = p.waitFor();
        if (exit != 0) {
            throw new RuntimeException("dot command failed with exit code " + exit);
        }

        tempDot.delete();
    }
}
