package com.task.network.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String value;
    Node parentNode;
    List<Node> childNodes;
    int depth = 0;
    
    public Node(String value) {
        this.value = value;
    }
    
    public void addChild(ArrayList<String> childValues) {
        ArrayList<Node> childNodes = new ArrayList<>();
        depth++;
        
        for (String nodeValue : childValues)
            childNodes.add(new Node(nodeValue));
        
        this.childNodes = childNodes;
    }
    
    public Node getParentNode() {
        return parentNode;
    }
    
    public int getDepth() {
        return depth;
    }
    
    public void setDepth(int depth) {
        this.depth = depth;
    }
}
