package edu.asu.graph;

import java.util.*;

public class BFSSearch extends AbstractGraphSearch {

    private final Queue<Path> queue = new LinkedList<>();

    @Override
    protected void add(Path path) {
        queue.add(path);
    }

    @Override
    protected Path next() {
        return queue.poll();
    }

    @Override
    protected boolean isEmpty() {
        return queue.isEmpty();
    }
}
