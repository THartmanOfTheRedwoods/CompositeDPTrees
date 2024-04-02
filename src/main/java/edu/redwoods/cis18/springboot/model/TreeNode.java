package edu.redwoods.cis18.springboot.model;

import edu.redwoods.cis18.springboot.model.TreeComponent;

public class TreeNode implements TreeComponent {

    private int data;
    private String name;
    private TreeComponent leftChild;
    private TreeComponent rightChild;
    private TreeComponent parent;

    public TreeNode(int data, String name) {
        this.data = data;
        this.name = name;
    }

    @Override
    public void setData(int data) {
        this.data = data;
    }

    @Override
    public int getData() {
        return data;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public TreeComponent getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeComponent node) {
        // If node is null, we assume this is an ORDER to set the child to null;
        if(node == null) { this.leftChild = null; return;}

        if(this.leftChild != null) {
            this.leftChild.add(node);
        } else {
            // This is not recursive, so let's tell the node I'm its parent too
            node.setParent(this);
            this.leftChild = node;
        }
    }

    @Override
    public TreeComponent getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeComponent node) {
        // If node is null, we assume this is an ORDER to set the child to null;
        if(node == null) { this.rightChild = null; return;}

        if(this.rightChild != null) {
            this.rightChild.add(node);
        } else {
            // This is not recursive, so let's tell the node I'm its parent too
            node.setParent(this);
            this.rightChild = node;
        }
    }

    @Override
    public void add(TreeComponent node) {
        // Check to see if node is greater or less then me
        if(node.getData() > this.getData()) { // add to right if data is >
            this.setRightChild(node);
        } else {
            this.setLeftChild(node); // add to left if data is <=
        }
    }

    // This method assumes we've already found the node we want to remove and have called this method with it.
    @Override
    public void remove() {
        if(this.isLeaf()) {  // We just need to reset the parent pointer.
            if(!this.isRoot()) {
                if (this.parent.hasLeftChild() && this.parent.getLeftChild().equals(this)) {
                    this.parent.setLeftChild(null);
                } else {
                    this.parent.setRightChild(null);
                }
                this.parent = null;
            } // If we are also the root, we do nothing because we can't delete self.
        } else if(this.hasOneChild()) { // If we only have one child, just move child up
            TreeComponent replacement = (this.hasLeftChild()) ? this.leftChild : this.rightChild;
            this.setData(replacement.getData());
            replacement.remove();
        } else {  // We need to find the largest node in the left child and swap it with this one.
            TreeComponent largest = this.leftChild.findLargest();
            this.setData(largest.getData());
            largest.remove();
        }
    }

    @Override
    public TreeComponent getParent() {
        return parent;
    }

    @Override
    public void setParent(TreeComponent parent) {
        this.parent = parent;
    }

    @Override
    public TreeComponent find(int data) {
        TreeComponent node = this;
        while(node != null) {
            if(data > node.getData()) { // Look right
                node = node.getRightChild();
            } else if(data < node.getData()) { // Look left
                node = node.getLeftChild();
            } else { // we're equal and this is the data you're looking for
                return node;
            }
        }
        return null;
    }

    @Override
    public TreeComponent findLargest() {
        TreeComponent node = this;
        // We know the largest node is on the right-most leaf, so keep going right to find it.
        while(node.hasRightChild()) {
            node = node.getRightChild();
        }
        return node;
    }

    @Override
    public TreeComponent findLeast() {
        TreeComponent node = this;
        // We know the smallest node is on the left-most leaf, so keep going left to find it.
        while(node.hasLeftChild()) {
            node = node.getLeftChild();
        }
        return node;
    }

    @Override
    public boolean hasLeftChild() {
        return leftChild != null;
    }

    @Override
    public boolean hasRightChild() {
        return rightChild != null;
    }

    @Override
    public boolean isLeaf() {
        return !this.hasLeftChild() && !this.hasRightChild();
    }

    @Override
    public boolean hasOneChild() {
        return this.hasLeftChild() ^ this.hasRightChild();
    }

    @Override
    public boolean isRoot() {
        return this.parent == null;  // If your parent is null, you are the root.
    }

    @Override
    public void traverse() {
        String graphTemplate = "  %s[\"%s\"]%s%n";
        if(this.isLeaf()) {
            System.out.printf(graphTemplate, name, data, "");
        } else {
            if (leftChild != null) {
                System.out.printf(graphTemplate, name, data, "-->" + leftChild.getName());
                leftChild.traverse();
            }
            if (rightChild != null) {
                System.out.printf(graphTemplate, name, data, "-->" + rightChild.getName());
                rightChild.traverse();
            }
        }
    }

    @Override
    public String nodeGraph() {
        String graphTemplate = "  %s[\"%s\"]%s%n";
        String result = "";
        if(this.isLeaf()) {
            result += String.format(graphTemplate, name, data, "");
        } else {
            if (leftChild != null) {
                result += String.format(graphTemplate, name, data, "-->" + leftChild.getName());
                result += leftChild.nodeGraph();
            }
            if (rightChild != null) {
                result += String.format(graphTemplate, name, data, "-->" + rightChild.getName());
                result += rightChild.nodeGraph();
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) { return true; }
        if (!(o instanceof TreeComponent)) {
            return false;
        }
        // typecast o to Complex so that we can compare data members
        TreeComponent c = (TreeComponent)o;
        // Compare the data members and return accordingly
        return this.getData() == c.getData();
    }
}