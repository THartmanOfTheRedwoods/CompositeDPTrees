package edu.redwoods.cis18.springboot.model;

import edu.redwoods.cis18.springboot.helper.UniqueNameGenerator;
import org.springframework.stereotype.Component;

// This class is the client of the composite design pattern, which is composed of TreeComponent and TreeNode.
@Component
public class BinaryTree {

    private TreeComponent root;
    private int nodeCount;

    public BinaryTree(int data) {
        root = new TreeNode(data, UniqueNameGenerator.generateUniqueName(3));
        nodeCount = 1;
    }
    public BinaryTree() {
        nodeCount = 0;
        root = null;
    }

    public void addNode(int data) {
        if(root == null) {  // This is our root node, so add it.
            root = new TreeNode(data, UniqueNameGenerator.generateUniqueName(3));
        } else {
            root.add(new TreeNode(data, UniqueNameGenerator.generateUniqueName(3)));
        }
        nodeCount++;
    }

    public boolean removeNode(int needle) {
        if(root != null) {
            TreeComponent node = root.find(needle);
            if(node != null) {
                if(node.equals(root) && nodeCount == 1) { // Handle when the user asks us to delete the only node left.
                    this.root = null;
                } else { // Handle all other situations.
                    node.remove();
                }
                nodeCount--;
                return true;
            } // Handle the situation where the needle doesn't exist in the tree.
        } // Handle the situation where the root node has been removed.
        return false;
    }

    public void removeAll() {
        // This is not the most efficient, but whatever. (-:
        while(root != null ) {
            this.removeNode(root.getData());
        }
    }

    public TreeComponent find(int needle) {
        if(root != null) {
            return root.find(needle);
        }
        return null; // Handle the situation where the tee is empty
    }

    public void traverse() {
        System.out.println("graph");
        if(root != null) {
            root.traverse();
        }
        System.out.println();
    }

    public String treeGraph() {
        String graph = String.format("graph%n");
        if(root != null) {
            graph += root.nodeGraph();
        }
        graph += String.format("%n");
        return graph;
    }

    // A Simple test method for this BinaryTree class, probably should be in unit tests :-),
    // but shows various ways to utilize this class.
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree(15);
        binaryTree.addNode(9);
        binaryTree.addNode(23);
        binaryTree.addNode(3);
        binaryTree.addNode(12);
        binaryTree.addNode(17);
        binaryTree.addNode(28);
        binaryTree.addNode(1);
        binaryTree.addNode(8);
        binaryTree.addNode(4);
        binaryTree.traverse();
        System.out.println(binaryTree.root.getLeftChild().findLargest().getData());
        int needle = 5;
        TreeComponent child = binaryTree.find(needle);
        if(child != null) { // null implies we didn't find anything!
            System.out.println(child.getData());
            child.remove();
        }
        boolean result = binaryTree.removeNode(28);
        System.out.printf("Removed? %b%n", result);
        binaryTree.traverse();
        result = binaryTree.removeNode(8);
        System.out.printf("Removed? %b%n", result);
        binaryTree.traverse();
        result = binaryTree.removeNode(9);
        System.out.printf("Removed? %b%n", result);
        binaryTree.traverse();
    }

    public int getNodeCount() {
        return nodeCount;
    }
}