package dcs.nxt;

import java.util.*;

public class Node implements Comparable<Node> {

    // The coordinate for the point
    private Coordinate coordinate;

    // The 'has seen' flag for DFS implementations
    private boolean hasSeen = false;

    // The possible sub-nodes of the graph. The graph may not be a binary tree.
    private SortedSet<Node> subnodes;

    // An optional string label for this node
    private String label;

    public Node(String label, int x, int y) {
        this.coordinate = new Coordinate(x, y);
        this.subnodes = new TreeSet<>();
        this.label = label;
    }

    public void addLink(Node node) {
        this.subnodes.add(node);
    }

    public Node nextChild() {
        return this.subnodes.stream()
                .sequential()
                .filter((node) -> node.hasSeen)
                .findFirst()
                .orElse(null);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean hasSeen() {
        if (subnodes.isEmpty()) {
            return hasSeen;
        } else {
            return subnodes.stream()
                    .map(Node::hasSeen)
                    .reduce(Boolean::logicalAnd)
                    .get();
        }
    }

    public Node[] getSubnodes() {
        return (Node[]) subnodes.toArray();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public int compareTo(Node other) {
        // Left is -ve compared to origin, Right is +ve.
        int delta = this.coordinate.getX() - other.getCoordinate().getX();

        if (delta == 0) {
            delta = this.label.compareTo(other.getLabel());
        }

        return delta;
    }
}
