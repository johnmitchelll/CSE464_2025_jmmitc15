package edu.asu.graph;

import java.util.*;

public class RandomWalkSearch extends AbstractGraphSearch {

    private final List<Path> bag = new ArrayList<>();
    private final Random random = new Random();

    @Override
    protected void add(Path path) {
        bag.add(path);
    }

    @Override
    protected Path next() {
        return bag.remove(random.nextInt(bag.size()));
    }

    @Override
    protected boolean isEmpty() {
        return bag.isEmpty();
    }
}
