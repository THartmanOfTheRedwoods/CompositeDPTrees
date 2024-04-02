package edu.redwoods.cis18.springboot.model;

public interface TreeComponent {
    void setData(int data);
    int getData();
    void setName(String name);
    String getName();
    TreeComponent getLeftChild();
    void setLeftChild(TreeComponent child);
    TreeComponent getRightChild();
    void setRightChild(TreeComponent child);
    void add(TreeComponent child);
    void remove();
    TreeComponent getParent();
    void setParent(TreeComponent parent);
    TreeComponent find(int data);
    TreeComponent findLargest();
    TreeComponent findLeast();
    boolean hasLeftChild();
    boolean hasRightChild();
    boolean isLeaf();
    boolean hasOneChild();
    boolean isRoot();
    void traverse();
    String nodeGraph();
}