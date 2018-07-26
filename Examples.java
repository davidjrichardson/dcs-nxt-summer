import dcs.nxt.Node;

import java.util.Arrays;

public class Examples {

    public static void main(String[] args) {
        // A graph is built by first specifying a root node
        // You then link in each of its children after creating
        // This is then repeated for each subsequent layer
        Node root = new Node("root", 0, 0);
        Node left1 = new Node("left 1", -1, -1);
        Node left2 = new Node("left 2", -2, -2);
        Node left3 = new Node("left 3", -3, -3);
        Node left4 = new Node("left 4", -4, -4);
        Node left5 = new Node("left 5", -5, -5);
        Node right1 = new Node("right 1", 1, -1);
        Node right2 = new Node("right 2", 2, -2);
        Node right3 = new Node("right 3", 3, -3);
        Node right4 = new Node("right 4", 4, -4);
        Node right5 = new Node("right 5", 5, -5);

        // To link them together you use the addChild(Node) method
        // The node you add to is the parent: foo.addChild(bar);
        // ---------------------------- parent ^ --------- ^ child
        root.addChild(left1);
        root.addChild(left2);
        root.addChild(right1);

        left1.addChild(right2);
        left1.addChild(right3);
        left1.addChild(left3);

        right2.addChild(left4);

        right1.addChild(left5);
        right1.addChild(right4);

        right4.addChild(right5);

        // Each node will have a nextChild() method that outputs the next
        // unseen child. Checking if a node has been seen will return whether
        // or not its subtree (or itself if no children) has been marked as seen.

        // This will recurse over the entire tree, printing out the nodes using post-order
        // traversal
        postOrderTraverse(root);

        // The distances between nodes can be found between two nodes by using the
        // directionsTo(node) method
        System.out.println(root.directionsTo(right3));
    }

    private static void postOrderTraverse(Node node) {
        // If the node has no children then it can be marked as seen and its label can
        // be printed
        if (node.nextChild() == null) {
            node.markAsSeen(true);

            System.out.println(node.getLabel());

            return;
        }

        // This makes sure each subtree of the current node is also recursed over
        Node n;
        while ((n = node.nextChild()) != null) {
            postOrderTraverse(n);
        }
    }
}
