package dcs.nxt;

import java.util.*;

public class Node implements Comparable<Node> {

    // The coordinate for the point
    private Coordinate coordinate;

    // The 'has seen' flag for DFS implementations
    private boolean isSeen = false;

    // The possible sub-nodes of the graph. The graph may not be a binary tree.
    private SortedSet<Node> subnodes;

    // An optional string label for this node
    private String label;

    public Node(int x, int y) {
        this(null, x, y);
    }

    public Node(String label, int x, int y) {
        this.coordinate = new Coordinate(x, y);
        this.subnodes = new TreeSet<>();
        this.label = label;
    }

    public void markAsSeen(boolean seen) {
        this.isSeen = seen;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public void addChild(Node node) {
        this.subnodes.add(node);
    }

    public Node nextChild() {
        return this.subnodes.stream()
                .sequential()
                .filter((node) -> !node.isSeen)
                .findFirst()
                .orElse(null);
    }

    public Coordinate getCoordinates() {
        return coordinate;
    }

    public boolean isSeen() {
        if (subnodes.isEmpty()) {
            return isSeen;
        } else {
            return subnodes.stream()
                    .map(Node::isSeen)
                    .reduce(Boolean::logicalAnd)
                    .get();
        }
    }

    public Node[] getSubnodes() {
        return (Node[]) subnodes.toArray();
    }

    public SortedSet<Node> getSubnodeSet() {
        return this.subnodes;
    }

    public String getLabel() {
        return label;
    }

    public Vector directionsTo(Node other) {
        return this.coordinate.getDifference(other.getCoordinates());
    }

    @Override
    public int compareTo(Node other) {
        // Left is -ve compared to origin, Right is +ve.
        int delta = this.coordinate.getX() - other.getCoordinates().getX();

        if (delta == 0) {
            delta = this.label.compareTo(other.getLabel());
        }

        return delta;
    }
}
