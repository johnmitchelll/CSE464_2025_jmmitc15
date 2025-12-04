package edu.asu.graph;

import java.util.*;

public class DFSSearch extends AbstractGraphSearch {

    private final Stack<Path> stack = new Stack<>();

    @Override
    protected void add(Path path) {
        stack.push(path);
    }

    @Override
    protected Path next() {
        return stack.pop();
    }

    @Override
    protected boolean isEmpty() {
        return stack.isEmpty();
    }
}
